package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SubjectController;
import controller.SubjectFocusListener;
import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;
import view.MainFrame;
import view.PredmetiJTable;
import view.TabbedPane;

public class AddSubjectDialog extends JDialog{
	
	private JPanel subName, subCode, semester, year, espb, professor;
	private JLabel labName, labCode, labSemester, labYear, labESPB, labProf;
	public static JTextField textName, textCode, textESPB, textProf;
	public static JButton okButton, cancelButton, addProf, removeProf;
	public static JComboBox<String> semesterComboBox, yearsComboBox;
	private Predmet sub;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4035411439816864535L;
	
	public AddSubjectDialog(int mode) {
		
	 	JDialog addSubject = new JDialog();
		addSubject.setIconImage(new ImageIcon("images1"+File.separator+"adds.png").getImage());
		addSubject.setTitle(MainFrame.getInstance().getResourceBundle().getString("dodajPred"));
		addSubject.setPreferredSize(new Dimension(420, 320));
		addSubject.setModal(true);
		
		JPanel centralPanel = new JPanel();
		BoxLayout centralBox = new BoxLayout(centralPanel, BoxLayout.Y_AXIS);
		centralPanel.setLayout(centralBox);
		Dimension dim = new Dimension(250, 25);
		
		subName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labName = new JLabel(MainFrame.getInstance().getResourceBundle().getString("naziv"));
		labName.setPreferredSize(new Dimension(130, 25));
		textName = new JTextField();
		textName.setName("subName");
		textName.setToolTipText("npr. 'Analiza 1'");
		textName.setPreferredSize(dim);
		subName.add(Box.createGlue());
		subName.add(labName);
		subName.add(textName);

		subCode = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labCode = new JLabel(MainFrame.getInstance().getResourceBundle().getString("sifra"));
		labCode.setPreferredSize(new Dimension(130, 25));
		textCode = new JTextField();
		textCode.setName("code");
		textCode.setToolTipText("npr. 'E2A1'");
		textCode.setPreferredSize(dim);
		subCode.add(Box.createGlue());
		subCode.add(labCode);
		subCode.add(textCode);
		
		espb = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labESPB = new JLabel(MainFrame.getInstance().getResourceBundle().getString("espb"));
		labESPB.setPreferredSize(new Dimension(130, 25));
		textESPB = new JTextField();
		textESPB.setName("espb");
		textESPB.setToolTipText("npr. '8'");
		textESPB.setPreferredSize(dim);
		espb.add(Box.createGlue());
		espb.add(labESPB);
		espb.add(textESPB);

		semester = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labSemester = new JLabel(MainFrame.getInstance().getResourceBundle().getString("sem"));
		labSemester.setPreferredSize(new Dimension(130, 25));
		String[] sem = { "Zimski", "Letnji" };
		semesterComboBox = new JComboBox<String>(sem);
		semesterComboBox.setPreferredSize(dim);
		semester.add(Box.createGlue());
		semester.add(labSemester);
		semester.add(semesterComboBox);

		year = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labYear = new JLabel(MainFrame.getInstance().getResourceBundle().getString("god"));
		labYear.setPreferredSize(new Dimension(130, 25));
		String[] years = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
		yearsComboBox = new JComboBox<String>(years);
		yearsComboBox.setPreferredSize(dim);
		year.add(Box.createGlue());
		year.add(labYear);
		year.add(yearsComboBox);
		
		professor = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labProf = new JLabel(MainFrame.getInstance().getResourceBundle().getString("profesor"));
		labProf.setPreferredSize(new Dimension(130, 25));
		textProf = new JTextField();
		textProf.setName("prof");
		textProf.setPreferredSize(new Dimension(140, 25));
		textProf.setEditable(false);
		addProf = new JButton("+");
		addProf.setPreferredSize(new Dimension(50, 25));
		removeProf = new JButton("-");
		removeProf.setPreferredSize(new Dimension(50, 25));
		
		professor.add(Box.createGlue());
		professor.add(labProf);
		professor.add(textProf);
		professor.add(addProf);
		professor.add(removeProf);
		
		textName.addFocusListener(new SubjectFocusListener());
		textCode.addFocusListener(new SubjectFocusListener());
		textESPB.addFocusListener(new SubjectFocusListener());
		
		centralPanel.add(subCode);
		centralPanel.add(subName);
		centralPanel.add(espb);
		centralPanel.add(year);
		centralPanel.add(semester);
		centralPanel.add(professor);
		
		centralPanel.add(Box.createHorizontalStrut(25));
		addSubject.add(centralPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel();
		BoxLayout box = new BoxLayout(bottomPanel, BoxLayout.X_AXIS);
		bottomPanel.setLayout(box);
		
		okButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("potvrdi"));
		okButton.setPreferredSize(new Dimension(120, 40));
		okButton.setEnabled(false);

		if(mode == 1) {
			addProf.setEnabled(true);
			removeProf.setEnabled(false);
			
			addProf.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!AddSubjectDialog.textCode.getText().isEmpty() && !AddSubjectDialog.textName.getText().isEmpty() &&
							!AddSubjectDialog.textESPB.getText().isEmpty()) {
						OdaberiProfesora diag = new OdaberiProfesora(1);
						addProf.setEnabled(false);
						removeProf.setEnabled(true);
					}else {
						System.out.println("Unesite podatke o predmetu!");
					}
				}
					
			});
			
			removeProf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String [] options = new String[2];
					options[0] = MainFrame.getInstance().getResourceBundle().getString("yesOption");
					options[1] = MainFrame.getInstance().getResourceBundle().getString("yesOption");
					int ret = JOptionPane.showOptionDialog(null, MainFrame.getInstance().getResourceBundle().getString("ukloniProfUpoz"), 
							MainFrame.getInstance().getResourceBundle().getString("ukloniProfNaslov"), JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, options, null);
				
					if (ret != JOptionPane.YES_OPTION) {
						dispose();
					}else {
						textProf.setText("");
						addProf.setEnabled(true);
						removeProf.setEnabled(false);
					}
				}});
	
			
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int year = yearsComboBox.getSelectedIndex() + 1; 
					int espb = Integer.valueOf(textESPB.getText());
					
					String semestar;
					if (semesterComboBox.getSelectedIndex() == 0) {
						semestar = "Zimski";
					}else {
						semestar = "Letnji";
					}
					
					Profesor prof = null;
					String[] parts = null;
					if(!textProf.getText().equals("")) {
						parts = textProf.getText().split(" ");
						for(Profesor p : BazaProfesora.getInstance().getProfessors()) {
							if(p.getIdCard().equals(parts[3])) {
								prof = p;
								break;
							}
						}
					}
					
					if(!SubjectController.getInstance().addSubject(textCode.getText(), textName.getText(), espb, year, semestar, prof)) {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodajPredUpoz"),
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.WARNING_MESSAGE);
					} 
					else {
						
						if(prof != null) {
							Predmet pr = new Predmet(textCode.getText(), textName.getText(), espb, year, semestar, prof);
							prof.getListOfSubjects().add(pr);
							pr.setProfessor(prof);
						}
						
						addSubject.dispose();
					}
					
				}
				
			});
		}
		else {
			this.sub = SubjectController.getInstance().findSubject(PredmetiJTable.getInstance().getSelectedRow());
			addProf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					OdaberiProfesora diag = new OdaberiProfesora(2);
					addProf.setEnabled(false);
					removeProf.setEnabled(true);
				}
				
			});
			
			removeProf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String [] options = new String[2];
					options[0] =  MainFrame.getInstance().getResourceBundle().getString("yesOption");
					options[1] =  MainFrame.getInstance().getResourceBundle().getString("noOption");
					int ret = JOptionPane.showOptionDialog(null, MainFrame.getInstance().getResourceBundle().getString("ukloniProfUpoz"), 
							MainFrame.getInstance().getResourceBundle().getString("ukloniProfNaslov"), JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, options, null);
				
					if (ret != JOptionPane.YES_OPTION) {
						dispose();
					}else {
						if(sub.getProfessor() != null)
							SubjectController.getInstance().removeProfessor(PredmetiJTable.getInstance().getSelectedRow());
						textProf.setText("");
						addProf.setEnabled(true);
						removeProf.setEnabled(false);
					}
				}});
			
			addSubject.setTitle(MainFrame.getInstance().getResourceBundle().getString("izmeniPred"));
			okButton.setEnabled(true);
			
			textCode.setText(sub.getCode());
			textName.setText(sub.getName());
			textESPB.setText(String.valueOf(sub.getEspb()));
			yearsComboBox.setSelectedIndex(sub.getYear() - 1);
			
			if(sub.getSemester().equals("Zimski")) {
				semesterComboBox.setSelectedIndex(0);
			}else {
				semesterComboBox.setSelectedIndex(1);
			}
			
			if(sub.getProfessor() == null) {
				textProf.setText("");
			}
			else {
				textProf.setText(sub.getProfessor().getTitle() + " " + sub.getProfessor().getFirstName() + " " + sub.getProfessor().getLastName() +
						" " + sub.getProfessor().getIdCard());
			}
			
			if(!textProf.getText().isEmpty()) {
				addProf.setEnabled(false);
				removeProf.setEnabled(true);
			}
			else {
				addProf.setEnabled(true);
				removeProf.setEnabled(false);
			}
			
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int year = yearsComboBox.getSelectedIndex() + 1; 
					int espb = Integer.valueOf(textESPB.getText());
					
					String semestar;
					if (semesterComboBox.getSelectedIndex() == 0) {
						semestar = "Zimski";
					}else {
						semestar = "Letnji";
					}
					
					Profesor prof = null;
					if(!textProf.getText().isEmpty()) {
						String[] parts = textProf.getText().split(" ");
						for(Profesor p : BazaProfesora.getInstance().getProfessors()) {
							if(p.getIdCard().equals(parts[3])) {
								prof = p;
								break;
							}
						}
					}
					else {
						prof = null;
					}
					
					if(prof != null) {
						sub.setProfessor(prof);
						prof.getListOfSubjects().remove(sub);
						prof.getListOfSubjects().add(sub);
					}
					
					boolean checker = true;
					for(Predmet p : BazaPredmeta.getInstance().getSubjects()) {
						if(!p.equals(sub)) {
							if(p.getCode().equals(textCode.getText())) {
								JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodajPredUpoz"),
										MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.WARNING_MESSAGE);
								checker = false;
							}
						}
					}
					
					
					if(checker) {
						sub.setCode(textCode.getText());
						sub.setName(textName.getText());
						sub.setEspb(espb);
						sub.setYear(year);
						sub.setSemester(semestar);
						sub.setProfessor(prof);
						
						TabbedPane.getInstance().refreshSubjectTable();
						addSubject.dispose();
					}
					
				}
				
			});
			
		}
		
		cancelButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("odustani"));
		cancelButton.setPreferredSize(new Dimension(120, 40));
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addSubject.dispose();
				
			}
			
		});
		
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(cancelButton);
		bottomPanel.add(Box.createHorizontalStrut(15));
		bottomPanel.add(okButton);
		bottomPanel.add(Box.createGlue());
	
		addSubject.add(bottomPanel, BorderLayout.SOUTH);
		addSubject.pack();
		addSubject.setLocationRelativeTo(MainFrame.getInstance());
		addSubject.setResizable(false);
		addSubject.setVisible(true);
	
	}
	
}
