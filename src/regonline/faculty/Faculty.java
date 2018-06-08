package regonline.faculty;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import regonline.Model;

@Entity
public class Faculty implements Model {

	@Id
	private String code;
	private String name;
	
	public Faculty(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public Faculty(){
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public void setId(Serializable id) {
		this.code = (String) id;
	}
	@Override
	public Serializable getId() {
		return code;
	}
}
