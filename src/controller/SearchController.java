package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import view.AbstractTableModelPredmet;
import view.AbstractTableModelProfesor;
import view.AbstractTableModelStudent;
import view.PredmetiJTable;
import view.ProfesoriJTable;
import view.StudentJTable;
import view.TabbedPane;
import view.ToolBar;


public class SearchController {
	
	public static SearchController instance = null;
	
	public static SearchController getInstance() {
		if(instance == null) {
			instance = new SearchController();
		}
		return instance;
	}
	
	public void searchStud() {
		if(TabbedPane.getInstance().getSelectedIndex() == 0) {
			String[] parts = ToolBar.searchField.getText().toLowerCase().split(" ");
			
			TableRowSorter<AbstractTableModelStudent> sorter = 
					new TableRowSorter<AbstractTableModelStudent>((AbstractTableModelStudent) StudentJTable.getInstance().getModel());
			List<RowFilter<Object, Object>> kriterijum = new ArrayList<RowFilter<Object,Object>>(3);
			
			kriterijum.add(RowFilter.regexFilter("(?i)" +  ".*" + "(?i)" + parts[0] + ".*", 2));
			
			if(parts.length >= 2) {
				kriterijum.add(RowFilter.regexFilter("(?i)" + ".*" + "(?i)" + parts[1] + ".*", 1));
				
				if(parts.length == 3) {
					kriterijum.add(RowFilter.regexFilter("(?i)" + ".*" + "(?i)" + parts[2] + ".*", 0));
				}
			}
			
			RowFilter<Object, Object> studentKriterijum = RowFilter.andFilter(kriterijum);
			sorter.setRowFilter(studentKriterijum);
			StudentJTable.getInstance().setRowSorter(sorter);
		}
	}
	
	public void searchProf() {
		if(TabbedPane.getInstance().getSelectedIndex() == 1) {
			String[] parts = ToolBar.searchField.getText().toLowerCase().split(" ");
			
			TableRowSorter<AbstractTableModelProfesor> sorter = 
					new TableRowSorter<AbstractTableModelProfesor>((AbstractTableModelProfesor) ProfesoriJTable.getInstance().getModel());
			List<RowFilter<Object, Object>> kriterijum = new ArrayList<RowFilter<Object,Object>>(2);
			
			kriterijum.add(RowFilter.regexFilter("(?i)" +  ".*" + "(?i)" + parts[0] + ".*", 2));
			
			if(parts.length == 2) {
				kriterijum.add(RowFilter.regexFilter("(?i)" + ".*" + "(?i)" + parts[1] + ".*", 1));
				
			}
			
			RowFilter<Object, Object> profesorKriterijum = RowFilter.andFilter(kriterijum);
			sorter.setRowFilter(profesorKriterijum);
			ProfesoriJTable.getInstance().setRowSorter(sorter);
		}
	}
	
	public void searchSub() {
		if(TabbedPane.getInstance().getSelectedIndex() == 2) {
			String part = ToolBar.searchField.getText().toLowerCase();
			
			TableRowSorter<AbstractTableModelPredmet> sorter = 
					new TableRowSorter<AbstractTableModelPredmet>((AbstractTableModelPredmet) PredmetiJTable.getInstance().getModel());
			List<RowFilter<Object, Object>> kriterijum = new ArrayList<RowFilter<Object,Object>>(1);
			
			kriterijum.add(RowFilter.regexFilter("(?i)" +  ".*" + "(?i)" + part + ".*", 1));
			
			
			RowFilter<Object, Object> predmetKriterijum = RowFilter.andFilter(kriterijum);
			sorter.setRowFilter(predmetKriterijum);
			PredmetiJTable.getInstance().setRowSorter(sorter);
		}
	}
}
