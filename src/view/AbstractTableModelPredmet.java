package view;

import javax.swing.table.AbstractTableModel;

import model.BazaPredmeta;

public class AbstractTableModelPredmet extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2312401778786703057L;

	public AbstractTableModelPredmet() {}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return BazaPredmeta.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return BazaPredmeta.getInstance().getSubjects().size();
	}

	@Override
	public String getColumnName(int column) {
		return BazaPredmeta.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return BazaPredmeta.getInstance().getValueAt(rowIndex, columnIndex);
	}
}
