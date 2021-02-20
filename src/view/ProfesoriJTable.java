package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;


public class ProfesoriJTable extends JTable {

	/**
	 * 
	 */
	
	private static ProfesoriJTable instance = null;
	public static ProfesoriJTable getInstance() {
		if(instance == null) {
			instance = new ProfesoriJTable();
		}
		
		return instance;
	}
	private static final long serialVersionUID = -3442801886235942895L;

	public ProfesoriJTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getTableHeader().setReorderingAllowed(false);
		this.setModel(new AbstractTableModelProfesor());
		this.setRowHeight(20);
		
	}
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}

}
