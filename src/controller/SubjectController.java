package controller;


import model.BazaPredmeta;
import model.Predmet;
import model.Profesor;
import view.PredmetiJTable;
import view.TabbedPane;

public class SubjectController {
	
private static SubjectController instance = null;

	
	public static SubjectController getInstance() {
		if(instance == null) {
			
			instance = new SubjectController();

		}
		
		return instance;
	}
	
	private SubjectController() {}

	  public void deleteSubject(int rowSelectedIndex) {
	    	String selectedRow = (String) PredmetiJTable.getInstance().getValueAt(rowSelectedIndex, 0);
	    	Predmet subject = BazaPredmeta.getInstance().getSubject(selectedRow);
			ProfessorController.getInstance().deleteSubject(subject);
			StudentController.getInstance().deleteSubject(subject);
			BazaPredmeta.getInstance().deleteSubject(subject.getCode());
			TabbedPane.getInstance().refreshSubjectTable();
			
	    }
	  
	  public boolean addSubject(String code, String name, int espb, int year, String semester, Profesor p) {
			
			if(BazaPredmeta.getInstance().checkIfSubjectExists(code) == null) {
				BazaPredmeta.getInstance().addSubject(code, name, espb, year, semester, p);
				TabbedPane.getInstance().refreshSubjectTable();
				return true;
			}
			
			
			return false;
		}
	  
	  public Predmet findSubject(int row) {
			String selectedRow = (String) PredmetiJTable.getInstance().getValueAt(row, 0);
			return BazaPredmeta.getInstance().getSubject(selectedRow);
		}
	  
	  public Predmet getSubject(String code) {
			return BazaPredmeta.getInstance().getSubject(code);
		}
	  
	  public void removeProfessor(int row) {
		  String selectedRow = (String) PredmetiJTable.getInstance().getValueAt(row, 0);
		  BazaPredmeta.getInstance().removeProfessor(selectedRow);
	  }
	  
	
}
