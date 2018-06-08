package regonline.program;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import regonline.Model;
import regonline.course.Course;
import regonline.faculty.Faculty;

@Entity
public class Program implements Model {

	@Id
	private String code;
	private String name;
	@ManyToOne
	private Faculty faculty;
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
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
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
	@Override
	public void setId(Serializable id) {
		this.code = (String) id;
	}
	
	@Override
	public Serializable getId() {
		return code;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
