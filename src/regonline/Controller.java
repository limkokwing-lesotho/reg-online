package regonline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import regonline.course.Course;
import regonline.datasource.DAO;
import regonline.datasource.HibernateHelper;
import regonline.faculty.Faculty;

public abstract class Controller<T extends Model> extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected DAO<T> dao;
	private Class<T> type;
	
	
	public Controller(Class<T> type){
		dao = new DAO<>(type);
		this.type = type;
	}
	
	/**
	 * The method takes care of GET requests, if a request has a parameter named <b>d</b> 
	 * it will be treated as a DELETE request, and will be handled by
	 * {@link #delete(HttpServletRequest, HttpServletResponse, String)} method <br/>
	 * The <b>u</b>'s value should contain the id of a record that has to be deleted
	 * If the method does not have the <b>d</b> parameter, the request will be treated as a normal 
	 * GET request and the request will be handled by the
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
	 * it will be treated as a PUT request and therefore the request will be handled by 
	 * {@link #update(HttpServletRequest, HttpServletResponse, String)} method <br/>
	 * The <b>u</b>'s value should contain the id of a record that has to be updated
	 * If the method does not have the <b>u</b> parameter, the request will be treated as a normal 
	 * POST request and the request will be handled by the
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
	 * Used to show an update page, a GET request has to have a <b>e</b> parameter to indecate that
	 * it is a request to edit a resource, the <b>e</b> parameter indicates the id of the resource to 
	 * be edited, the method loads the <code>edit.jsp</code> page with an attribute
	 * whose name is similar to the resource's class name (the name is all lower case), the 
	 * attribute will contain the resource specified by the id which is the value of the <b>e</b> parameter.
	 * @see #doGet(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void edit(HttpServletRequest request, HttpServletResponse response, String resourceId) 
			throws ServletException, IOException{
		request.setAttribute(StringUtils.lowerCase(type.getSimpleName()), dao.get(resourceId));
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
	 * Handles POST requests when creating a resource<br/>
	 * Before forwarding the request the {@link #beforeCreate()} method is invoked
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
			obj.setId(request.getParameter("id"));
			create(obj, request);
			createOrUpdate(obj, request);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		dao.save(obj);
		index(request, response);
	}
	
	private void create(T obj, HttpServletRequest request) {
		
	}

	/**
	 * Used for updating a record, a POST request has to have a <b>u</b> parameter to be treated as
	 * an update, the <b>u</b> parameter indicates the id of the resource to be updated
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
	
	protected void update(T obj, HttpServletRequest request) {

	}

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
}
