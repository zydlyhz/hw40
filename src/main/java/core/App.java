package core;

import java.io.*;

public class App {
	static CreditCardValidation a = new CreditCardValidation();
	static App b = new App();	
	public static void main(String[] args) throws IOException  {
		String csvFile = "./cc.csv";
		String line = "";
		String delimiter = ",";
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
			String[] csv = line.split(delimiter);
			System.out.println(
					"CC Type: " 	+ CreditCardValidation.type(csv[0], csv[1]) + ", " + 
					"Expiration - " + (CreditCardValidation.exp(csv[2]) ? "valid": "expired") + "; " + 
					"CVV - " 		+ (CreditCardValidation.cvv(csv[0], csv[3]) ? "valid": "Invalid") + "; " + 
					"Luhn Test: " 	+ ((CreditCardValidation.luhn(csv[1])) ? "valid": "invalid"));}
		br.close();
	}
}
