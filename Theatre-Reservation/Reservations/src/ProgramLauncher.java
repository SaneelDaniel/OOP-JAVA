import java.util.ArrayList;
import java.util.TreeMap;


/**
 * 
 * @author danielsaneel
 * @version 02-15-2020 1.0
 * Program Launcher Class
 */
public class ProgramLauncher {
	

	public static String directory = System.getProperty("user.dir");
	
	public static void main(String[] args) {
		Reservation instance = new Reservation();
		Theater t = new Theater();
		instance.mainMenu();
	}
}
