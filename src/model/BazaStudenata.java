package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.Student.Status;

public class BazaStudenata {

	private static BazaStudenata instance = null;
	public static BazaStudenata getInstance() {
		if(instance == null) {
			instance = new BazaStudenata();
		}
		
		return instance;
	}
	
	private List<Student> students;
	private List<String> columns;
	
	public BazaStudenata() {
		
		this.columns = new ArrayList<String>();

		this.columns.add("INDEKS");
		this.columns.add("IME");
		this.columns.add("PREZIME");
		this.columns.add("GODINA STUDIJA");
		this.columns.add("STATUS");
		this.columns.add("PROSJEK");
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public int getColumnCount() {
		return columns.size();
	}
	
	public String getColumnName(int index) {
		return this.columns.get(index);
	}
	
	public Student getStudentFromList(String rowIdx) {
		Student ret = null;
		for(Student s : students) {
			if(s.getNumIdx().equals(rowIdx)) {
				return s;
			}
		}
		
		return ret;
	}
	
	public Object getValueAt(int row, int column) {
		Student student = this.students.get(row);
		switch (column) {
		case 0:
			return student.getNumIdx();
		case 1:
			return student.getFirstName();
		case 2:
			return student.getLastName();
		case 3:
			return String.valueOf(student.getCurrentYear());
		case 4:
			if(student.getStudentStatus() == Status.B)
				return "Budžet";
			else
				return "Samofinansiranje";
		case 5:
			if(student.getAverageGrade() != 0)
				return student.getAverageGrade();
			else
				return 0.0;
			
		default:
			return null;
		}
	}
	
	public void addStudent(String firstName, String lastName, String birthday, String adress,
			String phone, String email, String numOfIndex, int year, int currentYear, double avgGrade, Student.Status status) {
		this.students.add(new Student(firstName, lastName, birthday, adress, phone, email, numOfIndex, year, currentYear, avgGrade, status));
	}
	
	public Student checkIfStudentExists(String ind) {
		for(Student s : students) {
			if(s.getNumIdx().equals(ind))
				return s;
		}
		
		return null;
	}
	
	public void deleteSubject(Predmet p) {
		for(Student s : students) {
			List<Predmet> lp1 = s.getOtherExams();
			List<Ocena> lp2 = s.getPassedExams();
			for(Predmet subject : lp1) {
				if(subject == p) {
					lp1.remove(subject);
					s.setOtherExams(lp1);
					break;
				}
			}
			for(Ocena o : lp2) {
				if(o.getSubject() == p) {
					lp2.remove(o);
					s.setPassedExams(lp2);
				}
			}
		}
	}
	
	public void deleteStudent(String ind) {
		for(Student s : students) {
			if(s.getNumIdx().equals(ind)) {
				students.remove(s);
				break;
			}
		}
	}
	
	public void deleteStudentWithGrade(Student s) {
		for(Student stud : students) {
			List<Ocena> lista = stud.getPassedExams();
			for(Ocena o : lista) {
				if(o.getStudent().equals(s)) {
					lista.remove(o);
					stud.setPassedExams(lista);
				}
			}
		}
	}
	
	public void addGradeToSTudent(Student s, Ocena o) {
		for(Student stud : students) {
			if(stud.getNumIdx().equals(s.getNumIdx())) {
				stud.getPassedExams().add(o);
				o.getSubject().getPassedSubject().add(stud);
			}
		}
	}
	public void removeSubject(Student s, Predmet p) {
	for(Student stud : students) {
		if(stud.getNumIdx().equals(s.getNumIdx())) {
			for(Predmet pr : stud.getOtherExams()) {
				if(pr.getCode().equals(p.getCode())) {
					stud.getOtherExams().remove(pr);
					break;
				}
			}
		}
		
	}
	}
}












