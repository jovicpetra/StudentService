package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.ProfessorController;
import controller.SubjectController;
import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;
import view.MainFrame;
import view.ProfesoriJTable;
import view.TabbedPane;

public class AddSubjectToProfessor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1370886109034277609L;

	public static JButton btn1, btn2;
	
	public AddSubjectToProfessor() {
		
		this.setTitle(MainFrame.getInstance().getResourceBundle().getString("dodajPredmet"));
		this.setIconImage(new ImageIcon("images1"+File.separator+"add.png").getImage());
		this.setPreferredSize(new Dimension(300, 250));
		this.setModal(true);
		this.setResizable(false);
		
		JPanel finalPanel = new JPanel(new BorderLayout());

			
		JPanel upPanel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(MainFrame.getInstance().getResourceBundle().getString("predmetiLbl"));
		upPanel.add(label, BorderLayout.NORTH);
		
		JPanel centralPanel = new JPanel(new BorderLayout());

		DefaultListModel<String> model = new DefaultListModel<>();
		
		for (Predmet p : BazaPredmeta.getInstance().getSubjects()) {
			int a = 0;
			for(Profesor prof : BazaProfesora.getInstance().getProfessors()) {
				for(Predmet p2 : prof.getListOfSubjects()) {
					if(p == p2) {
						a = 1;
					}
				}
			}
			if(a == 0) {
				model.addElement(p.getCode() + "-" + p.getName());
			}
		}
		JList<String> lista = new JList<>(model);
		lista.setFixedCellWidth(this.getWidth()*3/4);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		lista.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(lista.getSelectedIndex() != -1)
					btn1.setEnabled(true);
				else
					btn1.setEnabled(false);
			}
			
		});
		
		JScrollPane centralScroll = new JScrollPane(lista);
		centralScroll.setPreferredSize(new Dimension(280, 400));
		centralScroll.setBorder(new EmptyBorder(10, 10, 10, 10));
		centralPanel.add(centralScroll, BorderLayout.CENTER);
		
		JPanel downPanel = new JPanel(new FlowLayout());
		btn1 = new JButton(MainFrame.getInstance().getResourceBundle().getString("potvrdi"));
		btn1.setEnabled(false);
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String p = lista.getSelectedValue();
				String[] parts = p.split("-");
				Predmet subject = BazaPredmeta.getInstance().checkIfSubjectExists(parts[0]);
				ProfessorController.getInstance().getProfessor(ProfesoriJTable.getInstance().getSelectedRow()).getListOfSubjects().add(subject);
				Profesor prof = ProfessorController.getInstance().findProfessor(ProfesoriJTable.getInstance().getSelectedRow());
				SubjectController.getInstance().getSubject(parts[0]).setProfessor(prof);
				TabbedPane.getInstance().refreshSubjectTable();
				Object[] red = {subject.getCode(), subject.getName(), subject.getYear(), subject.getSemester()};
				((DefaultTableModel) AddProfessorDialog.instance.getPredaje().table.getModel()).addRow(red);
				((DefaultTableModel) AddProfessorDialog.instance.getPredaje().table.getModel()).fireTableDataChanged();
				dispose();
			}});
		btn2 = new JButton(MainFrame.getInstance().getResourceBundle().getString("odustani"));
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}});
		downPanel.add(Box.createHorizontalGlue());
		downPanel.add(btn1, FlowLayout.LEFT);
		downPanel.add(btn2, FlowLayout.RIGHT);
		finalPanel.add(upPanel, BorderLayout.NORTH);
		finalPanel.add(centralPanel, BorderLayout.CENTER);
		finalPanel.add(downPanel, BorderLayout.SOUTH);
		this.add(finalPanel);
		this.pack();
		this.setLocationRelativeTo(this.getParent());
		this.setVisible(true);
	}
}