package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class CheckValue {
	
	public static boolean checkNameAndSurname(String s) {
		String nameRegEx = "[\\p{L}]+";
		if(s.matches(nameRegEx))
			return true;
		
		return false;
	}
	
	public static boolean checkDate(String s)  {
		boolean ret = false;
		LocalDate d = null;
		
		try {
			d = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			ret = true;
		} catch (DateTimeParseException e) {
			ret = false;
		}
		
		String[] parts = s.split("\\.");
		
		if(ret) {
			LocalDate now = LocalDate.now();
			if(now.compareTo(d) >= 0) {
				ret = true;
			}else {
				ret = false;
			}
		
			int dan = Integer.parseInt(parts[0]);
			int mjesec = Integer.parseInt(parts[1]);
			int godina = Integer.parseInt(parts[2]);
			
			if (mjesec == 1 || mjesec == 3 || mjesec == 5 || mjesec == 7 || mjesec == 8 || mjesec == 10 || mjesec == 12 ) {
				if(dan <= 0 || dan >= 32)
					ret = false;
			} else if (mjesec == 4 || mjesec == 6 || mjesec == 9 || mjesec == 1) {
				if(dan <= 0 || dan >= 31)
					ret = false;
			} else {
				if( godina % 4 == 0) {
					if(dan <= 0 || dan >= 30)
						ret = false;
				} else {
					if(dan <= 0 || dan >= 28)
						ret = false;
				}
			}

		}	
		
		return ret;
	}
	
	public static boolean checkAdress(String s) {
		String adressRegEx = "([0-9]*[\\s]*[\\p{L}]+)+([\\s][0-9]{0,5})*,[\\s]([\\p{L}]+[\\s]*)*(,[\\s][a-zA-Z]+[\\s][0-9]{1,3})*";
		if(s.matches(adressRegEx))
			return true;
		return false;
	}
	
	public static boolean checkPhone(String s) {
		String phoneRegEx = "[0-9]{3}/[0-9]{3,5}-[0-9]{3,5}";
		if(s.matches(phoneRegEx))
			return true;
		return false;
	}
	
	public static boolean checkEmail(String s) {
		String emailRegEx = "[^@]+@[^\\.]+\\..+";
		if(s.matches(emailRegEx))
			return true;
		return false;
	}
	
	public static boolean checkIndex(String s) {
		String indexRegEx = "[a-z]{1,3}-[0-9]{1,3}-[1,2][09][0-9]{2}";
		if(s.matches(indexRegEx))
			return true;
		return false;
	}
	
	public static boolean checkYear(String s) {
		String yearRegEx = "[12][09][0-9]{2}";
		if(s.matches(yearRegEx))
			return true;
		return false;
	}
	
	public static boolean checkAvg(String s) {
		String yearRegEx = "[0-9]{1}\\.[0-9]{2}";
		if(s.matches(yearRegEx))
			return true;
		return false;
	}
	
	public static boolean checkIdCard(String s) {
		String idCardRegEx = "[0-9]{9}";
		if(s.matches(idCardRegEx))
			return true;
		return false;
	}
	
	public static boolean checkSubName(String s) {
		String subNameRegEx = "([\\p{L}]+[\\s]*)+[0-9]*";
		if(s.matches(subNameRegEx))
			return true;
		return false;
	}
	
	public static boolean checkCode(String s) {
		String CodeRegEx = "[A-Za-z0-9]{2,5}";
		if(s.matches(CodeRegEx))
			return true;
		return false;
	}
	
	public static boolean checkESPB(String s) {
		String CodeRegEx = "[0-9]{1,2}";
		if(s.matches(CodeRegEx))
			return true;
		return false;
	}

}



















