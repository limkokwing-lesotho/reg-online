package regonline.faculty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Faculty {

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
	
}
