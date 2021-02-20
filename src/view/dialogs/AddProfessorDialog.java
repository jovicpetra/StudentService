package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import controller.ProfessorController;
import controller.ProfessorFocusListener;
import model.BazaProfesora;
import model.Profesor;
import view.MainFrame;
import view.ProfesoriJTable;
import view.TabbedPane;

public class AddProfessorDialog extends JDialog{

	private static final long serialVersionUID = 1278610958004998272L;
	
	public static AddProfessorDialog instance = null;
	public static JTextField firstName;
	public static JTextField lastName;
	public static JTextField dateOfBirth;
	public static JTextField homeAddress;
	public static JTextField phoneNum;
	public static JTextField email;
	public static JTextField officeAddress;
	public static JTextField idCard;
	public static JComboBox<String> position, title;
	public static JButton btnOk;
	public Profesor professor;
	public SubjectList predaje;
	public SubjectList getPredaje() {
		return predaje;
	}
	
	public AddProfessorDialog(int mode) {
		
		instance = this;
		JDialog addProfessor = new JDialog();
		addProfessor.setTitle(MainFrame.getInstance().getResourceBundle().getString("dodajProf"));
		addProfessor.setPreferredSize(new Dimension(400, 410));
		addProfessor.setModal(true);
		addProfessor.setIconImage(new ImageIcon("images1"+File.separator+"add.png").getImage());
		addProfessor.setResizable(false);
			
		JPanel upPanel = new JPanel(new GridBagLayout());
		JPanel finalPanel = new JPanel(new BorderLayout());
		
		JLabel NameLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("ime"));
		firstName = new JTextField();
		firstName.setName("firstName");
		firstName.setToolTipText("'Petar'");
		firstName.addFocusListener(new ProfessorFocusListener());

		upPanel.add(NameLabel,constraintLbl(0, 0));
		upPanel.add(firstName,constraintTF(1, 0));
		
		JLabel LnameLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("prezime"));
		lastName = new JTextField();
		lastName.setName("lastName");
		lastName.setToolTipText("'Perić'");
		lastName.addFocusListener(new ProfessorFocusListener());

		upPanel.add(LnameLabel, constraintLbl(0, 1));
		upPanel.add(lastName, constraintTF(1, 1));
		
		JLabel DateOfBirthLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("datum"));
		dateOfBirth = new JTextField();
		dateOfBirth.setName("dateOfBirth");
		dateOfBirth.setToolTipText("'01.01.2001'");
		dateOfBirth.addFocusListener(new ProfessorFocusListener());

		upPanel.add(DateOfBirthLabel, constraintLbl(0, 2));
		upPanel.add(dateOfBirth, constraintTF(1, 2));
		
		JLabel homeAdressLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("adresa"));
		homeAddress = new JTextField();
		homeAddress.setName("homeAddress");
		homeAddress.setToolTipText("'Nemanjina 15, Beograd'");
		homeAddress.addFocusListener(new ProfessorFocusListener());
	
		upPanel.add(homeAdressLabel, constraintLbl(0, 3));
		upPanel.add(homeAddress, constraintTF(1, 3));
		
		JLabel phoneNumLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("broj"));
		phoneNum = new JTextField();
		phoneNum.setName("phoneNum");
		phoneNum.setToolTipText("'066/555-555'");
		phoneNum.addFocusListener(new ProfessorFocusListener());

		upPanel.add(phoneNumLabel, constraintLbl(0, 4));
		upPanel.add(phoneNum, constraintTF(1, 4));
		
		JLabel emailLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("email")); 
		email = new JTextField();
		email.setName("email");
		email.setToolTipText("'peraperic@gmail.com'");
		email.addFocusListener(new ProfessorFocusListener());

		upPanel.add(emailLabel, constraintLbl(0, 5));
		upPanel.add(email, constraintTF(1, 5));
		
		JLabel idCardLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("brLk"));
		idCard = new JTextField();
		idCard.setName("idCard");
		idCard.setToolTipText("'123456'");
		idCard.addFocusListener(new ProfessorFocusListener());

		upPanel.add(idCardLabel, constraintLbl(0, 6));
		upPanel.add(idCard, constraintTF(1, 6));
		
		
		JLabel officeAdressLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("kanc"));
		officeAddress = new JTextField();
		officeAddress.setName("officeAddress");
		officeAddress.setToolTipText("'Nemanjina 15, Beograd'");
		officeAddress.addFocusListener(new ProfessorFocusListener());

		upPanel.add(officeAdressLabel, constraintLbl(0, 7));
		upPanel.add(officeAddress, constraintTF(1, 7));
		
		
		String[] titles = { "Doktor profesor", "Doktor", "Master" };
		JLabel titleLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("titula"));
		title = new JComboBox<String>(titles);
		
		upPanel.add(titleLabel, constraintLbl(0, 8));
		upPanel.add(title,  constraintTF(1, 8));
		
		String[] positions = { "Asistent", "Saradnik u nastavi", "Redovni profesor", "Vanredni profesor", "Docent" };
		JLabel positionLabel = new JLabel(MainFrame.getInstance().getResourceBundle().getString("zvanje"));
		position = new JComboBox<String>(positions);
		
		upPanel.add(positionLabel, constraintLbl(0, 9));
		upPanel.add(position, constraintTF(1, 9));
		
		
		JPanel downPanel = new JPanel();
		
		btnOk = new JButton(MainFrame.getInstance().getResourceBundle().getString("potvrdi"));
		btnOk.setPreferredSize(new Dimension(90, 25));
		
		JButton btnCancel = new JButton(MainFrame.getInstance().getResourceBundle().getString("odustani"));
		btnCancel.setPreferredSize(new Dimension(97, 25));
		
		downPanel.add(btnCancel);
		downPanel.add(btnOk);

				
		finalPanel.add(upPanel, BorderLayout.NORTH);
		finalPanel.add(downPanel, BorderLayout.SOUTH);
		
		addProfessor.add(finalPanel);
		
		if(mode == 1) {
			
		btnOk.setEnabled(false);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String firstNameFieldValue = firstName.getText();
				String lastNameFieldValue = lastName.getText();
				String dateOfBirthFieldValue = dateOfBirth.getText();
				String homeAddressFieldValue = homeAddress.getText();
				String phoneNumFieldValue = phoneNum.getText();
				String emailFieldValue = email.getText();
				String officeAddressFieldValue = officeAddress.getText();
				String idCardFieldValue = idCard.getText();
				String titleToString = "";
				String positionToString = "";
				
				switch (title.getSelectedIndex()) {
				case 0:
					titleToString = "Doktor profesor";
					break;
				case 1:
					titleToString = "Doktor";
					break;
				case 2:
					titleToString = "Master";
				
				}
				
				switch (position.getSelectedIndex()) {
				case 0:
					positionToString = "Asistent";
					break;
				case 1:
					positionToString = "Saradnik u nastavi";
					break;
				case 2:
					positionToString = "Redovni profesor";
					break;
				case 3:
					positionToString = "Vanredni profesor";
					break;
				case 4:
					positionToString = "Docent";
				}
				
				
				Profesor professor = new Profesor(lastNameFieldValue, firstNameFieldValue, LocalDate.parse(dateOfBirthFieldValue, DateTimeFormatter.ofPattern("dd.MM.yyyy")), homeAddressFieldValue, phoneNumFieldValue,
						emailFieldValue, officeAddressFieldValue, idCardFieldValue, titleToString, positionToString );
				
				if(!ProfessorController.getInstance().addProfessor(professor)) {
					JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("greskaProf"), "GREŠKA", JOptionPane.WARNING_MESSAGE);
				} 
				else {
					addProfessor.dispose();
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addProfessor.dispose();
			}
		});
		}else {
			
			addProfessor.setTitle(MainFrame.getInstance().getResourceBundle().getString("izmeniProf"));
			addProfessor.setIconImage(new ImageIcon("images1"+File.separator+"edit.png").getImage());
			addProfessor.setPreferredSize(new Dimension(400, 480));
			predaje = new SubjectList();
			
			JTabbedPane tabs = new JTabbedPane();
			tabs.addTab(MainFrame.getInstance().getResourceBundle().getString("informacije"), null, finalPanel, MainFrame.getInstance().getResourceBundle().getString("informacije"));
			SubjectList subjects = new SubjectList();
			tabs.addTab(MainFrame.getInstance().getResourceBundle().getString("predaje"), null, subjects,MainFrame.getInstance().getResourceBundle().getString("predaje"));			
			addProfessor.add(tabs, BorderLayout.CENTER);
			
			btnOk.setEnabled(true);
			this.professor = ProfessorController.getInstance().findProfessor(ProfesoriJTable.getInstance().getSelectedRow());
			firstName.setText(professor.getFirstName());
			lastName.setText(professor.getLastName());
			dateOfBirth.setText(professor.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
			homeAddress.setText(professor.getHomeAddress());
			phoneNum.setText(professor.getPhoneNum());
			email.setText(professor.getEmail());
			idCard.setText(professor.getIdCard());
			officeAddress.setText(professor.getOfficeAddress());
			
			if (professor.getTitle().equals("Doktor profesor")) {
				title.setSelectedIndex(0);
			} else if (professor.getTitle().equals("Doktor")) {
				title.setSelectedIndex(1);
			} else {
				title.setSelectedIndex(2);
			} 
			
			if (professor.getPosition().equals("Asistent")) {
				position.setSelectedIndex(0);
			} else if (professor.getPosition().equals("Saradnik u nastavi")) {
				position.setSelectedIndex(1);
			} else if (professor.getPosition().equals("Redovni profesor")) {
				position.setSelectedIndex(2);
			} else if (professor.getPosition().equals("Vanredni profesor")) {
				position.setSelectedIndex(3);
			} else {
				position.setSelectedIndex(4);
			}
			
			btnOk.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					LocalDate d = LocalDate.parse(dateOfBirth.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
					
					String titleToString = "";
					String positionToString = "";
					
					switch (title.getSelectedIndex()) {
					case 0:
						titleToString = "Doktor profesor";
						break;
					case 1:
						titleToString = "Doktor";
						break;
					case 2:
						titleToString = "Master";
						break;
					
					}
					
					switch (position.getSelectedIndex()) {
					case 0:
						positionToString = "Asistent";
						break;
					case 1:
						positionToString = "Saradnik u nastavi";
						break;
					case 2:
						positionToString = "Redovni profesor";
						break;
					case 3:
						positionToString = "Vanredni profesor";
						break;
					case 4:
						positionToString = "Docent";
						break;
					}
					
					boolean checker = true;
					
					for(Profesor p : BazaProfesora.getInstance().getProfessors()) {
						if(!p.equals(professor)) {
							if(p.getIdCard().equals(idCard.getText()) || p.getEmail().equals(email.getText())) {
								JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("greskaProf"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.WARNING_MESSAGE);
								checker = false;
							}
						}
					}
					
					if(checker) {
						professor.setFirstName(firstName.getText());
						professor.setLastName(lastName.getText());
						professor.setDateOfBirth(d);
						professor.setHomeAddress(homeAddress.getText());
						professor.setPhoneNum(phoneNum.getText());
						professor.setEmail(email.getText());
						professor.setIdCard(idCard.getText());
						professor.setOfficeAddress(officeAddress.getText());
						professor.setTitle(titleToString);
						professor.setPosition(positionToString);
						}
					
					TabbedPane.getInstance().refreshProfessorTable();
					addProfessor.dispose();
				}
			});
			
			btnCancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					addProfessor.dispose();
					
				}
				
			});

		}
		
		addProfessor.pack();
		addProfessor.setLocationRelativeTo(MainFrame.getInstance());
		addProfessor.setVisible(true);

	}
		
	private GridBagConstraints constraintLbl(int x,int y) {
		GridBagConstraints ogr = new GridBagConstraints();
		
		
		ogr.gridx = x;
		ogr.gridy = y;
		ogr.gridwidth = 1;
		ogr.anchor = GridBagConstraints.WEST;
		ogr.insets = new Insets(10, 20, 0, 0);
		return ogr;
	}
	private GridBagConstraints constraintTF(int x,int y) {
		
		GridBagConstraints ogr = new GridBagConstraints();
		
		ogr.gridx = x;
		ogr.gridy = y;
		ogr.gridwidth = 3;
		ogr.weightx = 100;
		ogr.fill = GridBagConstraints.HORIZONTAL;
		ogr.insets = new Insets(10, 20, 0, 20);
		return ogr;
	}
}

