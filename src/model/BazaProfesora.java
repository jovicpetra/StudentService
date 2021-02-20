package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BazaProfesora {
	
	private static BazaProfesora instance = null;
	
	public static BazaProfesora getInstance() {
		if(instance == null) {
			instance = new BazaProfesora();
		}
		return instance;
	}
		
	private List<Profesor> professors;
	private List<String> columns;
	
	private BazaProfesora() {
	
		//initProfessors();

		this.columns = new ArrayList<String>();
		this.columns.add("BRLK");
		this.columns.add("IME");
		this.columns.add("PREZIME");
		this.columns.add("TITULA");
		this.columns.add("ZVANJE");

	}
	
	private void initProfessors() {
		this.professors = new ArrayList<Profesor>();
		List<Predmet> subjects = new ArrayList<Predmet>();
		Predmet p1 = new Predmet();
		subjects.add(p1);
		professors.add(new Profesor("Jovanovic", "Jovo", LocalDate.parse("22.02.1983", DateTimeFormatter.ofPattern("dd.MM.yyyy")) , "Dusanova 12, Irig", "055/440-333",
				"jovojovo@gmail.com", "Dusanoca 1, Irig", "123123", "Doktor", "Redovni profesor"));
		professors.add(new Profesor("Ivanovic", "Ivan", LocalDate.parse("22.02.1983", DateTimeFormatter.ofPattern("dd.MM.yyyy")) , "Dusanova 12, Irig", "055/440-333",
				"ivanivanovic@gmail.com", "Beogradska 1, Bijeljina", "111222", "Doktor", "Redovni profesor"));
		professors.add(new Profesor("Dusanovic", "Dusan", LocalDate.parse("22.02.1983", DateTimeFormatter.ofPattern("dd.MM.yyyy")) , "Dusanova 12, Irig", "055/440-333",
				"dusan@gmail.com", "Majevicka 1, Bijeljina", "777888", "Doktor", "Redovni profesor"));
	}
	

	public List<Profesor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Profesor> professors) {
		this.professors = professors;
	}
	


	public int getColumnCount() {
		return columns.size();
	}

	public String getColumnName(int index) {
		return this.columns.get(index);
	}
	

	public Profesor getRow(int rowIndex) {
		return this.professors.get(rowIndex);
	}
	
	public Profesor getProfesorFromList(String rowIndex) {
		Profesor ret = null;
		for(Profesor p : professors) {
			if(p.getIdCard().equals(rowIndex)) {
				return p;
			}
		}
		
		return ret;
	}

	public String getValueAt(int row, int column) {
		Profesor professor = this.professors.get(row);
		switch (column) {
		case 0:
			return professor.getIdCard();
		case 1:
			return professor.getFirstName();
		case 2:
			return professor.getLastName();
		case 3:
			return professor.getTitle();
		case 4:
			return professor.getPosition();
		default:
			return null;
		}
	}

	public void addProfessor(String lastName, String firstName, LocalDate dateOfBirth, String homeAddress, String phoneNum,
			String email, String officeAddress, String idCard, String title, String position,
			List<Predmet> listOfSubjects) {
		professors.add(new Profesor(lastName, firstName, dateOfBirth, homeAddress, phoneNum, email, officeAddress, idCard, title, position, listOfSubjects));
	}
	
	public boolean checkIfProfessorExists(String id) {
		for(Profesor p : professors) {
			if(p.getIdCard().equals(id))
				return true;
		}
		
		return false;
	}
	
	public void deleteSubject(Predmet p) {
		for(Profesor professor : professors) {
			List<Predmet> lp = professor.getListOfSubjects();
			for(Predmet subject : lp) {
				if(subject.getCode().equals(p.getCode())) {
					lp.remove(subject);
					professor.setListOfSubjects(lp);
					break;
				}
			}
		}
	}
	
	public void deleteProfessor(String id) {
		for(Profesor prof : professors) {
			if(prof.getIdCard().equals(id)) {
				professors.remove(prof);
				break;
			}
		}
	}
	

}







