package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import view.dialogs.AboutDialog;
import view.dialogs.AddProfessorDialog;
import view.dialogs.AddStudentDialog;
import view.dialogs.AddSubjectDialog;
import view.dialogs.DeleteProfessor;
import view.dialogs.DeleteStudent;
import view.dialogs.DeleteSubject;
import view.dialogs.HelpDialog;

public class MyMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4273777597552585506L;
	private JMenu mnuAdministracija, file, edit, help;
	JMenuItem menuItemNew, menuItemClose, menuItemEdit, menuItemDelete, menuItemHelp, menuItemAbout;
	private JCheckBoxMenuItem mniSrpski;
	private JCheckBoxMenuItem mniEngleski;
	public MyMenuBar() {
			
		file = new JMenu("Fajl");
		file.setMnemonic(KeyEvent.VK_F);
		menuItemNew = new JMenuItem(" Novi     ");
		menuItemNew.setFont(new Font ("Arial", Font.PLAIN, 12));
		menuItemNew.setIcon(new ImageIcon("./images1"+File.separator+"add.png"));
		menuItemNew.setMnemonic(KeyEvent.VK_N);
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItemNew.setToolTipText("Dodavanje novog entiteta u sistem");
		
		ActionListener addNewDialog = new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(TabbedPane.getInstance().getSelectedIndex() == 0) {
					AddStudentDialog studentDialog = new AddStudentDialog(1);
				}
				if(TabbedPane.getInstance().getSelectedIndex() == 1) {
					AddProfessorDialog professorDialog = new AddProfessorDialog(1);
				}
				if(TabbedPane.getInstance().getSelectedIndex() == 2) {
					AddSubjectDialog subDialog = new AddSubjectDialog(1);
				}
			}
		};
	
		menuItemNew.addActionListener(addNewDialog);
		
		menuItemClose = new JMenuItem(" Zatvori     ");
		menuItemClose.setFont(new Font ("Arial", Font.PLAIN, 12));
		menuItemClose.setIcon(new ImageIcon("./images1"+File.separator+"close.png"));
		menuItemClose.setMnemonic(KeyEvent.VK_C);
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuItemClose.setToolTipText("Zatvaranje aplikacije");
		
		ActionListener addCloseDialog = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String [] options = new String[2];
				options[0] = MainFrame.getInstance().getResourceBundle().getString("yesOption");
				options[1] = MainFrame.getInstance().getResourceBundle().getString("noOption");
				int ret = JOptionPane.showOptionDialog(null, MainFrame.getInstance().getResourceBundle().getString("zatvaranjeAplikacije"), MainFrame.getInstance().getResourceBundle().getString("zatvaranjeAplikacijeNaslov"), JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, options, null);
			
				if (ret != JOptionPane.YES_OPTION) {
					MainFrame.getInstance().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				}else {
					System.exit(0);
				}
			
			}
		};
	
		menuItemClose.addActionListener(addCloseDialog);
		
		file.add(menuItemNew);
		file.addSeparator();
		file.add(menuItemClose);
		
		
		edit = new JMenu("Izmeni");
		edit.setMnemonic(KeyEvent.VK_E);
		menuItemEdit = new JMenuItem(" Izmeni     ");
		menuItemEdit.setFont(new Font ("Arial", Font.PLAIN, 12));
		menuItemEdit.setIcon(new ImageIcon("./images1"+File.separator+"edit.png"));
		menuItemEdit.setMnemonic(KeyEvent.VK_I);
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuItemEdit.setToolTipText("Izmena postojećeg entiteta");
		
		ActionListener addEditDialog = new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(TabbedPane.getInstance().getSelectedIndex() == 0) {
					AddStudentDialog studentDialog;
					if(StudentJTable.getInstance().getSelectedRow() != -1)
						studentDialog = new AddStudentDialog(2);
					else {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozStud"), 
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
						
					}
				}
				if(TabbedPane.getInstance().getSelectedIndex() == 1) {
					AddProfessorDialog profDialog;
					if(ProfesoriJTable.getInstance().getSelectedRow() != -1)
						profDialog = new AddProfessorDialog(2);
					else {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozProf"), 
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
						
					}
				}
				if(TabbedPane.getInstance().getSelectedIndex() == 2) {
					AddSubjectDialog subDialog;
					if(PredmetiJTable.getInstance().getSelectedRow() != -1)
						subDialog = new AddSubjectDialog(2);
					else {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozPred"), 
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
						
					}
				}
			}
		};
	
		menuItemEdit.addActionListener(addEditDialog);
		
		menuItemDelete = new JMenuItem(" Izbriši     ");
		menuItemDelete.setFont(new Font ("Arial", Font.PLAIN, 12));
		menuItemDelete.setIcon(new ImageIcon("./images1"+File.separator+"delete.png"));
		menuItemDelete.setMnemonic(KeyEvent.VK_D);
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuItemDelete.setToolTipText("Brisanje postojećeg entiteta");
		
		ActionListener addDeleteDialog = new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(TabbedPane.getInstance().getSelectedIndex() == 0) {
					DeleteStudent delete;
					if(StudentJTable.getInstance().getSelectedRow() != -1)
						delete= new DeleteStudent();
					else {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozStud"), 
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
					}
				}
				if(TabbedPane.getInstance().getSelectedIndex() == 1) {
					DeleteProfessor deleteProf;
					if(ProfesoriJTable.getInstance().getSelectedRow() != -1)
						deleteProf = new DeleteProfessor();
					else {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozProf"), 
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
					}
				}
				if(TabbedPane.getInstance().getSelectedIndex() == 2) {
					DeleteSubject deleteSubject;
					if(ProfesoriJTable.getInstance().getSelectedRow() != -1)
						deleteSubject = new DeleteSubject();
					else {
						JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozPred"), 
								MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		};
	
		menuItemDelete.addActionListener(addDeleteDialog);
		
		edit.add(menuItemEdit);
		edit.addSeparator();
		edit.add(menuItemDelete);
		
		
		help = new JMenu("Pomoć");
		help.setMnemonic(KeyEvent.VK_H);
		menuItemHelp = new JMenuItem(" Pomoć     ");
		menuItemHelp.setFont(new Font ("Arial", Font.PLAIN, 12));
		menuItemHelp.setIcon(new ImageIcon("./images1"+File.separator+"help.png"));
		menuItemHelp.setMnemonic(KeyEvent.VK_P);
		menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		menuItemHelp.setToolTipText("Pomoć oko funkcionisanja aplikacije");
		
		ActionListener addHelpDialog = new ActionListener() {
		
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpDialog newDialog = new HelpDialog();
			}
		};
	
		menuItemHelp.addActionListener(addHelpDialog);
		
		menuItemAbout = new JMenuItem(" Info     ");
		menuItemAbout.setFont(new Font ("Arial", Font.PLAIN, 12));
		menuItemAbout.setIcon(new ImageIcon("./images1"+File.separator+"about.png"));
		menuItemAbout.setMnemonic(KeyEvent.VK_A);
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		menuItemAbout.setToolTipText("Informacije");
		
		ActionListener addAboutDialog = new ActionListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutDialog newAboutDialog = new AboutDialog();
			}
		};
		
		menuItemAbout.addActionListener(addAboutDialog);
		
		help.add(menuItemHelp);
		help.addSeparator();
		help.add(menuItemAbout);
		
		add(file);
		add(edit);
		add(help);
		
		mnuAdministracija = new JMenu("Jezik");
		mnuAdministracija.setMnemonic(KeyEvent.VK_L);
		add(mnuAdministracija);

		mniSrpski = new JCheckBoxMenuItem("Srpski");
		mniSrpski.setSelected(true);
		mniSrpski.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mniEngleski.setSelected(false);
				Locale.setDefault(new Locale("sr", "RS"));
				MainFrame.getInstance().changeLanguage();

			}
		});
		mnuAdministracija.add(mniSrpski);

		mniEngleski = new JCheckBoxMenuItem("Engleski");
		mniEngleski.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mniSrpski.setSelected(false);
				Locale.setDefault(new Locale("en", "US"));
				MainFrame.getInstance().changeLanguage();
			}
		});
		mnuAdministracija.add(mniEngleski);	
	}	
	
	public void initComponents() {
		file.setText(MainFrame.getInstance().getResourceBundle().getString("fajl"));
		edit.setText(MainFrame.getInstance().getResourceBundle().getString("izmena"));
		help.setText(MainFrame.getInstance().getResourceBundle().getString("pomoc"));
		mnuAdministracija.setText(MainFrame.getInstance().getResourceBundle().getString("jezik"));
		menuItemNew.setText(MainFrame.getInstance().getResourceBundle().getString("novi"));
		menuItemClose.setText(MainFrame.getInstance().getResourceBundle().getString("zatvori"));
		menuItemEdit.setText(MainFrame.getInstance().getResourceBundle().getString("izmena"));
		menuItemDelete.setText(MainFrame.getInstance().getResourceBundle().getString("izbrisi"));
		menuItemHelp.setText(MainFrame.getInstance().getResourceBundle().getString("pomoc"));
		menuItemAbout.setText(MainFrame.getInstance().getResourceBundle().getString("info"));
		mniSrpski.setText(MainFrame.getInstance().getResourceBundle().getString("Serbian"));
		mniEngleski.setText(MainFrame.getInstance().getResourceBundle().getString("English"));
		menuItemNew.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("dodajTT"));
		menuItemClose.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("zatvoriTT"));
		menuItemEdit.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("izmeniTT"));
		menuItemDelete.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("obrisiTT"));
		menuItemHelp.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("pomocTT"));
		menuItemAbout.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("infoTT"));

	}
	
}
