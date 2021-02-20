package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Predmet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7283932247025887699L;
	private String code;
	private String name;
	private String semester;
	private int year;
	private Profesor professor;
	private int espb;
	private List<Student> passedSubject = new ArrayList<Student>();
	private List<Student> didntPassSubject = new ArrayList<Student>();
	
	public Predmet() {}
	
	public Predmet(String code, String name, int espb, int year, String semester) {
		super();
		this.code = code;
		this.name = name;
		this.semester = semester;
		this.year = year;
		this.espb = espb;		
		
	}
	
	public Predmet(String code, String name, int espb, int year, String semester, Profesor p) {
		super();
		this.code = code;
		this.name = name;
		this.semester = semester;
		this.year = year;
		this.espb = espb;		
		this.professor = p;
	}
	
	public Predmet(String code, String name, String semester, int year, Profesor professor, int espb,
			List<Student> passedSubject, List<Student> didntPassSubject) {
		super();
		this.code = code;
		this.name = name;
		this.semester = semester;
		this.year = year;
		this.professor = professor;
		this.espb = espb;
		this.passedSubject = passedSubject;
		this.didntPassSubject = didntPassSubject;
		
		
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

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Profesor getProfessor() {
		return professor;
	}

	public void setProfessor(Profesor professor) {
		this.professor = professor;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public List<Student> getPassedSubject() {
		return passedSubject;
	}

	public void setPassedSubject(List<Student> passedSubject) {
		this.passedSubject = passedSubject;
	}

	public List<Student> getDidntPassSubject() {
		return didntPassSubject;
	}

	public void setDidntPassSubject(List<Student> didntPassSubject) {
		this.didntPassSubject = didntPassSubject;
	}
	
	
	
	
	
	
}