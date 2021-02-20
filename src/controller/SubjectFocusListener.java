package controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import view.dialogs.AddSubjectDialog;

public class SubjectFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		JTextField textField = (JTextField) e.getComponent();
		@SuppressWarnings("unused")
		String inputText =  textField.getText();
		@SuppressWarnings("unused")
		String fieldName = textField.getName();
		
		if(textField.getForeground()==Color.RED) {
			textField.setForeground(Color.BLACK);
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField textField = (JTextField) e.getComponent();
		String inputText =  textField.getText();
		String fieldName = textField.getName();

		boolean uslov = (CheckValue.checkSubName(inputText) && (fieldName.equals(AddSubjectDialog.textName.getName()))) 
					|| (CheckValue.checkCode(inputText) && (fieldName.equals(AddSubjectDialog.textCode.getName())))
					|| (CheckValue.checkESPB(inputText) && (fieldName.equals(AddSubjectDialog.textESPB.getName())));
		
		if(!uslov) {
			textField.setForeground(Color.RED);
		}
		
		if((AddSubjectDialog.textName.getForeground() == Color.RED) || (AddSubjectDialog.textCode.getForeground() == Color.RED)
				|| (AddSubjectDialog.textESPB.getForeground() == Color.RED)) {
			AddSubjectDialog.okButton.setEnabled(false);
		}
		else if((AddSubjectDialog.textName.getText().isEmpty()) || (AddSubjectDialog.textCode.getText().isEmpty())
				|| (AddSubjectDialog.textName.getText().isEmpty())) {
			AddSubjectDialog.okButton.setEnabled(false);
		}
		else {
			AddSubjectDialog.okButton.setEnabled(true);
		}

	}

}
