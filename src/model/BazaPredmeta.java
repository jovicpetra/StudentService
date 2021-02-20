package model;

import java.util.ArrayList;
import java.util.List;


public class BazaPredmeta {

	private static BazaPredmeta instance = null;
	
	public static BazaPredmeta getInstance() {
		if(instance == null) {
			instance = new BazaPredmeta();
		}
		return instance;
	}
		
	private List<Predmet> subjects;
	private List<String> columns;
	
	private BazaPredmeta() {
	
		//initSubjects();

		this.columns = new ArrayList<String>();
		this.columns.add("SIFRA");
		this.columns.add("NAZIV");
		this.columns.add("ESPB");
		this.columns.add("GODINA");
		this.columns.add("SEMESTAR");

	}
	
	private void initSubjects() {
		this.subjects = new ArrayList<Predmet>();
		subjects.add(new Predmet("P122", "ANALIZA 1", 9, 1,"Zimski"));
		subjects.add(new Predmet("P234", "ALGEBRA" ,9, 2,"Zimski"));
		subjects.add(new Predmet("P355", "FIZIKA", 9, 3,"Letnji"));
		subjects.add(new Predmet("P356", "ANALIZA 2", 9, 4,"Letnji"));
	}
	

	public List<Predmet> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Predmet> subjects) {
		this.subjects = subjects;
	}


	public int getColumnCount() {
		return columns.size();
	}

	public String getColumnName(int index) {
		return this.columns.get(index);
	}

	public Predmet getRow(int rowIndex) {
		return this.subjects.get(rowIndex);
	}
	
	public Predmet getSubject(String rowIndex) {
		Predmet ret = null;
		
		for(Predmet p : subjects) {
			if(p.getCode().equals(rowIndex))
				return p;
		}
		
		return ret;
	}


	public String getValueAt(int row, int column) {
		Predmet subject = this.subjects.get(row);
		switch (column) {
		case 0:
			return subject.getCode();
		case 1:
			return subject.getName();
		case 2:
			return Integer.toString(subject.getEspb());
		case 3:
			return Integer.toString(subject.getYear());
		case 4:
			return subject.getSemester();
		default:
			return null;
		}
	}
	
	public void deleteSubject(String code) {
		for (Predmet p : subjects) {
			if (p.getCode().equals(code)) {
				subjects.remove(p);
				break;
			}
		}
	}
	
	public void deleteStudentsFromSubject(Student s) {
		for(Predmet p : subjects) {
			p.getPassedSubject().remove(s);
			p.getDidntPassSubject().remove(s);
		}
	}
	
	public void deleteProfessorFromSubject(Profesor p) {
		for(Predmet pred : subjects) {
			if(pred.getProfessor() !=null) {
				if(pred.getProfessor().equals(p)) {
					pred.setProfessor(null);
				}
			}
		}
	}
	
	public void addSubject(String code, String name, int espb, int year, String semester, Profesor p) {
		this.subjects.add(new Predmet(code, name, espb, year, semester, p));
	}
	
	public Predmet checkIfSubjectExists(String ind) {
		for(Predmet s : subjects) {
			if(s.getCode().equals(ind))
				return s;
		}
		
		return null;
	}
	
	public void removeProfessor(String code) {
		for(Predmet s : subjects) {
			if(s.getCode().equals(code)) {
				s.getProfessor().getListOfSubjects().remove(s);
				s.setProfessor(null);
				break;
			}
		}
	}
}









