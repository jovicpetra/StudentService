package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Profesor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -167552978826174007L;
	private String lastName;
	private String firstName;
	private LocalDate dateOfBirth;
	private String homeAddress;
	private String phoneNum;
	private String email;
	private String officeAddress;
	private String idCard;
	private String title; //titula
	private String position; //zvanje
	private List<Predmet> listOfSubjects = new ArrayList<Predmet>();
	
	public Profesor() {}
	
	
	
	public Profesor(String lastName, String firstName, LocalDate dateOfBirth, String homeAddress, String phoneNum,
			String email, String officeAddress, String idCard, String title, String position,
			List<Predmet> listOfSubjects) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
		this.phoneNum = phoneNum;
		this.email = email;
		this.officeAddress = officeAddress;
		this.idCard = idCard;
		this.title = title;
		this.position = position;
		this.listOfSubjects = listOfSubjects;
	}
	
	public Profesor(String lastName, String firstName, LocalDate dateOfBirth, String homeAddress, String phoneNum,
			String email, String officeAddress, String idCard, String title, String position) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
		this.phoneNum = phoneNum;
		this.email = email;
		this.officeAddress = officeAddress;
		this.idCard = idCard;
		this.title = title;
		this.position = position;
	}



	public Profesor(String lastName, String firstName, String dateOfBirth, String homeAddress, String phoneNum,
			String email, String officeAddress, String idCard, String title, String position,
			List<Predmet> listOfSubjects) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		this.homeAddress = homeAddress;
		this.phoneNum = phoneNum;
		this.email = email;
		this.officeAddress = officeAddress;
		this.idCard = idCard;
		this.title = title;
		this.position = position;
		this.listOfSubjects = listOfSubjects;
	}
	
	public Profesor(String firstName, String lastName,String title, String position) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.title = title;
		this.position = position;
	}
	
	public Profesor(String firstName, String lastName, String idCard, String title, String position) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.idCard = idCard;
		this.title = title;
		this.position = position;
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
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<Predmet> getListOfSubjects() {
		return listOfSubjects;
	}
	public void setListOfSubjects(List<Predmet> listOfSubjects) {
		this.listOfSubjects = listOfSubjects;
	}
	


}
