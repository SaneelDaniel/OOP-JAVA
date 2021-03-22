package tp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class tp {

	
	public static void main(String[] args) {
		
		
		LocalDate d;
		String s = "04/05/2020";
		DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		d = LocalDate.parse(s,form);

		System.out.print(d);
	}
}
