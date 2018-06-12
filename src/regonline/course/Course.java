package regonline.course;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import regonline.Model;
import regonline.datasource.DAO;
import regonline.faculty.Faculty;

@Entity
public class Course implements Model {

	public enum Type{
		Minor,
		Core,
		Major
	}
	
	@Id
	private String code;
	private String name;
	private Type type;
	private int credits;
	@ManyToMany
	private List<Course> prerequisites;
	@ManyToOne
	private Faculty faculty;
	
	public Course(String code, String name, Type type, int credits) {
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
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}

	public List<Course> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
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
