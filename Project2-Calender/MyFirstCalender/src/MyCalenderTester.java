import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Calendar;

public class MyCalenderTester {
	/**
	 * Initialize the Calender
	 * Load Events if the file events.txt exists
	 * prints the current month and eny events if present
	 */
	static void initializeFile(MyCalendar cal, File file) {

		String path = System.getProperty("user.dir");
		String fName = path+"/events.txt";
		System.out.println(fName);
		try {
			File events  = file;
			if(events.exists()) {
				System.out.println("File found");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine(); 

				String name = null;
				String second = null;
				if(line != null) {
					while(line!=null) {
						name = line;
						second = br.readLine();
						String[] arr = second.split(" ");
						if(arr.length == 5) {
							cal.createRecurringFromFile(name, arr);
						}
						else if(arr.length == 3) {
							cal.createEventFromFile(name, arr);
						}
						line = br.readLine();
					}
				}
				//initializeFile(file);
			}
			else {
				file.createNewFile();
			}
		}
		catch(Exception x){
			x.printStackTrace();
		}
	}

	public static void main(String args[]) {
		System.out.println("Welcome to your Calender");
		String path = System.getProperty("user.dir");
		String fName = path+"/events.txt";
		File file = new File(fName);
		
		UI ui = new UI();
		initializeFile(ui.cal, file);
	}
}
