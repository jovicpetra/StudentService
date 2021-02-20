package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.StudentController;
import model.BazaPredmeta;
import model.Predmet;
import model.Student;
import view.MainFrame;
import view.StudentJTable;

public class DodavanjePredmeta extends JDialog{
	
	public static DodavanjePredmeta instance = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5480373919104746396L;
	
	private JButton okButton, cancelButton;
	public DefaultListModel<String> model;
	public String row = "";
	private Student s;
	public JList<String> subjects;

	public DodavanjePredmeta() {
		instance = this;
		JDialog dodajPredmet = new JDialog();
		dodajPredmet.setTitle(MainFrame.getInstance().getResourceBundle().getString("dodajPred"));
		dodajPredmet.setPreferredSize(new Dimension(300, 500));
		dodajPredmet.setIconImage(new ImageIcon("images1"+File.separator+"add.png").getImage());
		dodajPredmet.setModal(true);
		
		JPanel centralPanel = new JPanel();
		BoxLayout centralBox = new BoxLayout(centralPanel, BoxLayout.Y_AXIS);
		centralPanel.setLayout(centralBox);
		
		subjects = new JList<>();
		this.s = StudentController.getInstance().findStudent(StudentJTable.getInstance().getSelectedRow());
		
		model = new DefaultListModel<>();
		for(Predmet p : BazaPredmeta.getInstance().getSubjects()) {
			if(!StudentController.getInstance().checkSubject(s, p)) {
				row = p.getCode() + " / " + p.getName() + " / " + p.getEspb() + " / " + p.getSemester();
				model.addElement(row);
			}
		}
		
		subjects.setModel(model);
		
		if(model.isEmpty()) {
			JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("dodavanjePredProf"), 
					MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
		}
		
		subjects.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(subjects.getSelectedIndex() != -1)
					okButton.setEnabled(true);
				else
					okButton.setEnabled(false);
			}
			
		});
		
		JScrollPane centralScroll = new JScrollPane(subjects);
		centralScroll.setPreferredSize(new Dimension(280, 400));
		centralScroll.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		centralPanel.add(centralScroll);
		
		JPanel bottomPanel = new JPanel();
		BoxLayout box = new BoxLayout(bottomPanel, BoxLayout.X_AXIS);
		bottomPanel.setLayout(box);
		
		okButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("dodaj"));
		okButton.setPreferredSize(new Dimension(120, 40));
		okButton.setEnabled(false);
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String p = subjects.getSelectedValue();
				String[] parts = p.split(" / ");
				Predmet pr = BazaPredmeta.getInstance().getSubject(parts[0]);
				s.getOtherExams().add(BazaPredmeta.getInstance().getSubject(parts[0]));
				pr.getDidntPassSubject().add(s);
				
				Object[] red = { pr.getCode(), pr.getName(), pr.getEspb(), 
						pr.getYear(), pr.getSemester() };
				((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).addRow(red);
				((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).fireTableDataChanged();
				dodajPredmet.setVisible(false);
			}
			
		});
		
		cancelButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("odustani"));
		cancelButton.setPreferredSize(new Dimension(120, 40));
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dodajPredmet.setVisible(false);
				
			}
			
		});
		
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(okButton);
		bottomPanel.add(Box.createHorizontalStrut(15));
		bottomPanel.add(cancelButton);
		bottomPanel.add(Box.createGlue());
		
		dodajPredmet.add(centralPanel, BorderLayout.NORTH);
		dodajPredmet.add(bottomPanel, BorderLayout.SOUTH);
		
		dodajPredmet.pack();
		dodajPredmet.setLocationRelativeTo(dodajPredmet.getParent());
		dodajPredmet.setResizable(false);
		if(!model.isEmpty())
			dodajPredmet.setVisible(true);
		else
			dodajPredmet.setVisible(false);
		
	}
	
}
