package regonline;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import regonline.datasource.DAO;
import regonline.utils.TypeUtils;
@SuppressWarnings("serial")
public abstract class Controller<T extends Model> extends HttpServlet{
	protected DAO<T> dao;
	private Class<T> type;
	
	
	public Controller(Class<T> type){
		dao = new DAO<>(type);
		this.type = type;
	}
	
	/**
	 * The method takes care of GET requests: if a request has a parameter named <b>d</b> 
	 * it will be treated as a DELETE request, and will be handled by
	 * {@link #delete(HttpServletRequest, HttpServletResponse, String)} method
	 * <b>d</b>'s value should contain the id of a record that has to be deleted.<br/>
	 * If the request has a parameter named <b>e</b>, it will be treated as an "edit" request, 
	 * the value of <b>e</b> is the id of a record to be edited. this request will be handled in the
	 * {@link #edit(HttpServletRequest, HttpServletResponse, String)} method. <br/>
	 * Otherwise the request will be treated as a normal GET request which will be handled by the
	 * {@link #index(HttpServletRequest, HttpServletResponse)} method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String deleteId = request.getParameter("d");
		String editId = request.getParameter("e");
		if(deleteId != null){
			delete(request, response, deleteId);
		}
		else if(editId != null){
			edit(request, response, editId);
		}
		else{
			index(request, response);
		}
	}

	/**
	 * The method takes care of POST requests, if a request has a parameter named <b>u</b>
	 * it will be treated as a PUT request, handled by 
	 * {@link #update(HttpServletRequest, HttpServletResponse, String)} method <br/>
	 * The <b>u</b>'s value should contain the id of a record that has to be updated
	 * If the method does not have the <b>u</b> parameter, the request will be treated as a normal 
	 * POST request which will be handled by the
	 * {@link #create(HttpServletRequest, HttpServletResponse)} method
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String updateId = request.getParameter("u");
		if(updateId != null){
			processUpdateRequest(request, response, updateId);
		}
		else{
			processCreateRequest(request, response);
		}
	}
	
	/**
	 * Used to show an edit page (a page used for editing a record). A GET request has to have a <b>e</b> 
	 * parameter to indicate that it is a request to edit a resource (of type {@link Model}), the <b>e</b> parameter holds the 
	 * value of the id for the resource to be edited, the method loads the <code>edit.jsp</code> page.<br/>
	 * Before loading the page, an attribute is set in this request, this attribute is an object to be edited.
	 * The attribute's name is the object's class name (the name is in camel cases with the first latter as
	 * a lower case later), the value is the resource/object to be edited
	 * @see #doGet(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void edit(HttpServletRequest request, HttpServletResponse response, String resourceId) 
			throws ServletException, IOException{
		request.setAttribute(toCamelCase(type.getSimpleName()), dao.get(resourceId));
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	/**
	 * Handles GET requests when retrieving all resources for a specified type and forwards a request
	 * to the index page <br/>
	 * Before forwarding the request the {@link #beforeIndex()} method is invoked
	 * @see #doGet(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void index(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		request.setAttribute("all", dao.all());
		beforeIndex();
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * Used to write code that needs to be executed before the index page is loaded
	 */
	protected void beforeIndex() {
		
	}

	/**
	 * Handles POST requests when creating a resource (of type {@link Model})<br/>
	 * Before forwarding the request, the {@link #create(Model, HttpServletRequest)} and 
	 * {@link #createOrUpdate(Model, HttpServletRequest)} methods is invoked respectively
	 * @see #doPost(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processCreateRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		T obj = null;
		try {
			obj = type.newInstance();
			String id = request.getParameter("id");
			if(id != null){
				obj.setId(castId(id));
			}
			create(obj, request);
			createOrUpdate(obj, request);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		dao.save(obj);
		index(request, response);
	}

	/**
	 * The method is meant to be overridden (implemented) by a subclass of {@link Controller} to put code that
	 * is intended to be executed before the object is persisted in the database. <br/>
	 * This method is executed by the {@link #processCreateRequest(HttpServletRequest, HttpServletResponse)} 
	 * before creating the object
	 * @param obj
	 * @param request
	 */
	protected void create(T obj, HttpServletRequest request) {
		
	}

	/**
	 * Used for updating a record, a POST request has to have a <b>u</b> parameter to be treated as
	 * an update, the <b>u</b> parameter indicates the id of the resource to be updated. <br/>
	 * Before forwarding the request the {@link #update(Model, HttpServletRequest)} and 
	 * {@link #createOrUpdate(Model, HttpServletRequest)} methods is invoked respectively
	 * @see #doPost(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @param id the id of an record to be updated
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processUpdateRequest(HttpServletRequest request, HttpServletResponse response, String resourceId) 
			throws ServletException, IOException{
		T obj = dao.get(resourceId);
		update(obj, request);
		createOrUpdate(obj, request);
		dao.update(obj);
		index(request, response);
	}
	
	/**
	 * The method is meant to be overridden (implemented) by a subclass of {@link Controller} to put code that
	 * is intended to be executed before the object is persisted in the database. <br>
	 * This method is executed by {@link #processUpdateRequest(HttpServletRequest, HttpServletResponse, String)}
	 * method before updating an object
	 * @param obj
	 * @param request
	 */
	protected void update(T obj, HttpServletRequest request) {

	}

	/**
	 * The method is meant to be overridden (implemented) by a subclass of {@link Controller} to put code that
	 * is intended to be executed before the object is persisted in the database. <br>
	 * This method is executed by either {@link #processCreateRequest(HttpServletRequest, HttpServletResponse)}
	 * or {@link #processUpdateRequest(HttpServletRequest, HttpServletResponse, String)} 
	 * before creating or updating an object
	 * @param obj
	 * @param request
	 */
	protected void createOrUpdate(T obj, HttpServletRequest request) {

	}

	/**
	 * Used for deleting a resource, this is called from a GET request that has a <b>d</b> parameter, which
	 * is an id of the resource to be deleted
	 * @see #doGet(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @param id the id of an record to be deleted
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException{
		T obj = dao.load(id);
		if(obj != null){
			dao.delete(obj);
		}
		index(request, response);
	}
	
	
	private String toCamelCase(String name) {
		if(name == null || name.trim().isEmpty()){
			return name;
		}
		char[] buffer = name.toCharArray();
		buffer[0] = Character.toLowerCase(buffer[0]);
		return new String(buffer);
	}
	
	private Serializable castId(String id) {
		Serializable s = null;
		s = (Serializable) TypeUtils.castType(id, TypeUtils.getIdField(type).getType());
		return s;
	}
}
