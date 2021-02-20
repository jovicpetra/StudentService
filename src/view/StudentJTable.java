package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class StudentJTable extends JTable {
	
	private static StudentJTable instance = null;
	public static StudentJTable getInstance() {
		if(instance == null) {
			instance = new StudentJTable();
		}
		
		return instance;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -729251922543511523L;

	public StudentJTable() {
		
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelStudent());
		this.setRowHeight(20);
		this.getTableHeader().setReorderingAllowed(false);
	
	}
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		
		Component c = super.prepareRenderer(renderer, row, column);
		if(isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		}else {
			c.setBackground(Color.WHITE);
		}
		
		return c;
	}
	
	

}
