package view;

import java.util.Comparator;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TabbedPane extends JTabbedPane {
	private JScrollPane scrollPaneStud;
	private JScrollPane scrollPaneProf;
	private JScrollPane scrollPaneSub;
	private static TabbedPane instance = null;
	public static TabbedPane getInstance() {
		if(instance == null) {
			instance = new TabbedPane();
		}
		
		return instance;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8320287704098046391L;

	public TabbedPane() {
		super();

		scrollPaneStud = new JScrollPane(StudentJTable.getInstance());
		scrollPaneStud.setBorder(new EmptyBorder(10,30,10,30));
		TableRowSorter<TableModel> sortiraj = new TableRowSorter<TableModel>(StudentJTable.getInstance().getModel());
		
		sortiraj.setComparator(0, new Comparator<String>(){
			
			public int compare(String indeks1, String indeks2) {
				String[] prvi = indeks1.split("-");
				String[] drugi = indeks2.split("-");
				String smjer1 = prvi[0];
				String smjer2 = drugi[0];
				int broj1 = Integer.parseInt(prvi[1]);
				int broj2 = Integer.parseInt(drugi[1]);
				
				int godina1 = Integer.parseInt(prvi[2]);
				int godina2 = Integer.parseInt(drugi[2]);
				
				if(smjer1.compareTo(smjer2) != 0) {
					return smjer1.compareTo(smjer2);
				}
				else {
					if(godina1 > godina2)				
						return 1;
					else if(godina1 < godina2)
						return -1;             
					else {								
						if(broj1 > broj2)
							return 1;
						else if(broj1 < broj2)
							return -1;
						else
							return 0;
					}
				}
			}
		});
		
		sortiraj.setComparator(5, new Comparator<Double>(){
	        public int compare(Double o1, Double o2){
	            return o1.compareTo(o2);
	        }

	    });
		
		StudentJTable.getInstance().setRowSorter(sortiraj);
		
		scrollPaneProf = new JScrollPane(ProfesoriJTable.getInstance());
		scrollPaneProf.setBorder(new EmptyBorder(10,30,10,30));
		
		TableRowSorter<TableModel> sortProfessor = new TableRowSorter<TableModel>(ProfesoriJTable.getInstance().getModel());
		ProfesoriJTable.getInstance().setRowSorter(sortProfessor);		
		
		scrollPaneSub = new JScrollPane(PredmetiJTable.getInstance());
		scrollPaneSub.setBorder(new EmptyBorder(10,30,10,30));
		
		TableRowSorter<TableModel> sortSubject = new TableRowSorter<TableModel>(PredmetiJTable.getInstance().getModel());
		PredmetiJTable.getInstance().setRowSorter(sortSubject);	
		
		this.addTab("Student", null, scrollPaneStud, "Student");
		this.addTab("Profesor", null, scrollPaneProf, "Profesor");
		this.addTab("Predmet", null, scrollPaneSub,"Predmet");
		
		if(this.getSelectedIndex() == 0) {
			refreshStudentTable();
		}
		
		if(this.getSelectedIndex() == 1) {
			refreshProfessorTable();
		}
		
		if(this.getSelectedIndex() == 2) {
			refreshSubjectTable();
		}
	}
	
	public void refreshStudentTable() {
		AbstractTableModelStudent model = (AbstractTableModelStudent) StudentJTable.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	public void refreshProfessorTable() {
		AbstractTableModelProfesor model = (AbstractTableModelProfesor) ProfesoriJTable.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	public void refreshSubjectTable() {
		AbstractTableModelPredmet model = (AbstractTableModelPredmet) PredmetiJTable.getInstance().getModel();
		model.fireTableDataChanged();
		validate();
	}
	
	public void initComponents() {
		this.addTab(MainFrame.getInstance().getResourceBundle().getString("student"), null, scrollPaneStud, MainFrame.getInstance().getResourceBundle().getString("student"));
		this.addTab(MainFrame.getInstance().getResourceBundle().getString("profesor"), null, scrollPaneProf, MainFrame.getInstance().getResourceBundle().getString("profesor"));
		this.addTab(MainFrame.getInstance().getResourceBundle().getString("predmet"), null, scrollPaneSub,MainFrame.getInstance().getResourceBundle().getString("predmet"));
	}
}
