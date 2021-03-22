import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class MyCalendar {
	//ArrayList<String> months = new ArrayList<String>(12);
	ArrayList<String> days = new ArrayList<>(7);
	TreeMap<LocalDate, ArrayList<Event>> map;
	ArrayList<Event> recurringEvents;
	ArrayList<Event> oneTime;
	LocalDate cal;

	public MyCalendar() {
		cal = LocalDate.now();
		map = new TreeMap<>();
		initList(cal);
		recurringEvents = new ArrayList<>(); 
		//printMonthCal(cal);
	}

	
	/**
	 * print the current month
	 * @param gCal2 a Calendar instance
	 */
	private void initList(LocalDate cal) {
		days.add("Mo");
		days.add("Tu");
		days.add("We");
		days.add("Th");
		days.add("Fr");
		days.add("Sa");
		days.add("Su");
	}


	/*
	 * prints the month view of the calendar
	 */
	private void printMonthCal(LocalDate cal) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		System.out.println(formatter.format(cal));
		int dayOfMonthToday = cal.getDayOfMonth();
		int dayOfWeek = cal.getDayOfWeek().getValue();
		LocalDate x = LocalDate.of(cal.getYear(), cal.getMonth(), 1); 
		int firstDayOfMonth = x.getDayOfWeek().getValue();
		int lastDay = x.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
		x = LocalDate.of(cal.getYear(), cal.getMonth(), lastDay);
		int lastWeekDay = x.getDayOfWeek().getValue();

		//print days
		for(int i = 0; i<days.size(); i++) {
			System.out.print(days.get(i)+"   ");
		}
		System.out.println();


		for(int j = 0; j<=days.size(); j++) {
			if(j == firstDayOfMonth) {
				break;
			}
			else {
				System.out.printf("    ");
			}
		}
		LocalDate d = LocalDate.of(cal.getYear(), cal.getMonth(), 1);
		if(map.containsKey(d)) {
			System.out.print(" {1}");
		}
		else {
			System.out.print("   1");
		}


		if(firstDayOfMonth == 7) {
			System.out.println();
			firstDayOfMonth = 1;
		}

		for(int i = 2; i<=lastDay; i++) {

			d = LocalDate.of(cal.getYear(), cal.getMonth(), i);

			if(i<10) {
				if(map.containsKey(d)) {
					System.out.printf("{%d}", i);
					System.out.print("   ");
					//continue;
				}
				else {
					if(dayOfMonthToday == i) {
						System.out.printf("[%d]", i);
						System.out.print("   ");
					}
					else {
						System.out.printf("%d    ", i);
					}
				}
			}
			else {
				if(map.containsKey(d)) {
					System.out.printf("{%d}", i);
					System.out.print("  ");
					//continue;
				}
				else {
					if(dayOfMonthToday == i) {
						System.out.printf("[%d]", i);
						System.out.print("  ");
					}
					else {
						System.out.printf("%d   ", i);
					}
				}

			}
			if(firstDayOfMonth==7) {
				System.out.println();
				firstDayOfMonth = 1;
			}
			else {
				firstDayOfMonth++;
			}
		}
	}


	/**
	 * creates the event in the calendar
	 * @param Event e
	 */
	public void createEvent(Event e) {
		LocalDate date = e.sDate;
		if(!map.containsKey(date)) {
			ArrayList<Event> newList = new ArrayList<>();
			newList.add(e);
			map.put(date, newList);
		}
		else {
			map.get(date).add(e);

		}
	}



	/**
	 * Method checks if the given event time clashes with any existing events
	 * @param Event e
	 * @return true if e does not clash with any other event
	 */
	public boolean timeValidated(Event e) {
		LocalDate date = e.sDate;
		LocalTime stime = e.sTime;
		LocalTime etime = e.eTime;
		ArrayList<Event> list  = new ArrayList<Event>();
		if(map.containsKey(date)) {
			list = map.get(date);

			for(int i = 0; i<list.size(); i++) {
				Event evnt = list.get(i);

				if(e.sTime.equals(evnt.sTime) || e.eTime.equals(evnt.eTime)) {
					return false;
				}
				//check for start times
				if(e.sTime.isBefore(evnt.sTime)) {
					if(!e.eTime.isBefore(evnt.sTime)) {
						return false;
					}
				}
				//check for end time
				if(e.sTime.isAfter(evnt.sTime)) {
					if(!e.sTime.isAfter(evnt.eTime)) {
						return false;
					}
				}
			}
			return true;
		}
		else {
			return true;
		}
	}

	


	/**
	 * create a onne-time event from file
	 * @param arr
	 */
	public void createEventFromFile(String name, String[] arr) {
		LocalDate date = LocalDate.parse(arr[0]);
		LocalTime stime = LocalTime.parse(arr[1]);
		LocalTime etime = LocalTime.parse(arr[2]);
		Event e = new Event(name,date,stime,etime);
		createEvent(e);
	}

	/**
	 * create a recurring event from file
	 * @param arr
	 */
	public void createRecurringFromFile(String name, String[] arr) {
		// TODO Auto-generated method stub
		char[] days = arr[0].toCharArray();
		LocalTime stime = LocalTime.parse(arr[1]);
		LocalTime etime = LocalTime.parse(arr[2]);
		LocalDate sdate = LocalDate.parse(arr[3]);
		LocalDate edate = LocalDate.parse(arr[4]);

		int[] dayNum = new int[days.length];
		for(int i = 0; i<days.length; i++) {
			if(days[i] == 'M') {
				dayNum[i] = 1;
			}
			else if(days[i] == 'T') {
				dayNum[i] = 2;
			}
			else if(days[i] == 'W') {
				dayNum[i] = 3;
			}
			else if(days[i] == 'R') {
				dayNum[i] = 4;
			}
			else if(days[i] == 'F') {
				dayNum[i] = 5;
			}
			else if(days[i] == 'A') {
				dayNum[i] = 6;
			}
			else if(days[i] == 'S') {
				dayNum[i] = 7;
			}
		}

		for(LocalDate d = sdate; !d.isAfter(edate); d = d.plusDays(1)) {
			for(int i = 0; i<dayNum.length; i++) {
				if(d.getDayOfWeek().getValue() == dayNum[i]) {
					Event e = new Event(name, sdate, d, edate, stime, etime, arr[0]);
					createEvent(e);
				}
			}
		}
		Event rEvent = new Event(name, sdate, null, edate, stime, etime, arr[0]);
		recurringEvents.add(rEvent);
	}

	/**
	 * method displays all the events on a given date
	 * @param date
	 */
	public void viewByDate(LocalDate date) {
		ArrayList<Event> list = new ArrayList<Event>();
		if(map.containsKey(date)) {
			list = map.get(date);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, YYYY");
			System.out.println(formatter.format(date));
			for(int i = 0; i<list.size(); i++) {
				Event e = list.get(i);
				System.out.println("\t" + e.sTime + " - " + e.eTime + ": " + e.name);				
			}
		}
		else {
			System.out.println(":: No Event Scheduled Yet For: " + date + " ::");
		}

	}

	/**
	 * print the list of events ordered by date and time
	 */
	public void eventList() {
		if(map.isEmpty()) {
			System.out.println("No Events Scheduled Yet");
			return;
		}
		System.out.println("One Time Events: ");
		System.out.println("________________ \n");

		System.out.println("2020");
		for(Map.Entry<LocalDate, ArrayList<Event>> entry: map.entrySet() ) {

			LocalDate currentDate = entry.getKey();
			ArrayList<Event> list = map.get(currentDate);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm a");
			for(int i = 0;i<list.size();i++) {
				Event e = list.get(i);
				if(e.recurring == false) {
					System.out.println("   "+formatter.format(currentDate) +"  " + timeFormatter.format(e.sTime) + " - " + timeFormatter.format(e.eTime) + "  " + e.name);
				}
			}
		}

		System.out.println("\nRecurring Events:");
		System.out.println("________________ \n");
		if(recurringEvents.size()==0) {
			System.out.println("No Recurring Event Scheduled");
			return;
		}
		for(int i = 0; i<recurringEvents.size(); i++) {
			Event e = recurringEvents.get(i);
			System.out.println(e.name);
			System.out.print(e.rdays + " " + e.sTime + " " + e.eTime + " " + e.recurringStartDate + " " + e.eDate);
			System.out.println();
		}

	}

	/**
	 * delete selected one time event
	 * @param date
	 * @param ename
	 */
	public void deleteSelected(LocalDate date, String ename) {
		if(!map.containsKey(date)){
			System.out.println("No event Scheduled");
		}
		else {
			ArrayList<Event> list = map.get(date);
			for(int i = 0; i<list.size(); i++) {
				if(list.get(i).name.equals(ename)) {
					list.remove(i);
					System.out.println("Event Successfully Deleted");
					break;

				}
			}
		}
	}


	/**
	 * delete all events for a specific date
	 * @param date
	 */
	public void deleteAll(LocalDate date) {
		if(!map.containsKey(date)) {
			System.out.println("No Events Scheduled For the Date");
		}
		else {
			for(int i = map.get(date).size()-1; i>=0; i--) {
				map.get(date).remove(i);
			}
			System.out.println("Events Successfully Deleted");
		}
	}

	/**
	 * deletes the given recurring event from memory
	 * @param name
	 */
	public void deleteRecurring(String name) {
		for(Map.Entry<LocalDate, ArrayList<Event>> entry: map.entrySet()) {
			LocalDate d = entry.getKey();
			ArrayList<Event> list = map.get(d);
			for(int i = 0; i<list.size(); i++) {
				Event e = list.get(i);
				if(e.recurring) {
					if(e.name.equals(name)) {
						list.remove(e);
					}
				}
			}
		}
		for(int i = 0; i<recurringEvents.size(); i++) {
			Event e  = recurringEvents.get(i);
			if(e.name.equals(name)) {
				recurringEvents.remove(e);
			}
		}
	}


	/**
	 * method to view and scroll calendar by dates
	 * @param d
	 */
	public void viewByD(LocalDate d) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMMM dd, YYYY");
		System.out.println("\n" + formatter.format(d));
		if(map.containsKey(d)) {
			ArrayList<Event> list = map.get(d);
			for(int i = 0; i<list.size(); i++) {
				System.out.println(list.get(i).name + " : " + list.get(i).sTime + " - " + list.get(i).eTime);
			}
		}
		else {
			System.out.println("No Scheduled Events\n");
			return;
		}

	}

	/**
	 * save event list on the output.txt file
	 */
	public void quit() {
		try {
			File file = new File("output.txt");
			FileWriter write = null;

			if(!file.exists()) {
				file.createNewFile();
				write = new FileWriter(file.getAbsoluteFile());
			}
			String path = file.getAbsolutePath();

			BufferedWriter writer = new BufferedWriter(write);

			for(Map.Entry<LocalDate, ArrayList<Event>> entry: map.entrySet()) {
				LocalDate d = entry.getKey();
				ArrayList<Event> list = map.get(d);
				for(int i = 0; i<list.size(); i++) {
					Event e = list.get(i);
					writer.write(e.name + "\n" );
					writer.write(e.sDate + " " + e.sTime + " " + e.eTime + "\n");
				}
			}
			writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
