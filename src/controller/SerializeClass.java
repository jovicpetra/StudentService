package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;
import model.Predmet;
import model.Profesor;
import model.Student;

public class SerializeClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 537475563297057606L;
	private List<Predmet> subjects;
	private List<Student> students;
	private List<Profesor> professors;

	public static SerializeClass instance = null;

	public static SerializeClass getInstance() {
		if(instance == null) {
			
			instance = new SerializeClass();

		}
		
		return instance;
	}
	
	public SerializeClass() {
		
		subjects = new ArrayList<Predmet>();
		students = new ArrayList<Student>();
		professors = new ArrayList<Profesor>();

	}
	
	public void serialize() {
		File f = new File("objectstream.txt");
		
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(fos));
			
			subjects = BazaPredmeta.getInstance().getSubjects();
			students = BazaStudenata.getInstance().getStudents();
			professors = BazaProfesora.getInstance().getProfessors();
			
			os.writeObject(getInstance());
			
			os.close();
			fos.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public void deserialize() {
		
		File f = new File("objectstream.txt");
		
		try(FileInputStream fis = new FileInputStream(f);
				ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(fis))){
			
			SerializeClass serialize;
			
			serialize = (SerializeClass)oi.readObject();
			
			BazaPredmeta.getInstance().setSubjects(serialize.getSubjects());
			BazaStudenata.getInstance().setStudents(serialize.getStudents());
			BazaProfesora.getInstance().setProfessors(serialize.getProfessors());
			
			oi.close();
			fis.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Predmet> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Predmet> subjects) {
		this.subjects = subjects;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Profesor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Profesor> professors) {
		this.professors = professors;
	}
	
	
}
