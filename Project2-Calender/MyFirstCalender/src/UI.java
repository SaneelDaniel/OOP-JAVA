import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UI {
	
	MyCalendar cal;
	Scanner sc = new Scanner(System.in);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY");

	
	/**
	 * creates a new instace of UI with its own MyCalendar object
	 */
	public UI() {
		this.cal = new MyCalendar();
		mainMenu();
	}


	/**
	 * run main menu functions
	 */
	public void mainMenu() {
		System.out.println();
		System.out.println(":: MAIN MENU ::");
		System.out.println(": Please Select One Of The Options :");
		System.out.println("\n[V]iew By\t[C]reate\t[G]o To\t[E]vent List \t[D]elete \t[Q]uit");
		String input = sc.next();
		
		if(input.equals("V") || input.equals("v")) {
			viewBy();
		}
		else if(input.equals("C") || input.equals("c")) {
			create();
		}
		else if(input.equals("G") || input.equals("g")) {
			goTo();
		}
		else if(input.equals("E") || input.equals("e")) {
			eventList();
		}
		else if(input.equals("D") || input.equals("d")) {
			delete();
		}
		else if(input.equals("Q") || input.equals("q")) {
			quit();
		}
		else {
			System.out.println("PLEASE ENTER A VALID ENTRY");
			mainMenu();
		}
		
	}
	
	
	/**
	 * method views the events on a calendar by day/month views
	 */
	private void viewBy() {
		System.out.println("Please Choose From [D]ay View or [M]onth View: ");
		String input = sc.next();
		
		//day view
		if(input.equals("D") || input.equals("d")) {
			LocalDate d = LocalDate.now();
			cal.viewByD(d);
			
			while(1==1) {
				input = sc.next();
				System.out.println("[P]revious or [N]ext or [G]o back to main menu");
				if(input.equals("P") || input.equals("p")) {
					cal.viewByD(d = d.minusDays(1));
				}
				else if(input.equals("N") || input.equals("n")) {
					cal.viewByD(d = d.plusDays(1));
				}
				else if(input.equals("G") || input.equals("g")) {
					mainMenu();
				}
			}
		}
		else {
			System.out.println("Sorry Month View Not Available");
			mainMenu();
		}
	}

	
	/**
	 * method to interact with the creation of event
	 */
	private void create() {
		String name = null;
		LocalDate date;
		LocalTime stime;
		LocalTime etime;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		Scanner checkS = new Scanner(System.in);		
		System.out.println("Please Enter a Name for the Event: ");
		name = checkS.next();
		
		System.out.println("Please Enter the Date (MM/dd/YYYY): ");
		String strdate = checkS.next();
		LocalDate uDate = LocalDate.parse(strdate, formatter);
		
		formatter = DateTimeFormatter.ofPattern("HH:mm");
		System.out.println("PLease Enter the Start Time: ");
		stime = LocalTime.parse(sc.next());
		
		System.out.println("PLease Enter the End Time: ");
		etime = LocalTime.parse(sc.next());
		
		
		Event e = new Event(name, uDate, stime, etime);
		
		if(cal.timeValidated(e)) {
			cal.createEvent(e);
			System.out.println("Event Created Successfully: ");
		}
		else {
			System.out.println("The Given Time's clash with another event on the given date: ");
			cal.viewByDate(uDate);
		}
		mainMenu();
	}
	
	
	/**
	 * delete option to delete events
	 */
	private void delete() {
		System.out.println("[S]elected \t[A]ll \t[DR]");
		String input = sc.next();
		
		if(input.equals("S") || input.equals("s")) {
			System.out.println("Enter the Date [YYYY-MM-dd]: ");
			String strdate = sc.next();
			LocalDate date = LocalDate.parse(strdate);
			cal.viewByDate(date);
			
			System.out.println("Enter the name: ");
			String ename = sc.next();
			cal.deleteSelected(date, ename);
			mainMenu();
		}
		else if(input.equals("A") || input.equals("a")) {
			System.out.println("Enter the Date [YYYY-MM-dd]: ");
			String strdate = sc.next();
			LocalDate date = LocalDate.parse(strdate);
			cal.deleteAll(date);
			mainMenu();
		}
		else if(input.equals("DR")) {
			System.out.println("Please Enter the name of the event: ");
			sc.nextLine();
			String name = sc.nextLine();
			
			cal.deleteRecurring(name);
			mainMenu();
		}
		else {
			System.out.println("Please Enter a valid entry: ");
			delete();
		}
		
		
	}

	/*
	 * print the list of events, ordered by date and time 
	 */
	private void eventList() {
		cal.eventList();
		mainMenu();
	}

	/**
	 * method to go to a specific date and view events on that date
	 */
	private void goTo() {
		System.out.println("Pease Enter the Date [YYYY-MM-dd]: ");
		String strdate = sc.next();
		LocalDate date = LocalDate.parse(strdate);
		cal.viewByDate(date);
		mainMenu();
	}

	/**
	 * Program saves the data to a file and terminnates
	 */
	private void quit() {
		cal.quit();
		System.out.println("Good Bye!");
		System.exit(0);
	}
	

}
