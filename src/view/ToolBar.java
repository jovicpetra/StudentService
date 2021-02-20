package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.SearchController;
import view.dialogs.AddProfessorDialog;
import view.dialogs.AddStudentDialog;
import view.dialogs.AddSubjectDialog;
import view.dialogs.DeleteProfessor;
import view.dialogs.DeleteStudent;
import view.dialogs.DeleteSubject;

public class ToolBar extends JToolBar {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4268951943328073987L;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	JButton btnSearch;
	public static JTextField searchField;

	public ToolBar() {
		
		super(SwingConstants.HORIZONTAL);
		btnAdd = new JButton();
		btnAdd.setToolTipText("Dodaj novi entitet");
		btnAdd.setIcon(new ImageIcon("images"+File.separator+"add.png"));
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dodavanjeEntiteta();
			}
			
		});
		add(btnAdd);

		addSeparator();

		btnEdit = new JButton();
		btnEdit.setToolTipText("Izmijeni entitet");
		btnEdit.setIcon(new ImageIcon("images"+File.separator+"edit.png"));
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				izmjenaEntiteta();
			}
		});
		
		add(btnEdit);

		addSeparator();

		btnDelete = new JButton();
		btnDelete.setToolTipText("Obriši entitet");
		btnDelete.setIcon(new ImageIcon("images"+File.separator+"delete.png"));


		btnDelete.setIcon(new ImageIcon("images"+File.separator+"delete.png"));
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				brisanjeEntiteta();
			}
			
		});
		
		btnDelete.setIcon(new ImageIcon("images"+File.separator+"delete.png"));
		add(btnDelete);
		
		addSeparator();
	
		searchField=new JTextField(30);
		searchField.setMaximumSize(new Dimension(70, 40));
		searchField.setToolTipText("Unesi kriterijum pretrage");
	  	
		btnSearch = new JButton();
		btnSearch.setToolTipText("Pretraži");
		btnSearch.setIcon(new ImageIcon("images"+File.separator+"search.png"));
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(TabbedPane.getInstance().getSelectedIndex() == 0) {
					SearchController.getInstance().searchStud();
				}
				if(TabbedPane.getInstance().getSelectedIndex() == 1) {
					SearchController.getInstance().searchProf();
				}
				
				if(TabbedPane.getInstance().getSelectedIndex() == 2) {
					SearchController.getInstance().searchSub();
				}		
			}
			
		});
		
		add(Box.createHorizontalGlue());
		add(searchField);
		add(btnSearch);
				
		setFloatable(true);

	}
	
	private Object dodavanjeEntiteta() {
		if (TabbedPane.getInstance().getSelectedIndex() == 0) {
			AddStudentDialog dialog = new AddStudentDialog(1);
		}
		if (TabbedPane.getInstance().getSelectedIndex() == 1) {
			AddProfessorDialog dialog = new AddProfessorDialog(1);
		}

		if (TabbedPane.getInstance().getSelectedIndex() == 2) {
			AddSubjectDialog subDialog = new AddSubjectDialog(1);
		}
		return listenerList;
	}
	
	private Object izmjenaEntiteta() {
		if (TabbedPane.getInstance().getSelectedIndex() == 0) {
			AddStudentDialog studentDialog;
			if(StudentJTable.getInstance().getSelectedRow() != -1)
				studentDialog = new AddStudentDialog(2);
			else {
				JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozStud"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
				
			}
		}
		if (TabbedPane.getInstance().getSelectedIndex() == 1) {
			AddProfessorDialog dialog;
			if(ProfesoriJTable.getInstance().getSelectedRow() != -1)
				dialog = new AddProfessorDialog(2);
			else {
				JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozProf"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
				
			}
		}

		if (TabbedPane.getInstance().getSelectedIndex() == 2) {
			AddSubjectDialog subDialog;
			if(PredmetiJTable.getInstance().getSelectedRow() != -1)
				subDialog = new AddSubjectDialog(2);
			else {
				JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozPred"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);	
			}
		}
		return listenerList;
	}
	
	private Object brisanjeEntiteta() {
		if (TabbedPane.getInstance().getSelectedIndex() == 0) {
			DeleteStudent delete;
			if(StudentJTable.getInstance().getSelectedRow() != -1)
				delete= new DeleteStudent();
			else {
				JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozStud"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);	
			}
		}
		if (TabbedPane.getInstance().getSelectedIndex() == 1) {
			DeleteProfessor deleteProf;
			if(ProfesoriJTable.getInstance().getSelectedRow() != -1)
				deleteProf = new DeleteProfessor();
			else {
				JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozProf"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);	
			}
		}
		if (TabbedPane.getInstance().getSelectedIndex() == 2) {
			if(PredmetiJTable.getInstance().getSelectedRow() != -1) {
				DeleteSubject deleteSubject = new DeleteSubject();}
			else {
				JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozPred"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);	
			}
		}
		return listenerList;
	}
	
	public void initComponents() {
		btnAdd.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("dodajTT"));
		btnEdit.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("izmeniTT"));
		btnDelete.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("obrisiTT"));
		btnSearch.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("pretraziTT"));
		searchField.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("searchfieldTT"));

	}

}
