package regonline.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import regonline.Model;

@Entity
public class User implements Model {

	@Id
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private boolean hasOTP;
	
	@ElementCollection
	@CollectionTable(name="roles", joinColumns=@JoinColumn(name="user_id"))
	@Column(name="role")
	private List<String> roles;
	
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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

	public void addRole(String role) {
		if(roles == null){
			roles = new ArrayList<>();
		}
		roles.add(role);
	}
	
}
