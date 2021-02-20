package controller;


import model.BazaPredmeta;
import model.BazaStudenata;
import model.Ocena;
import model.Predmet;
import model.Student;
import view.StudentJTable;
import view.TabbedPane;

public class StudentController {

	public static StudentController instance = null;
	

	public static StudentController getInstance() {
		if(instance == null)
			instance = new StudentController();
		return instance;
	}
	
	public StudentController() {}
		
	public boolean addStudent(String firstName, String lastName, String birthday, String adress,
				String phone, String email, String numOfIndex, int year, int currentYear, double avgGrade, Student.Status status) {
			
			if(BazaStudenata.getInstance().checkIfStudentExists(numOfIndex) == null) {
				BazaStudenata.getInstance().addStudent(firstName, lastName, birthday, adress, phone, email, numOfIndex, year, currentYear, avgGrade, status);
				TabbedPane.getInstance().refreshStudentTable();
				return true;
			}
			
			
			return false;
		}
	
	public void deleteSubject(Predmet p) {
		BazaStudenata.getInstance().deleteSubject(p);
		TabbedPane.getInstance().refreshStudentTable();
	}
	
	public Student findStudent(int row) {
		String selectedRow = (String) StudentJTable.getInstance().getValueAt(row, 0);
		return BazaStudenata.getInstance().getStudentFromList(selectedRow);
	}
	
	public void deleteStudent(int row) {
		Student stud = findStudent(row);
		BazaStudenata.getInstance().deleteStudent(stud.getNumIdx());
		BazaStudenata.getInstance().deleteStudentWithGrade(stud);
		TabbedPane.getInstance().refreshStudentTable();
		BazaPredmeta.getInstance().deleteStudentsFromSubject(stud);
		TabbedPane.getInstance().refreshSubjectTable();
	}
	
	public boolean checkSubject(Student s, Predmet pr) {
		for(Ocena o : s.getPassedExams()) {
			if(o.getSubject().equals(pr))
				return true;
		}
		
		for(Predmet p : s.getOtherExams()) {
			if(p.equals(pr))
				return true;
		}
		
		if(s.getCurrentYear() < pr.getYear())
			return true;
		
		return false;
	}
	
	public void addGrade(Student s, Ocena o) {
		BazaStudenata.getInstance().addGradeToSTudent(s, o);
		TabbedPane.getInstance().refreshSubjectTable();
	}
	public void removeSubject(Student s, Predmet p) {
		BazaStudenata.getInstance().removeSubject(s, p);
		TabbedPane.getInstance().refreshSubjectTable();
	}
	
	
	
}














