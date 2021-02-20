package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5631869953798967395L;



	public enum Status { B, S; }

	private String lastName;
	private String firstName;
	private LocalDate dateOfBirth;
	private String adress;
	private String phone;
	private String email;
	private String numIdx;
	private int year;
	private int currentYear;
	private double averageGrade;
	private Status studentStatus;
	private List<Ocena> passedExams = new ArrayList<Ocena>();
	private List<Predmet> otherExams = new ArrayList<Predmet>();
	
	public Student() {}

	public Student(String numIdx, String firstName, String lastName, int currentYear, Status studentStatus,
			double averageGrade) {
		super();
		this.numIdx = numIdx;
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentYear = currentYear;
		this.studentStatus = studentStatus;
		this.averageGrade = averageGrade;
	}

	public Student(String firstName, String lastName, LocalDate dateOfBirth, String adress, String phone, String email,
			String numIdx, int year, int currentYear, double averageGrade, Status studentStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.numIdx = numIdx;
		this.year = year;
		this.currentYear = currentYear;
		this.averageGrade = averageGrade;
		this.studentStatus = studentStatus;
	}
	
	public Student(String firstName, String lastName, String dateOfBirth, String adress, String phone, String email,
			String numIdx, int year, int currentYear, double averageGrade, Status studentStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.numIdx = numIdx;
		this.year = year;
		this.currentYear = currentYear;
		this.averageGrade = averageGrade;
		this.studentStatus = studentStatus;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		this.adress = adress;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNumIdx() {
		return numIdx;
	}



	public void setNumIdx(String numIdx) {
		this.numIdx = numIdx;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public int getCurrentYear() {
		return currentYear;
	}



	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}



	public double getAverageGrade() {
		return averageGrade;
	}



	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}



	public Status getStudentStatus() {
		return studentStatus;
	}



	public void setStudentStatus(Status studentStatus) {
		this.studentStatus = studentStatus;
	}



	public List<Ocena> getPassedExams() {
		return passedExams;
	}



	public void setPassedExams(List<Ocena> passedExams) {
		this.passedExams = passedExams;
	}



	public List<Predmet> getOtherExams() {
		return otherExams;
	}



	public void setOtherExams(List<Predmet> otherExams) {
		this.otherExams = otherExams;
	}

	
	
}
