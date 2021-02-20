package controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import view.dialogs.AddProfessorDialog;

public class ProfessorFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		JTextField textField = (JTextField) e.getComponent();
		
		if(textField.getForeground()==Color.RED) {
			textField.setForeground(Color.BLACK);
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

		JTextField textField = (JTextField) e.getComponent();
		String inputText =  textField.getText();
		String fieldName = textField.getName();

		boolean uslov = checkFormat(inputText, fieldName);

		if(!uslov) {
			textField.setForeground(Color.RED);
		}
		
		if((AddProfessorDialog.firstName.getForeground() == Color.RED) || (AddProfessorDialog.lastName.getForeground() == Color.RED) 
				|| (AddProfessorDialog.dateOfBirth.getForeground() == Color.RED) || (AddProfessorDialog.homeAddress.getForeground() == Color.RED)
				|| (AddProfessorDialog.phoneNum.getForeground() == Color.RED) || (AddProfessorDialog.email.getForeground() == Color.RED)
				|| (AddProfessorDialog.officeAddress.getForeground() == Color.RED) || (AddProfessorDialog.idCard.getForeground() == Color.RED)) {
			AddProfessorDialog.btnOk.setEnabled(false);
		}
		else if ((AddProfessorDialog.firstName.getText().isEmpty()) || (AddProfessorDialog.lastName.getText().isEmpty())
				|| (AddProfessorDialog.dateOfBirth.getText().isEmpty()) || (AddProfessorDialog.homeAddress.getText().isEmpty())
				|| (AddProfessorDialog.phoneNum.getText().isEmpty()) || (AddProfessorDialog.email.getText().isEmpty()) 
				|| (AddProfessorDialog.officeAddress.getText().isEmpty()) || (AddProfessorDialog.idCard.getText().isEmpty())) {
			AddProfessorDialog.btnOk.setEnabled(false);
		}
		else {
			AddProfessorDialog.btnOk.setEnabled(true);
		}
	}
	
	public boolean checkFormat(String inputText, String fieldName){
		if((CheckValue.checkNameAndSurname(inputText) && (fieldName.equals(AddProfessorDialog.firstName.getName()) || fieldName.equals(AddProfessorDialog.lastName.getName())))
				|| (CheckValue.checkDate(inputText) && (fieldName.equals(AddProfessorDialog.dateOfBirth.getName())))
				|| (CheckValue.checkAdress(inputText) && (fieldName.equals(AddProfessorDialog.homeAddress.getName()) || fieldName.equals(AddProfessorDialog.officeAddress.getName())))
				|| (CheckValue.checkPhone(inputText) && (fieldName.equals(AddProfessorDialog.phoneNum.getName())))
				|| (CheckValue.checkEmail(inputText) && (fieldName.equals(AddProfessorDialog.email.getName())))
				|| (CheckValue.checkIdCard(inputText) && (fieldName.equals(AddProfessorDialog.idCard.getName())))) return true;
				return false;
		}

}
