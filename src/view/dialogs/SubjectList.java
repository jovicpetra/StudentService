package view.dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProfessorController;
import model.BazaPredmeta;
import model.Predmet;
import model.Profesor;
import view.MainFrame;
import view.ProfesoriJTable;

public class SubjectList extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2765191070437932525L;
	
	public static JButton btn1, btn2;
	public static JTable table;
	public  DefaultTableModel model;
	private Predmet pr;
	private Profesor prof;

	public SubjectList() {
		setLayout(new BorderLayout());
		JPanel btnPanel = new JPanel(new GridBagLayout());
		JPanel tablePanel = new JPanel(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		
		btn1 = new JButton(MainFrame.getInstance().getResourceBundle().getString("btn1"));
		gbc.gridx = 0;
		gbc.gridy = 0;
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddSubjectToProfessor dilog = new AddSubjectToProfessor();
				
			}});
		btnPanel.add(btn1, gbc);
		btn2 = new JButton(MainFrame.getInstance().getResourceBundle().getString("btn2"));
		gbc.gridx = 1;
		gbc.gridy = 0;
		btnPanel.add(btn2, gbc);

		this.add(btnPanel, BorderLayout.NORTH);
		
		table = new JTable();
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		model = new DefaultTableModel();
		String[] columns = {MainFrame.getInstance().getResourceBundle().getString("sifra1"),
				MainFrame.getInstance().getResourceBundle().getString("naziv1"),
				MainFrame.getInstance().getResourceBundle().getString("godina1"),
				MainFrame.getInstance().getResourceBundle().getString("semestar1")};
		model.setColumnIdentifiers(columns);

		this.prof = ProfessorController.getInstance().findProfessor(ProfesoriJTable.getInstance().getSelectedRow());
		
		for(Predmet p : prof.getListOfSubjects()) {
			Object[] row = { p.getCode(), p.getName(), p.getYear(), p.getSemester() };
			model.addRow(row);
		}
		
		table.setModel(model);
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					String selectedRow = (String) table.getValueAt(table.getSelectedRow(), 0);
					pr = (BazaPredmeta.getInstance().getSubject(selectedRow));
					String [] options = new String[2];
					options[0] =  MainFrame.getInstance().getResourceBundle().getString("yesOption");
					options[1] =  MainFrame.getInstance().getResourceBundle().getString("noOption");
					int ret = JOptionPane.showOptionDialog(null,  MainFrame.getInstance().getResourceBundle().getString("ukloniPredmet2"), MainFrame.getInstance().getResourceBundle().getString("ukloniPredmet1"), JOptionPane.YES_NO_OPTION, 
							JOptionPane.PLAIN_MESSAGE, null, options, null);
					if (ret != JOptionPane.YES_OPTION) {
	
					}else {
						if(pr != null) {
							prof.getListOfSubjects().remove(pr);
							pr.setProfessor(null);
							model.removeRow(table.getSelectedRow());
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozPred"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
			
		});
		
		JScrollPane central = new JScrollPane(table);
		central.setBorder(new EmptyBorder(10, 10, 10, 10));
		tablePanel.add(central, BorderLayout.CENTER);
		this.add(tablePanel, BorderLayout.CENTER);
	}
}


















