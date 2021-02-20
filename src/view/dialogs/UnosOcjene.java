package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CheckValue;
import controller.ProfessorFocusListener;
import controller.StudentController;
import model.Ocena;
import model.Predmet;
import model.Student;
import view.MainFrame;
import view.StudentJTable;
import view.TabbedPane;

public class UnosOcjene extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1629267325524197524L;
	
	public static JButton btn1, btn2;
	public  static JTextField code, name, date;
	public static JLabel codeLbl, nameLbl, ocenaLbl, dateLbl;
	public JComboBox<String> ocena;
	
	public UnosOcjene(Predmet p) {
		
		this.setTitle(MainFrame.getInstance().getResourceBundle().getString("unosOcene"));
		this.setIconImage(new ImageIcon("images"+File.separator+"icon.png").getImage());
		this.setPreferredSize(new Dimension(300, 250));
		this.setModal(true);
		this.setResizable(false);
		
		JPanel finalPanel = new JPanel(new BorderLayout());
		JPanel upPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel downPanel = new JPanel(new FlowLayout());
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 20, 0, 0);
		
		 codeLbl = new JLabel(MainFrame.getInstance().getResourceBundle().getString("sifra"));
		 gbc.gridx = 0;
		 gbc.gridy = 0;
		 upPanel.add(codeLbl, gbc);
		 nameLbl = new JLabel(MainFrame.getInstance().getResourceBundle().getString("naziv"));
		 gbc.gridx = 0;
		 gbc.gridy = 1;
		 upPanel.add(nameLbl, gbc);
		 dateLbl = new JLabel(MainFrame.getInstance().getResourceBundle().getString("datum2"));
		 gbc.gridx = 0;
		 gbc.gridy = 2;
		 upPanel.add(dateLbl, gbc);
		 ocenaLbl = new JLabel(MainFrame.getInstance().getResourceBundle().getString("ocena1"));
		 gbc.gridx = 0;
		 gbc.gridy = 3;
		 upPanel.add(ocenaLbl, gbc);
		 
		 gbc.gridwidth = 3;
		 gbc.weightx = 100;
		 gbc.fill = GridBagConstraints.HORIZONTAL;
		 gbc.insets = new Insets(10, 20, 0, 20);

		code = new JTextField();
		code.setText(p.getCode());
		code.setEnabled(false);
		code.setDisabledTextColor(Color.BLUE);
		gbc.gridx = 1;
		gbc.gridy = 0;
		upPanel.add(code, gbc);
		name = new JTextField();
		name.setText(p.getName());
		name.setEnabled(false);
		name.setDisabledTextColor(Color.BLUE);
		gbc.gridx = 1;
		gbc.gridy = 1;
		upPanel.add(name, gbc);
		String[] ocene = { "6", "7", "8", "9", "10"};
		ocena = new JComboBox<String>(ocene);
		gbc.gridx = 1;
		gbc.gridy = 3;
		upPanel.add(ocena, gbc);
	    date = new JTextField();
		date.setToolTipText("Format: '01.01.2021'");
		date.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				JTextField textField = (JTextField) e.getComponent();
				
				if(textField.getForeground()==Color.RED) {
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				JTextField textField = (JTextField) e.getComponent();
				String inputText =  textField.getText();
				CheckValue ck = new CheckValue();
				if(!ck.checkDate(inputText)) {
					textField.setForeground(Color.RED);
					btn1.setEnabled(false);
				}else {
					btn1.setEnabled(true);
				}
			}});
	    gbc.gridx = 1;
		gbc.gridy = 2;
		upPanel.add(date, gbc);
		
		btn1 = new JButton(MainFrame.getInstance().getResourceBundle().getString("potvrdi"));
		btn1.setEnabled(false);
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String ocenaTxt  = (String) ocena.getSelectedItem();
				int oc = Integer.parseInt(ocenaTxt);
				String dateTxt = date.getText();
				LocalDate d = LocalDate.parse(dateTxt, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
				Student s = StudentController.getInstance().findStudent(StudentJTable.getInstance().getSelectedRow());
				Ocena o = new Ocena(s, p, oc, d);
				StudentController.getInstance().addGrade(s, o);
				StudentController.getInstance().removeSubject(s, p);
				((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).removeRow(AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getSelectedRow());
				((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).fireTableDataChanged();
				
				Object[] red = {p.getCode(), p.getName(), p.getEspb(), 
						o.getValue(), o.getExamDate()};
				((DefaultTableModel) AddStudentDialog.instance.getPolozeni().polozeniJTable.getModel()).addRow(red);
				((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).fireTableDataChanged();
				int espb = 0;
				double suma = 0;
				double pros = 0;
				for(Ocena mark : s.getPassedExams()) {
					espb += mark.getSubject().getEspb();
					suma += mark.getValue();
					pros = suma/s.getPassedExams().size();
				}
				s.setAverageGrade(pros);
				DecimalFormat f = new DecimalFormat("0.00");
				String input = MainFrame.getInstance().getResourceBundle().getString("prosecnaOcena") + " "+ f.format(pros) + "\n" + 
						MainFrame.getInstance().getResourceBundle().getString("ukupnoEspb") + " "+ espb;
				AddStudentDialog.instance.getPolozeni().t.setText(input);
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
		finalPanel.add(downPanel, BorderLayout.SOUTH);
		this.add(finalPanel);
		this.pack();
		this.setLocationRelativeTo(this.getParent());
		this.setVisible(true);
	}

}
