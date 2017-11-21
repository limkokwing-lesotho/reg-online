package regonline.program;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Program {

	@Id
	private String code;
	private String name;
	
	public Program(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public Program(){
		
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
