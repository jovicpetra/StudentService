package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Ocena implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 602942751098967204L;
	private Student student; 
	private Predmet subject;
	private int value;
	private LocalDate examDate;
	
	public Ocena(Student student, Predmet subject, int value, LocalDate examDate) {
		super();
		this.student = student;
		this.subject = subject;
		this.value = value;
		this.examDate = examDate;
	}

	public Ocena() {
		super();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Predmet getSubject() {
		return subject;
	}

	public void setSubject(Predmet subject) {
		this.subject = subject;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}	
}

