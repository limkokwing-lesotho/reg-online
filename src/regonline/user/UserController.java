package regonline.user;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import regonline.Controller;


@WebServlet("/users/")
@SuppressWarnings("serial")
public class UserController extends Controller<User> {

	public UserController() {
        super(User.class);
    }
    
	@Override
	protected void create(User user, HttpServletRequest request) {
    	String password = BCrypt.hashpw("111111", BCrypt.gensalt());
    	user.setPassword(password);
    	user.setHasOTP(true);
	}
	
    @Override
    protected void createOrUpdate(User user, HttpServletRequest request) {
    	user.setFirstName(request.getParameter("firstName"));
    	user.setLastName(request.getParameter("lastName"));
    	String password = request.getParameter("password");
    	password = BCrypt.hashpw(password, BCrypt.gensalt());
    	user.setPassword(password);
    }
}
