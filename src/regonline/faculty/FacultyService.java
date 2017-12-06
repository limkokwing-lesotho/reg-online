package regonline.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import regonline.Controller;


@WebServlet("/faculties/")
public class FacultyService extends Controller<Faculty> {
	private static final long serialVersionUID = 1L;

    public FacultyService() {
        super(Faculty.class);
    }

    @Override
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Faculty faculty = new Faculty();
		faculty.setCode(request.getParameter("code"));
		faculty.setName(request.getParameter("name"));
		dao.save(faculty);
		all(request, response);
	}
	
	@Override
	protected void update(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		Faculty faculty = dao.get(id);
		faculty.setCode(request.getParameter("code"));
		faculty.setName(request.getParameter("name"));
		dao.save(faculty);
		all(request, response);
	}
	
    @Override
	protected void delete(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException{
		Faculty faculty = dao.load(id);
		if(faculty != null){
			dao.delete(faculty);
		}
		all(request, response);
	}

}
