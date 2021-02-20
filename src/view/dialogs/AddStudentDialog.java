package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import controller.StudentController;
import controller.StudentFocusListener;
import model.BazaStudenata;
import model.Student;
import view.MainFrame;
import view.StudentJTable;
import view.TabbedPane;

public class AddStudentDialog extends JDialog {
	
	public static AddStudentDialog instance = null;

	public static JPanel firstName, lastName, dateOfBirth, adress, phoneNum, email, numOfIndex, year, currentYear, studentStatus, avgGrade,centralPanel;
	private JLabel labFirstName, labLastName, labBirthday, labAdress, labPhoneNum, labEmail, labNumOfIndex, labYear, labCurrYear, labStatus, labAvg;
	public static JTextField textFName, textLName, textDate, textAdress, textPhone, textEmail, textIndex, textYear, textAvg;
	public static JComboBox<String> yearsComboBox, statusComboBox;
	public static JButton okButton;
	public Student stud;
	public NepolozeniIspiti nepolozeni;
	public NepolozeniIspiti getNepolozeni() {
		return nepolozeni;
	}
	public PolozeniIspiti polozeni;
	public PolozeniIspiti getPolozeni() {
		return polozeni;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045717492832954333L;
	
	public AddStudentDialog(int mode) { //mode = 1 ADD, mode = 2 EDIT
		instance = this;
		JDialog addStudent = new JDialog();
		addStudent.setIconImage(new ImageIcon("images1"+File.separator+"add.png").getImage());
		addStudent.setTitle(MainFrame.getInstance().getResourceBundle().getString("dodajStud"));
		addStudent.setPreferredSize(new Dimension(400, 410));
		addStudent.setModal(true);
		
		JPanel addStudentPanel = new JPanel();
		addStudentPanel.setLayout(new BorderLayout());
		
		centralPanel = new JPanel();
		BoxLayout centralBox = new BoxLayout(centralPanel, BoxLayout.Y_AXIS);
		centralPanel.setLayout(centralBox);
		Dimension dim = new Dimension(180, 20);
		
		firstName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labFirstName = new JLabel(MainFrame.getInstance().getResourceBundle().getString("ime"));
		labFirstName.setPreferredSize(dim);
		textFName = new JTextField();
		textFName.setName("firstName");
		textFName.setToolTipText("'Petar'");
		textFName.setPreferredSize(dim);
		firstName.add(Box.createGlue());
		firstName.add(labFirstName);
		firstName.add(textFName);

		lastName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labLastName = new JLabel(MainFrame.getInstance().getResourceBundle().getString("prezime"));
		labLastName.setPreferredSize(dim);
		textLName = new JTextField();
		textLName.setName("lastName");
		textLName.setToolTipText("'Petrović'");
		textLName.setPreferredSize(dim);
		lastName.add(Box.createGlue());
		lastName.add(labLastName);
		lastName.add(textLName);

		dateOfBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labBirthday = new JLabel(MainFrame.getInstance().getResourceBundle().getString("datum"));
		labBirthday.setPreferredSize(dim);
		textDate = new JTextField();
		textDate.setName("birthday");
		textDate.setToolTipText("'04.07.1999'");
		textDate.setPreferredSize(dim);
		dateOfBirth.add(Box.createGlue());
		dateOfBirth.add(labBirthday);
		dateOfBirth.add(textDate);

		adress = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labAdress = new JLabel(MainFrame.getInstance().getResourceBundle().getString("adresa"));
		labAdress.setPreferredSize(dim);
		textAdress = new JTextField();
		textAdress.setName("adress");
		textAdress.setToolTipText("'Stražilovska 25, Novi Sad'");
		textAdress.setPreferredSize(dim);
		adress.add(Box.createGlue());
		adress.add(labAdress);
		adress.add(textAdress);
		
		phoneNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labPhoneNum = new JLabel(MainFrame.getInstance().getResourceBundle().getString("broj"));
		labPhoneNum.setPreferredSize(dim);
		textPhone = new JTextField();
		textPhone.setName("phone");
		textPhone.setToolTipText("'065/555-555'");
		textPhone.setPreferredSize(dim);
		phoneNum.add(Box.createGlue());
		phoneNum.add(labPhoneNum);
		phoneNum.add(textPhone);

		email = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labEmail = new JLabel(MainFrame.getInstance().getResourceBundle().getString("email"));
		labEmail.setPreferredSize(dim);
		textEmail = new JTextField();
		textEmail.setName("email");
		textEmail.setToolTipText("'nesto@gmail.com'");
		textEmail.setPreferredSize(dim);
		email.add(Box.createGlue());
		email.add(labEmail);
		email.add(textEmail);

		numOfIndex = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labNumOfIndex = new JLabel(MainFrame.getInstance().getResourceBundle().getString("brInd"));
		labNumOfIndex.setPreferredSize(dim);
		textIndex = new JTextField();
		textIndex.setName("index");
		textIndex.setToolTipText("'ra-5-2018'");
		textIndex.setPreferredSize(dim);
		numOfIndex.add(Box.createGlue());
		numOfIndex.add(labNumOfIndex);
		numOfIndex.add(textIndex);

		year = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labYear = new JLabel(MainFrame.getInstance().getResourceBundle().getString("godina"));
		labYear.setPreferredSize(dim);
		textYear = new JTextField();
		textYear.setName("year");
		textYear.setToolTipText("'2018'");
		textYear.setPreferredSize(dim);
		year.add(Box.createGlue());
		year.add(labYear);
		year.add(textYear);
		
		/*avgGrade = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labAvg = new JLabel("Prosjek*");
		labAvg.setPreferredSize(dim);
		textAvg = new JTextField();
		textAvg.setName("average");
		textAvg.setToolTipText("npr. '9.58'");
		textAvg.setPreferredSize(dim);
		avgGrade.add(Box.createGlue());
		avgGrade.add(labAvg);
		avgGrade.add(textAvg);*/
		
		currentYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labCurrYear = new JLabel(MainFrame.getInstance().getResourceBundle().getString("trenutna"));
		labCurrYear.setPreferredSize(dim);
		String[] years = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
		yearsComboBox = new JComboBox<String>(years);
		yearsComboBox.setPreferredSize(dim);
		currentYear.add(Box.createGlue());
		currentYear.add(labCurrYear);
		currentYear.add(yearsComboBox);
		
		studentStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labStatus = new JLabel(MainFrame.getInstance().getResourceBundle().getString("finans"));
		labStatus.setPreferredSize(dim);
		String[] status = { "Budžet", "Samofinansiranje"};
		statusComboBox = new JComboBox<String>(status);
		statusComboBox.setPreferredSize(dim);
		studentStatus.add(Box.createGlue());
		studentStatus.add(labStatus);
		studentStatus.add(statusComboBox);
		
		textFName.addFocusListener(new StudentFocusListener());
		textLName.addFocusListener(new StudentFocusListener());
		textDate.addFocusListener(new StudentFocusListener());
		textAdress.addFocusListener(new StudentFocusListener());
		textPhone.addFocusListener(new StudentFocusListener());
		textEmail.addFocusListener(new StudentFocusListener());
		textIndex.addFocusListener(new StudentFocusListener());
		textYear.addFocusListener(new StudentFocusListener());
		//textAvg.addFocusListener(new StudentFocusListener());
		
		centralPanel.add(firstName);
		centralPanel.add(lastName);
		centralPanel.add(dateOfBirth);
		centralPanel.add(adress);
		centralPanel.add(phoneNum);
		centralPanel.add(email);
		centralPanel.add(numOfIndex);
		centralPanel.add(year);
		//centralPanel.add(avgGrade);
		centralPanel.add(currentYear);
		centralPanel.add(studentStatus);
		
		centralPanel.add(Box.createHorizontalStrut(25));
		addStudentPanel.add(centralPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel();
		BoxLayout box = new BoxLayout(bottomPanel, BoxLayout.X_AXIS);
		bottomPanel.setLayout(box);
		
		okButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("potvrdi"));
		okButton.setPreferredSize(new Dimension(120, 40));
		JButton cancelButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("odustani"));
		cancelButton.setPreferredSize(new Dimension(120, 40));
		
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(cancelButton);
		bottomPanel.add(Box.createHorizontalStrut(15));
		bottomPanel.add(okButton);
		bottomPanel.add(Box.createGlue());
		
		addStudentPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		addStudent.add(addStudentPanel);
		
		if(mode == 1) {
			okButton.setEnabled(false);
			
			okButton.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					int currYear = yearsComboBox.getSelectedIndex() + 1; 
					double avgGrade = 0;
					int year = Integer.valueOf(textYear.getText());
					
					Student.Status status;
					if (statusComboBox.getSelectedIndex() == 0) {
						status = Student.Status.B;
					}else {
						status = Student.Status.S;
					}
					
					String[] parts = textDate.getText().split("\\.");
					String[] indeks = textIndex.getText().split("-");
					
					if(textYear.getText().compareTo(parts[2]) <= 0) {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodajStudUpoz1"),
								MainFrame.getInstance().getResourceBundle().getString("greska"), 
								JOptionPane.WARNING_MESSAGE);
					}
					else if(textYear.getText().compareTo(indeks[2]) != 0) {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodajStudUpoz2"),
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.WARNING_MESSAGE);
					}
					else if(!StudentController.getInstance().addStudent(textFName.getText(), textLName.getText(), textDate.getText(), textAdress.getText(), textPhone.getText(),
							textEmail.getText(), textIndex.getText(), year, currYear, avgGrade, status)) {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodajStudUpoz3"),
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.WARNING_MESSAGE);
					} 
					else {
						addStudent.dispose();
					}
					
				}
				
			});

			cancelButton.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					addStudent.dispose();
					
				}
				
			});
		} else {
			addStudent.setTitle(MainFrame.getInstance().getResourceBundle().getString("izmeniStud"));
			addStudent.setIconImage(new ImageIcon("images1"+File.separator+"edit.png").getImage());
			addStudent.setPreferredSize(new Dimension(400, 480));
			
			okButton.setEnabled(true);
			this.stud = StudentController.getInstance().findStudent(StudentJTable.getInstance().getSelectedRow());
			
			textFName.setText(stud.getFirstName());
			textLName.setText(stud.getLastName());
			textDate.setText(stud.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
			textAdress.setText(stud.getAdress());
			textPhone.setText(stud.getPhone());
			textEmail.setText(stud.getEmail());
			textIndex.setText(stud.getNumIdx());
			textYear.setText(String.valueOf(stud.getYear()));
			//textAvg.setText(String.valueOf(stud.getAverageGrade()));
			yearsComboBox.setSelectedIndex(stud.getCurrentYear() - 1);
			if(stud.getStudentStatus() == Student.Status.B)
				statusComboBox.setSelectedIndex(0);
			else
				statusComboBox.setSelectedIndex(1);
			
			JTabbedPane tabs = new JTabbedPane();
			tabs.addTab(MainFrame.getInstance().getResourceBundle().getString("informacije"), 
					null, addStudentPanel, 
					MainFrame.getInstance().getResourceBundle().getString("informacije"));
			polozeni = new PolozeniIspiti();
			tabs.addTab(MainFrame.getInstance().getResourceBundle().getString("polozeni"), 
					null, polozeni,
					MainFrame.getInstance().getResourceBundle().getString("polozeni"));
			nepolozeni = new NepolozeniIspiti();
			tabs.addTab(MainFrame.getInstance().getResourceBundle().getString("nepolozeni"), 
					null, nepolozeni,
					MainFrame.getInstance().getResourceBundle().getString("nepolozeni"));
			
			addStudent.add(tabs, BorderLayout.CENTER);
			
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int currYear = yearsComboBox.getSelectedIndex() + 1; 
					double avgGrade = 0;
					int year = Integer.valueOf(textYear.getText());
					LocalDate d = LocalDate.parse(textDate.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
					
					Student.Status status;
					if (statusComboBox.getSelectedIndex() == 0) {
						status = Student.Status.B;
					}else {
						status = Student.Status.S;
					}
					
					String[] parts = textDate.getText().split("\\.");
					String[] indeks = textIndex.getText().split("-");
					
					if(textYear.getText().compareTo(parts[2]) <= 0) {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodajStudUpoz1"),
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.WARNING_MESSAGE);
					}
					else if(textYear.getText().compareTo(indeks[2]) != 0) {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodajStudUpoz2"),
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.WARNING_MESSAGE);
					}
					else {
						boolean checker = true;
						for(Student s : BazaStudenata.getInstance().getStudents()) {
							if(!s.equals(stud)) {
								if(s.getNumIdx().equals(textIndex.getText()) || s.getEmail().equals(textEmail.getText())) {
									JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodajStudUpoz3"),
											MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.WARNING_MESSAGE);
									checker = false;
								}
							}
						}
						
						if(checker) {
							stud.setFirstName(textFName.getText());
							stud.setLastName(textLName.getText());
							stud.setDateOfBirth(d);
							stud.setAdress(textAdress.getText());
							stud.setPhone(textPhone.getText());
							stud.setEmail(textEmail.getText());
							stud.setNumIdx(textIndex.getText());
							stud.setYear(year);
							//stud.setAverageGrade(avgGrade);
							stud.setCurrentYear(currYear);
							stud.setStudentStatus(status);
							
							TabbedPane.getInstance().refreshStudentTable();
							addStudent.dispose();
						}
					}
				}
			});
			
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					addStudent.dispose();
					
				}
				
			});
		}
		
		addStudent.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				TabbedPane.getInstance().refreshStudentTable();
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		addStudent.pack();
		addStudent.setLocationRelativeTo(MainFrame.getInstance());
		addStudent.setResizable(false);
		addStudent.setVisible(true);
	
	}
}
