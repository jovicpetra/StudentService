package view.dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.StudentController;
import controller.SubjectController;
import model.BazaPredmeta;
import model.Ocena;
import model.Predmet;
import model.Student;
import view.MainFrame;
import view.PredmetiJTable;
import view.StudentJTable;

public class NepolozeniIspiti extends JPanel{
	
	private static NepolozeniIspiti instance = null;
	public static NepolozeniIspiti getInstance() {
		if(instance == null) {
			instance = new NepolozeniIspiti();
		}
		
		return instance;
	}
	
	private Predmet pr;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9147042369832877750L;
	
	public static JButton btn1, btn2, btn3;
	public JTable nepolozeniJTable;
	public NepolozeniIspiti() {
		setLayout(new BorderLayout());
		JPanel btnPanel = new JPanel(new GridBagLayout());
		JPanel tablePanel = new JPanel(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		
		btn1 = new JButton(MainFrame.getInstance().getResourceBundle().getString("dodaj"));
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePredmeta dodavanje = new DodavanjePredmeta();
			}
			
		});
		
		btnPanel.add(btn1, gbc);
		btn2 = new JButton(MainFrame.getInstance().getResourceBundle().getString("ukloni"));
		gbc.gridx = 1;
		gbc.gridy = 0;
		btnPanel.add(btn2, gbc);
		btn3 = new JButton(MainFrame.getInstance().getResourceBundle().getString("polaganje"));
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int row = nepolozeniJTable.getSelectedRow();
				if(row != -1) {
					Predmet p = SubjectController.getInstance().getSubject( (String) nepolozeniJTable.getValueAt(row, 0));
					UnosOcjene dialog = new UnosOcjene(p);
				}else {
					JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozPred"),MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
				}
			}});
		gbc.gridx = 2;
		gbc.gridy = 0;
		btnPanel.add(btn3, gbc);
		this.add(btnPanel, BorderLayout.NORTH);
		
		nepolozeniJTable = new JTable();
		nepolozeniJTable.setRowSelectionAllowed(true);
		nepolozeniJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nepolozeniJTable.getTableHeader().setReorderingAllowed(false);
		
		DefaultTableModel model = new DefaultTableModel();
		String[] columns = {MainFrame.getInstance().getResourceBundle().getString("sifra1"),
				MainFrame.getInstance().getResourceBundle().getString("naziv1"), "ESPB",
				MainFrame.getInstance().getResourceBundle().getString("godina1"), 
				MainFrame.getInstance().getResourceBundle().getString("semestar1")};
		model.setColumnIdentifiers(columns);

		Student s = StudentController.getInstance().findStudent(StudentJTable.getInstance().getSelectedRow());		
		for(Predmet p : s.getOtherExams()) {
			Object[] row = { p.getCode(), p.getName(), p.getEspb(), p.getYear(), p.getSemester() };
			model.addRow(row);
		}
		
		nepolozeniJTable.setModel(model);
		
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(nepolozeniJTable.getSelectedRow() != -1) {
					String selectedRow = (String) nepolozeniJTable.getValueAt(nepolozeniJTable.getSelectedRow(), 0);
					pr = (BazaPredmeta.getInstance().getSubject(selectedRow));
					String [] options = new String[2];
					options[0] = MainFrame.getInstance().getResourceBundle().getString("yesOption");
					options[1] = MainFrame.getInstance().getResourceBundle().getString("noOption");
					int ret = JOptionPane.showOptionDialog(null, MainFrame.getInstance().getResourceBundle().getString("ukloniPredmet2"), MainFrame.getInstance().getResourceBundle().getString("ukloniPredmet1"), JOptionPane.YES_NO_OPTION, 
							JOptionPane.PLAIN_MESSAGE, null, options, null);
					if (ret != JOptionPane.YES_OPTION) {
	
					}else {
						if(pr != null) {
							s.getOtherExams().remove(pr);
							pr.getDidntPassSubject().remove(s);
							((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).removeRow(nepolozeniJTable.getSelectedRow());
							((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).fireTableDataChanged();
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("upozPred"), MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		JScrollPane central = new JScrollPane(nepolozeniJTable);
		central.setBorder(new EmptyBorder(10, 10, 10, 10));
		tablePanel.add(central, BorderLayout.CENTER);
		this.add(tablePanel, BorderLayout.CENTER);
	}

}
