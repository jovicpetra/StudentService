package view.dialogs;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controller.ProfessorController;
import view.MainFrame;
import view.ProfesoriJTable;
import view.TabbedPane;

public class DeleteProfessor extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2721388493241296445L;
	
	public DeleteProfessor() {
		String [] options = new String[2];
		options[0] = MainFrame.getInstance().getResourceBundle().getString("yesOption");
		options[1] = MainFrame.getInstance().getResourceBundle().getString("noOption");
		int ret = JOptionPane.showOptionDialog(null, MainFrame.getInstance().getResourceBundle().getString("brisanjeProfPoruka"), 
				MainFrame.getInstance().getResourceBundle().getString("izbrisiProf"), JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, options, null);
	
		if (ret != JOptionPane.YES_OPTION) {
			dispose();
		}else {
			ProfessorController.getInstance().deleteProfessor(ProfesoriJTable.getInstance().getSelectedRow());
			TabbedPane.getInstance().refreshProfessorTable();
		}
	}

}
