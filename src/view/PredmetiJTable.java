package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class PredmetiJTable extends JTable{

	/**
	 * 
	 */
	
	private static PredmetiJTable instance = null;
	public static PredmetiJTable getInstance() {
		if(instance == null) {
			instance = new PredmetiJTable();
		}
		
		return instance;
	}
	private static final long serialVersionUID = -1404313258591137307L;

	public PredmetiJTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getTableHeader().setReorderingAllowed(false);
		this.setModel(new AbstractTableModelPredmet());
		this.setRowHeight(20);
	}
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		// System.out.println(row + " " + column);
		Component c = super.prepareRenderer(renderer, row, column);
		
		// selektovani red ce imati drugaciju boju od ostalih
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}
}
