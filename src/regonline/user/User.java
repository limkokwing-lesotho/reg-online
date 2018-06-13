package regonline.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import regonline.Model;

@Entity
public class User implements Model {

	@Id
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private boolean hasOTP;
	
	public User(){
		
	}
	
	public User(String username, String firstName, String lastName, String password) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getHasOTP() {
		return hasOTP;
	}

	public void setHasOTP(boolean hasOTP) {
		this.hasOTP = hasOTP;
	}

	public String getFullName(){
		return firstName +" "+ lastName;
	}

	@Override
	public void setId(Serializable id) {
		this.username = (String) id;
	}

	@Override
	public Serializable getId() {
		return username;
	}
	
}
