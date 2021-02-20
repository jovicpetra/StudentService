package view;

import javax.swing.table.AbstractTableModel;
import model.BazaStudenata;

public class AbstractTableModelStudent extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5712265068405275698L;

	@Override
	public int getRowCount() {
		return BazaStudenata.getInstance().getStudents().size();
	}

	@Override
	public int getColumnCount() {
		return BazaStudenata.getInstance().getColumnCount();
	}
	
	@Override
	public String getColumnName(int column) {
		return BazaStudenata.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaStudenata.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
