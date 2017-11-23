package regonline;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GenericServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * The method takes care of GET requests, if a request has a parameter named <b>d</b> 
	 * it will be treated as a DELETE request, and will be handled by
	 * {@link #delete(HttpServletRequest, HttpServletResponse, String)} method <br/>
	 * The <b>u</b>'s value should contain the id of a record that has to be deleted
	 * If the method does not have the <b>d</b> parameter, the request will be treated as a normal 
	 * GET request and the request will be handled by the
	 * {@link #get(HttpServletRequest, HttpServletResponse)} method
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String deleteId = request.getParameter("d");
		if(deleteId != null){
			delete(request, response, deleteId);
		}
		else{
			get(request, response);
		}
	}

	/**
	 * The method takes care of POST requests, if a request has a parameter named <b>u</b>
	 * it will be treated as a PUT request and therefore the request will be handled by 
	 * {@link #put(HttpServletRequest, HttpServletResponse, String)} method <br/>
	 * The <b>u</b>'s value should contain the id of a record that has to be updated
	 * If the method does not have the <b>u</b> parameter, the request will be treated as a normal 
	 * POST request and the request will be handled by the
	 * {@link #post(HttpServletRequest, HttpServletResponse)} method
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String updateId = request.getParameter("u");
		if(updateId != null){
			put(request, response, updateId);
		}
		else{
			post(request, response);
		}
	}
	
	/**
	 * Handles GET requests
	 * @see #doGet(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * Handles POST requests
	 * @see #doPost(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * Handles PUT requests (used for updating a record), which are technically POST requests
	 * @see #doPost(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @param id the id of an record to be updated
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void put(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException;
	
	/**
	 * Handles DELETE requests which are technically GET requests
	 * @see #doGet(HttpServletRequest, HttpServletResponse)
	 * @param request
	 * @param response
	 * @param id the id of an record to be deleted
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void delete(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException;

}
