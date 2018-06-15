package regonline.user;

import org.mindrot.jbcrypt.BCrypt;

import regonline.datasource.DAO;

public class UserDAO extends DAO<User> {

	public UserDAO() {
		super(User.class);
	}

	public User authenticate(String username, String password){
		User user = get(username);
		if(user != null && BCrypt.checkpw(password, user.getPassword())){
			return user;
		}
		return null;
	}
}
