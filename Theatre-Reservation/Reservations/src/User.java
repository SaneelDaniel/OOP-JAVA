import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


public class User {

	public static TreeMap<Theater.Show, ArrayList<String>> myTempBooking;
	public static TreeMap<Theater.Show, ArrayList<String>> bookings;
	public String id;
	public String pass;

	/*
	 * basic constructor
	 */
	public User() {

	}

	/**
	 * Creates User instance with given id and password
	 * @param id
	 * @param password
	 */
	public User(String id, String password) {
		this.id = id;
		this.pass = password;
		myTempBooking = new TreeMap<>();
		bookings = new TreeMap<>();
	}

	/**
	 * creates booking 
	 * @param desiredShow 
	 * @param list the list of desired seats.
	 */
	public void createBooking(Theater.Show desiredShow, ArrayList<String> list) {

		if(bookings != null) {

			if(bookings.containsKey(desiredShow)) {
				ArrayList<String> booked = bookings.get(desiredShow);
				booked.addAll(list);
			}
			else {
				bookings.put(desiredShow, list);
			}
		}
		
		
		if(myTempBooking.containsKey(desiredShow)) {
			myTempBooking.get(desiredShow).addAll(list);
		}
		else {
			myTempBooking.put(desiredShow, list);
		}
		
		
		
	}

	/**
	 * @param list the list of seats to be cancelled from bookings
	 */
	public void cancelBooking(Theater.Show desiredShow, ArrayList<String> list) {
		myTempBooking.get(desiredShow).removeAll(list);
		bookings.get(desiredShow).removeAll(list);
	}

	
	public static void viewReservation() {
		for(Map.Entry<Theater.Show, ArrayList<String>> entry : bookings.entrySet()) {
			ArrayList<String> reservations = new ArrayList<	>();
			Theater.Show show = entry.getKey();
			reservations = bookings.get(show);
			System.out.println("Show Date: " + show.date + "\tShow Time: " + show.time);
			for(int i = 0; i<reservations.size(); i++ ) {
				System.out.println(reservations.get(i));
			}
			
		}
		System.out.println(":: END ::");
	}

	
	/**
	 * 
	 * @param date
	 */
	public static void viewReservationByDate(LocalDate date) {
		Theater.Show show = null;
		System.out.println("Show Date: " + date);
		for(Map.Entry<Theater.Show, ArrayList<String>> entry: bookings.entrySet()) {
			show = entry.getKey();
			if(show.date.equals(date)) {
				System.out.println("Show Time: "+ show.time);
				System.out.println("\nSeats: ");
				for(int i = 0; i<bookings.get(show).size(); i++) {
					System.out.println(bookings.get(show).get(i));
				}
			}
		}
		if(show == null) {
			System.out.println(":: Sorry No Bookings for the given date ::");
		}
		else {
			System.out.println(":: END ::");
		}
		
	}
	
}
