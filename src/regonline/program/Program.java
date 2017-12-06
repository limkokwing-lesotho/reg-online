package regonline.program;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import regonline.course.Course;

@Entity
public class Program {

	@Id
	private String code;
	private String name;
	@OneToMany
	private List<Course> courses;
	
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
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public void addCourse(Course course){
		if(courses == null){
			courses = new ArrayList<>();
		}
		courses.add(course);
	}
	
}
