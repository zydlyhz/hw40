package core;

import java.sql.Timestamp;

public class CreditCardValidation {

	public static boolean type(String cc_type, String cc_number) {
		String number = new String(cc_number.replaceAll("\\s", "").replaceAll("-", ""));
		if (number.startsWith("4") && (number.length() == 13 || number.length() == 16) && cc_type.equals("VISA")) {return true;} 
		else if ((number.startsWith("51") || number.startsWith("52") || number.startsWith("53") || number.startsWith("54") || number.startsWith("55")) && number.length() == 16 && cc_type.equals("MasterCard")) {return true;}
		else if ((number.startsWith("6011") || number.startsWith("62") || number.startsWith("64") || number.startsWith("65")) && number.length() == 16 && cc_type.equals("Discover")) {return true;}
		else if ((number.startsWith("34") || number.startsWith("37")) && number.length() == 15 && cc_type.equals("American Express")) {return true;} 
		else {return false;}
	}
	
	public static boolean luhn(String cc_number) {
		String number = new String(cc_number.replaceAll("\\s", "").replaceAll("-", ""));
		int sum = 0; boolean swap = false;
		for (int i = number.length() - 1; i >= 0; i--) {
			int digit = Integer.parseInt(number.substring(i, i + 1));
			if (swap) {digit *= 2;if (digit > 9) {digit -= 9;}}
			sum += digit;swap = !swap;}
		return (sum % 10 == 0);
	}
	
	public static boolean exp(String cc_exp) {
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 					 // 2019-07-07 21:33:11.045
		int exp = Integer.parseInt(cc_exp.substring(3, 5) + cc_exp.substring(0, 2)); // 07/19
		int today = Integer.parseInt((String.valueOf(ts.toString().split("-")[0].substring(2, 4)) + ts.toString().split("-")[1]));
		return today <= exp;
		}
		
	public static boolean cvv(String cc_type, String cc_cvv) {
		if (cc_type.equals("American Express")) {return cc_cvv.matches("^(\\d{4})$");} 
		else {return cc_cvv.matches("^(\\d{3})$");}}
}
