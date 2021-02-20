package controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import view.dialogs.AddStudentDialog;

public class StudentFocusListener implements FocusListener {

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

		boolean uslov = (CheckValue.checkNameAndSurname(inputText) && (fieldName.equals(AddStudentDialog.textFName.getName()) || fieldName.equals(AddStudentDialog.textLName.getName())))
				|| (CheckValue.checkDate(inputText) && (fieldName.equals(AddStudentDialog.textDate.getName())))
				|| (CheckValue.checkAdress(inputText) && (fieldName.equals(AddStudentDialog.textAdress.getName())))
				|| (CheckValue.checkPhone(inputText) && (fieldName.equals(AddStudentDialog.textPhone.getName())))
				|| (CheckValue.checkEmail(inputText) && (fieldName.equals(AddStudentDialog.textEmail.getName())))
				|| (CheckValue.checkIndex(inputText) && (fieldName.equals(AddStudentDialog.textIndex.getName())))
				|| (CheckValue.checkYear(inputText) && (fieldName.equals(AddStudentDialog.textYear.getName())))
				|| (CheckValue.checkAvg(inputText) && (fieldName.equals(AddStudentDialog.textAvg.getName())));
		
		if(!uslov) {
			textField.setForeground(Color.RED);
		}
		
		if((AddStudentDialog.textFName.getForeground() == Color.RED) || (AddStudentDialog.textLName.getForeground() == Color.RED) 
				|| (AddStudentDialog.textDate.getForeground() == Color.RED) || (AddStudentDialog.textAdress.getForeground() == Color.RED)
				|| (AddStudentDialog.textPhone.getForeground() == Color.RED) || (AddStudentDialog.textEmail.getForeground() == Color.RED)
				|| (AddStudentDialog.textIndex.getForeground() == Color.RED) || (AddStudentDialog.textYear.getForeground() == Color.RED)) {
			AddStudentDialog.okButton.setEnabled(false);
		}
		else if ((AddStudentDialog.textFName.getText().isEmpty()) || (AddStudentDialog.textLName.getText().isEmpty())
				|| (AddStudentDialog.textDate.getText().isEmpty()) || (AddStudentDialog.textAdress.getText().isEmpty())
				|| (AddStudentDialog.textPhone.getText().isEmpty()) || (AddStudentDialog.textEmail.getText().isEmpty()) 
				|| (AddStudentDialog.textIndex.getText().isEmpty()) || (AddStudentDialog.textYear.getText().isEmpty())) {
			AddStudentDialog.okButton.setEnabled(false);
		}
		else {
			AddStudentDialog.okButton.setEnabled(true);
		}
	}
}
