package regonline.course;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;


import regonline.Controller;
import regonline.datasource.HibernateHelper;
import regonline.faculty.Faculty;


@WebServlet("/courses/")
public class CourseController extends Controller<Course> {
	private static final long serialVersionUID = 1L;

    public CourseController() {
        super(Course.class);
    }
    
    @Override
    protected void createOrUpdate(Course course, HttpServletRequest request) {
		course.setName(request.getParameter("name"));
		course.setCredits(Integer.parseInt(request.getParameter("credits")));
		course.setType(Course.Type.valueOf(request.getParameter("type")));
		String facultyId = request.getParameter("faculty");
		Faculty faculty = HibernateHelper.getSession().get(Faculty.class, facultyId);
		course.setFaculty(faculty);
    }
}
