package view.dialogs;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controller.StudentController;
import view.MainFrame;
import view.StudentJTable;
import view.TabbedPane;

public class DeleteStudent extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8703249051956714326L;
	
	public DeleteStudent() {
		String [] options = new String[2];
		options[0] = MainFrame.getInstance().getResourceBundle().getString("yesOption");
		options[1] = MainFrame.getInstance().getResourceBundle().getString("noOption");
		int ret = JOptionPane.showOptionDialog(null, MainFrame.getInstance().getResourceBundle().getString("brisanjeStudPoruka"), 
				MainFrame.getInstance().getResourceBundle().getString("izbrisiStud"), JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, options, null);
	
		if (ret != JOptionPane.YES_OPTION) {
			dispose();
		}else {
			StudentController.getInstance().deleteStudent(StudentJTable.getInstance().getSelectedRow());
			TabbedPane.getInstance().refreshStudentTable();
		}
	}

}
