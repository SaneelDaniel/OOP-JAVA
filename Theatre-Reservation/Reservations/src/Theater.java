import java.time.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 
 * @author danielsaneel
 * @version 02-15-2020 1.0
 * Theater class, creates object of theater with all available seats and shows.
 *
 */
public class Theater {

	public static ArrayList<Show> shows = new ArrayList<>();
	public ArrayList<String> showName = new ArrayList<>();

	public ArrayList<String> leftMainSeats = new ArrayList<>();
	public ArrayList<String> rightMainSeats = new ArrayList<>();
	public ArrayList<String> mainSeats = new ArrayList<>();
	public ArrayList<String> southBalconies1 = new ArrayList<>();
	public ArrayList<String> southBalconies2 = new ArrayList<>();
	public ArrayList<String> westBalconies = new ArrayList<>();
	public ArrayList<String> eastBalconies = new ArrayList<>();

	public ArrayList<String> availableSeats = new ArrayList<>();
	public ArrayList<String> bookedSeats = new ArrayList<>();

	/*
	 * Theater Constructor
	 * initializes seats and shows for the instance
	 */
	public Theater() {
		initializeSeats();
		initializeShows();
	}


	LocalDate initialdate = LocalDate.parse("2020-12-23");
	LocalDate finaldate = LocalDate.parse("2021-01-02");
	LocalDate discountNight1 = LocalDate.parse("2020-12-26");
	LocalDate discountNight2 = LocalDate.parse("2020-12-27");


	public String name = null;


	/*
	 * Helper Function to Initialize Shows
	 */
	public void initializeShows() {
		showName.add("Miracle on 34th Street");
		LocalTime first = LocalTime.parse("18:30");
		LocalTime second = LocalTime.parse("20:30");
		int i = 1;

		for(LocalDate date = initialdate; date.isBefore(finaldate.plusDays(1)); date = date.plusDays(1)) {
			Show s1 = new Show(date, first, this.availableSeats);
			Show s2 = new Show(date, second, this.availableSeats);
			shows.add(s1);
			shows.add(s2);
		}
		Reservation.shows = this.shows;
	}


	/**
	 * Helper Function to Initialize Seats
	 */
	public void initializeSeats() {
		name = "m";
		for(int i = 1; i<=150; i++) {
			String str = Integer.toString(i);
			if(i<=50) {
				leftMainSeats.add(name+str);
			}
			else if(i>50 && i<=100) {
				rightMainSeats.add(name+str);
			}
			else {
				mainSeats.add(name+str);
			}
		}

		name = "sb";
		for(int i = 1; i<=50; i++) {
			String num = Integer.toString(i);
			if(i<=25) {
				southBalconies1.add(name+num);
			}
			else {
				southBalconies2.add(name+num);
			}
		}

		//east and west balconies
		for(int i =1; i<=100; i++) {
			String num = Integer.toString(i);
			name = "wb";
			westBalconies.add(name+num);
			name = "eb";
			eastBalconies.add(name+num);
		}

		availableSeats.addAll(leftMainSeats);
		availableSeats.addAll(rightMainSeats);
		availableSeats.addAll(mainSeats);
		availableSeats.addAll(southBalconies1);
		availableSeats.addAll(southBalconies2);
		availableSeats.addAll(westBalconies);
		availableSeats.addAll(eastBalconies);
	}
	
	/*
	 * Inner Show Class
	 * implements Comparable
	 */
	class Show implements Comparable{
		public LocalDate date = null;
		public LocalTime time = null;
		public ArrayList<String> availableSeats; 

		/*
		 * Show constructor creates a show with given date, time and list of available seats
		 * @param date for the show
		 * @param time for the show
		 * @param list of available seats initially for the show
		 */
		public Show(LocalDate date, LocalTime time, ArrayList<String> availableSeats) {
			this.date = date;
			this.time = time;
			this.availableSeats = new ArrayList<>(availableSeats);
		}

		@Override
		/*
		 * Compares first by date and then by time
		 */
		public int compareTo(Object o) {
			Show that = (Show) o;
			if(this.date.isBefore(that.date)) {
				return -1;
			}
			else if(this.date.isAfter(that.date)) {
				return 1;
			}
			else {
				if(this.time.isBefore(that.time)) {
					return -1;
				}
				else if(this.time.isAfter(that.time)) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
	}


}
