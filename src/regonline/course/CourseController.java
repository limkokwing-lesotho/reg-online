package regonline.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import regonline.Controller;


@WebServlet("/courses/")
public class CourseController extends Controller<Course> {
	private static final long serialVersionUID = 1L;

    public CourseController() {
        super(Course.class);
    }

    @Override
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Course course = new Course();
		course.setCode(request.getParameter("code"));
		course.setName(request.getParameter("name"));
		course.setCredits(Double.parseDouble(request.getParameter("credits")));
		course.setType(Course.Type.valueOf(request.getParameter("type")));
		dao.save(course);
		all(request, response);
	}
	
	@Override
	protected void update(HttpServletRequest request, HttpServletResponse response, String id)
			throws ServletException, IOException {
		Course course = dao.get(id);
		course.setName(request.getParameter("name"));
		dao.update(course);
		all(request, response);
	}
}
