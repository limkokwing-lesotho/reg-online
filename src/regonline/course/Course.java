package regonline.course;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Course {

	public enum Type{
		Minor,
		Core,
		Major
	}
	
	@Id
	private String code;
	private String name;
	private Type type;
	private double credits;
	@OneToMany @JoinTable(name= "course_prerequisites")
	private List<Course> prerequisites;
	
	public Course(String code, String name, Type type, double credits) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
		this.credits = credits;
	}
	
	public Course(){
		
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
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}

	public List<Course> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
	
}
