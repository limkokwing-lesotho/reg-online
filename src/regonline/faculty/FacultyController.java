package regonline.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import regonline.Controller;


@WebServlet("/faculties/")
public class FacultyController extends Controller<Faculty> {
	private static final long serialVersionUID = 1L;

    public FacultyController() {
        super(Faculty.class);
    }

    @Override
    protected void createOrUpdate(Faculty faculty, HttpServletRequest request) {
		faculty.setName(request.getParameter("name"));
    }
	
}
