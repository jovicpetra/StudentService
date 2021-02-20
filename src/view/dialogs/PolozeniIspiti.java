package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.StudentController;
import model.Ocena;
import model.Student;
import view.MainFrame;
import view.StudentJTable;

public class PolozeniIspiti extends JPanel{

	public JTable polozeniJTable;
	public static JTextArea t;
	private static PolozeniIspiti instance = null;
	public static PolozeniIspiti getInstance() {
		if(instance == null) {
			instance = new PolozeniIspiti();
		}
		
		return instance;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4702301996691368782L;

	public PolozeniIspiti() {
		this.setLayout(new BorderLayout());
		
		JButton ponisti = new JButton(MainFrame.getInstance().getResourceBundle().getString("ponisti"));
		ponisti.setPreferredSize(new Dimension(160, 40));
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		
		buttons.add(Box.createHorizontalStrut(10));
		buttons.add(ponisti);
		buttons.add(Box.createHorizontalStrut(70));
			
		polozeniJTable = new JTable();
		polozeniJTable.setRowSelectionAllowed(true);
		polozeniJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		polozeniJTable.getTableHeader().setReorderingAllowed(false);
		
		DefaultTableModel model = new DefaultTableModel();
		String[] columns = {MainFrame.getInstance().getResourceBundle().getString("sifra1"),
							MainFrame.getInstance().getResourceBundle().getString("naziv1"),
							MainFrame.getInstance().getResourceBundle().getString("bod"), 
							MainFrame.getInstance().getResourceBundle().getString("ocena"), 
							MainFrame.getInstance().getResourceBundle().getString("datum1")};
		model.setColumnIdentifiers(columns);
		
		Student s = StudentController.getInstance().findStudent(StudentJTable.getInstance().getSelectedRow());
		int ukupnoEspb = 0;
		double avg = 0;
		double sum = 0;
		
		for(Ocena o : s.getPassedExams()) {
			Object[] row = { o.getSubject().getCode(), o.getSubject().getName(), o.getSubject().getEspb(), 
					o.getValue(), o.getExamDate() };
			ukupnoEspb += o.getSubject().getEspb();
			sum += o.getValue();
			avg = sum/s.getPassedExams().size();
			model.addRow(row);
		}
		
		polozeniJTable.setModel(model);
		JScrollPane central = new JScrollPane(polozeniJTable);
		central.setBorder(new EmptyBorder(10, 10, 10, 10));
				
		JPanel text = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		t = new JTextArea();
		DecimalFormat f = new DecimalFormat("0.00");
		String input = MainFrame.getInstance().getResourceBundle().getString("prosecnaOcena") + " "
				+ f.format(avg) + "\n" + MainFrame.getInstance().getResourceBundle().getString("ukupnoEspb") + " " + ukupnoEspb;
		t.setText(input);
		t.setForeground(Color.BLACK);
		t.setBackground(getBackground());
		text.setBorder(new EmptyBorder(10, 10, 10, 10));
		text.setPreferredSize(new Dimension(100, 100));
		text.add(t);
		
		ponisti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Ocena ocena = null;
				if(polozeniJTable.getSelectedRow() != -1) {
					String selectedRow = (String) polozeniJTable.getValueAt(polozeniJTable.getSelectedRow(), 0);
					for(Ocena o : s.getPassedExams()) {
						if(o.getSubject().getCode().equals(selectedRow)) {
							ocena = o;
						}
					}
				
					String [] options = new String[2];
					options[0] = MainFrame.getInstance().getResourceBundle().getString("yesOption");
					options[1] = MainFrame.getInstance().getResourceBundle().getString("noOption");
					int ret = JOptionPane.showOptionDialog(null, MainFrame.getInstance().getResourceBundle().getString("ponistavanjePor"), 
							MainFrame.getInstance().getResourceBundle().getString("ponistiNaslov"), JOptionPane.YES_NO_OPTION, 
							JOptionPane.PLAIN_MESSAGE, null, options, null);
					if (ret != JOptionPane.YES_OPTION) {
	
					}else {
						if(ocena != null) {
							s.getPassedExams().remove(ocena);
							s.getOtherExams().add(ocena.getSubject());
							ocena.getSubject().getDidntPassSubject().add(s); //dodam studenta u one koji nisu polozili predmet
							ocena.getSubject().getPassedSubject().remove(s); //izbacim ga iz onih koji su polozili
							model.removeRow(polozeniJTable.getSelectedRow());
							Object[] red = { ocena.getSubject().getCode(), ocena.getSubject().getName(), ocena.getSubject().getEspb(), 
									ocena.getSubject().getYear(), ocena.getSubject().getSemester() };
							((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).addRow(red);
							((DefaultTableModel) AddStudentDialog.instance.getNepolozeni().nepolozeniJTable.getModel()).fireTableDataChanged();
							
							int espb = 0;
							double suma = 0;
							double pros = 0;
							for(Ocena o : s.getPassedExams()) {
								espb += o.getSubject().getEspb();
								suma += o.getValue();
								pros = suma/s.getPassedExams().size();
							}
							
							s.setAverageGrade(pros);
							
							String input = MainFrame.getInstance().getResourceBundle().getString("prosecnaOcena") + " "
									+ f.format(pros) + "\n" + MainFrame.getInstance().getResourceBundle().getString("ukupnoEspb") + " " + espb;
							t.setText(input);
						}
					}
				
				} else {
					JOptionPane.showMessageDialog(null, MainFrame.getInstance().getResourceBundle().getString("ponistiUpoz"), 
							MainFrame.getInstance().getResourceBundle().getString("greska"), JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		this.add(buttons, BorderLayout.NORTH);
		this.add(central, BorderLayout.CENTER);
		this.add(text, BorderLayout.SOUTH);
		
	}

}