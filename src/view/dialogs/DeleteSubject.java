package view.dialogs;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controller.SubjectController;
import view.MainFrame;
import view.PredmetiJTable;

public class DeleteSubject extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1150582724972206686L;

	public DeleteSubject() {
	String [] options = new String[2];
	options[0] = MainFrame.getInstance().getResourceBundle().getString("yesOption");
	options[1] = MainFrame.getInstance().getResourceBundle().getString("noOption");
	
	int ret = JOptionPane.showOptionDialog(null, MainFrame.getInstance().getResourceBundle().getString("brisanjePredPoruka"), 
			MainFrame.getInstance().getResourceBundle().getString("izbrisiPred"), JOptionPane.YES_NO_OPTION, 
			JOptionPane.QUESTION_MESSAGE, null, options, null);

	if (ret != JOptionPane.YES_OPTION) {
		dispose();
	}else {
		SubjectController.getInstance().deleteSubject(PredmetiJTable.getInstance().getSelectedRow()); 
	}
	}
}
