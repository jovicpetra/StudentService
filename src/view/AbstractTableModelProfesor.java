package view;

import javax.swing.table.AbstractTableModel;

import model.BazaProfesora;

public class AbstractTableModelProfesor extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1406696593944639363L;
	
	public AbstractTableModelProfesor() {}

	@Override
	public int getColumnCount() {
		return BazaProfesora.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		return BazaProfesora.getInstance().getProfessors().size();
	}

	@Override
	public String getColumnName(int column) {
		return BazaProfesora.getInstance().getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return BazaProfesora.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
