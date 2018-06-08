package regonline.program;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import regonline.Controller;
import regonline.datasource.HibernateHelper;
import regonline.faculty.Faculty;


@WebServlet("/programs/")
public class ProgramController extends Controller<Program> {
	private static final long serialVersionUID = 1L;

    public ProgramController() {
        super(Program.class);
    }

    @Override
    protected void createOrUpdate(Program program, HttpServletRequest request) {
    	program.setName(request.getParameter("name"));
		String facultyId = request.getParameter("faculty");
		Faculty faculty = HibernateHelper.getSession().get(Faculty.class, facultyId);
		program.setFaculty(faculty);
    }
	
}
