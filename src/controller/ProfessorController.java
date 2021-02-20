package controller;

import java.util.List;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;
import view.ProfesoriJTable;
import view.TabbedPane;

public class ProfessorController {
	
	private static ProfessorController instance = null;
	
	public static ProfessorController getInstance() {
		if(instance == null) {
			
			instance = new ProfessorController();

		}
		
		return instance;
	}
	
	public boolean addProfessor(Profesor professor) {
		if(!BazaProfesora.getInstance().checkIfProfessorExists(professor.getIdCard())) {
			
			BazaProfesora.getInstance().addProfessor(professor.getLastName(), professor.getFirstName(), professor.getDateOfBirth(), professor.getHomeAddress(),
				professor.getPhoneNum(), professor.getEmail(), professor.getOfficeAddress(), professor.getIdCard(), professor.getTitle(), professor.getPosition(),professor.getListOfSubjects());
				TabbedPane.getInstance().refreshProfessorTable();
				return true;
			}
		return false;
	}
	
	
	public List<Profesor> getProfessors() {
		return BazaProfesora.getInstance().getProfessors();
	}
	
	public Profesor getProfessor(int selectedRowIndex) {
		return BazaProfesora.getInstance().getProfessors().get(selectedRowIndex);
	}
	
	public void deleteSubject(Predmet p) {
		BazaProfesora.getInstance().deleteSubject(p);
	}
	
	
	public Profesor findProfessor(int row) {
		String selectedRow = (String) ProfesoriJTable.getInstance().getValueAt(row, 0);
		return BazaProfesora.getInstance().getProfesorFromList(selectedRow);
	}
	
	public void deleteProfessor(int row) {
		Profesor prof = this.findProfessor(row);
		BazaProfesora.getInstance().deleteProfessor(prof.getIdCard());
		TabbedPane.getInstance().refreshProfessorTable();
		BazaPredmeta.getInstance().deleteProfessorFromSubject(prof);
		TabbedPane.getInstance().refreshSubjectTable();
	}
	
	
}



