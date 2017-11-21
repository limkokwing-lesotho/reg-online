package regonline.faculty;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import regonline.datasource.DAO;


@WebServlet("/faculties/")
public class FacultyService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO<Faculty> dao;

    public FacultyService() {
        super();
        dao = new DAO<>(Faculty.class);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("faculties", dao.all());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Faculty faculty;
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		faculty = new Faculty(code, name);
		dao.save(faculty);
		request.setAttribute("faculties", dao.all());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
