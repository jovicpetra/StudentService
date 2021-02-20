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

import controller.SubjectController;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;
import view.MainFrame;
import view.PredmetiJTable;
import view.TabbedPane;

public class OdaberiProfesora extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2773786609660994353L;
	
	private JButton okButton, cancelButton;
	public JList<String> professors;
	public DefaultListModel<String> model;
	public String row = "";
	private Predmet pr;
	
	public OdaberiProfesora(int mode) {
		
		JDialog odaberiProfesora = new JDialog();
		odaberiProfesora.setTitle(MainFrame.getInstance().getResourceBundle().getString("odaberiProf"));
		odaberiProfesora.setPreferredSize(new Dimension(300, 500));
		odaberiProfesora.setIconImage(new ImageIcon("images1"+File.separator+"add.png").getImage());
		odaberiProfesora.setModal(true);
		
		JPanel centralPanel = new JPanel();
		BoxLayout centralBox = new BoxLayout(centralPanel, BoxLayout.Y_AXIS);
		centralPanel.setLayout(centralBox);
		
		professors = new JList<>();
		
		model = new DefaultListModel<>();
		for(Profesor p : BazaProfesora.getInstance().getProfessors()) {
			row = p.getTitle() + " " + p.getFirstName() + " " + p.getLastName() + " " + p.getIdCard();
			model.addElement(row);
		}
		
		professors.setModel(model);
		
		professors.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(professors.getSelectedIndex() != -1)
					okButton.setEnabled(true);
				else
					okButton.setEnabled(false);
			}
			
		});
		
		JScrollPane centralScroll = new JScrollPane(professors);
		centralScroll.setPreferredSize(new Dimension(280, 400));
		centralScroll.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		centralPanel.add(centralScroll);
		
		JPanel bottomPanel = new JPanel();
		BoxLayout box = new BoxLayout(bottomPanel, BoxLayout.X_AXIS);
		bottomPanel.setLayout(box);
		
		okButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("potvrdi"));
		okButton.setPreferredSize(new Dimension(120, 40));
		okButton.setEnabled(false);
		
		//String[] parts = row.split(" ");
		
		if(mode == 1) {

			int year = AddSubjectDialog.yearsComboBox.getSelectedIndex() + 1; 
			int espb = Integer.valueOf(AddSubjectDialog.textESPB.getText());
			
			String semestar;
			if (AddSubjectDialog.semesterComboBox.getSelectedIndex() == 0) {
				semestar = "Zimski";
			}else {
				semestar = "Letnji";
			}
			
			this.pr = new Predmet(AddSubjectDialog.textCode.getText(), AddSubjectDialog.textName.getText(), espb, year, semestar);
		}
		else {
			this.pr = SubjectController.getInstance().findSubject(PredmetiJTable.getInstance().getSelectedRow());
		}
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String p = professors.getSelectedValue();
				AddSubjectDialog.textProf.setText(p);
				odaberiProfesora.setVisible(false);
			
			}
		});
		
		cancelButton = new JButton(MainFrame.getInstance().getResourceBundle().getString("odustani"));
		cancelButton.setPreferredSize(new Dimension(120, 40));
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				odaberiProfesora.setVisible(false);
				
			}
			
		});
		
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(cancelButton);
		bottomPanel.add(Box.createHorizontalStrut(15));
		bottomPanel.add(okButton);
		bottomPanel.add(Box.createGlue());
		
		odaberiProfesora.add(centralPanel, BorderLayout.NORTH);
		odaberiProfesora.add(bottomPanel, BorderLayout.SOUTH);
		
		odaberiProfesora.pack();
		odaberiProfesora.setLocationRelativeTo(odaberiProfesora.getParent());
		odaberiProfesora.setResizable(false);
		odaberiProfesora.setVisible(true);
		
	}

}
