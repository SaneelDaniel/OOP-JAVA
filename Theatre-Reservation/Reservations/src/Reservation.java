import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * 
 * @author danielsaneel
 * @version 02-15-2020 1.0
 * Class Reservation, handles most of the back end functions, 
 * Acts as a intermediate class between other objects
 */
public class Reservation {

	public static String loggedIn = null;
	public static TreeMap<String, String> idPass = new TreeMap<>();
	public static ArrayList<User> users = new ArrayList<>();
	public static ArrayList<Theater.Show> shows = new ArrayList<>();
	User loggedInUser = null;


	/**
	 * Start of the function/user Interface Screen 1
	 */
	public void mainMenu() {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Sign [U]p	Sign [I]n	E[X]it");

		String input = scnr.next();

		if(input.equals("U") || input.equals("u")) {
			signUp();
		}
		else if(input.equals("I") || input.equals("i")) {
			signIn();
		}
		else if(input.equals("X") || input.equals("x")) {
			try {
				exit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println(":: INVALID ENTRY ::");
			mainMenu();
		}

	}


	/**
	 * Run the Sign Up Screen
	 * registers a user on the system
	 */
	private void signUp() {
		Scanner scnr = new Scanner(System.in);

		System.out.println("Please Enter A Unique User Id: ");
		String id = scnr.next();
		if(Reservation.idPass.containsKey(id)) {
			System.out.println(":: User Id Already In Use, Please Select An Unique UserId ::");
			signUp();
		}
		System.out.println("Please Enter A Strong Password");
		String pass = scnr.next();
		User user1 = new User(id, pass);
		Reservation.idPass.put(id, pass);
		Reservation.users.add(user1);
		mainMenu();
	}


	/**
	 * Run the Sign In Screen/Options
	 * validates the user and lets him further in the system
	 */
	private void signIn() {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Please Enter Your User Id");
		String id = scnr.next();
		System.out.println("Please Enter Your Password");
		String pass = scnr.next();
		if(idPass.containsKey(id)) {
			if(Reservation.idPass.get(id).equals(pass)) {
				Reservation.loggedIn = id;
				//scnr.close();
				User user = new User();

				for(int i = 0; i <users.size(); i++) {
					user = users.get(i);
					if(users.get(i).id.equals(loggedIn)) {
						break;
					}
				}

				user.myTempBooking = new TreeMap<Theater.Show, ArrayList<String>>();
				transactionMode();
			}
			else {
				System.out.println(":: INVALID PASSWORD ::");
				mainMenu();
			}
		}
		else {
			System.out.println(":: INVALID CREDENTIALS OR NO SUCH USER EXISTS ::");
			mainMenu();
		}
	}

	/**
	 * Runs The Transaction Mode Operations
	 * [R]eserve function
	 * [V]iew function
	 * [Cancel] function
	 * [Out] function
	 */
	private void transactionMode() {
		Scanner scnr = new Scanner(System.in);
		System.out.println("[R]eserve\t[V]iew\t[C]ancel\t[O]ut");
		String input = scnr.next();



		for(int i = 0;i<users.size(); i++) {
			loggedInUser = users.get(i);
			if(loggedIn.equals(loggedInUser.id)) {
				break;
			}
		}
		ArrayList<String> myCurrentBookings;		//the currently logged in user's temp booking list

		// reserve
		if(input.equals("R")|| input.equals("r")) {
			System.out.println("Please Enter the Date for the booking in [YYYY-MM-DD :: 2020-03-24] format: ");
			input = scnr.next();			
			LocalDate date = LocalDate.parse(input);
			System.out.println("Please Enter the desired Time for the show in [ HH:MM ] format,\n\nPlease Note Available times are \n18:30 PM and \n20:30 PM: ");
			input = scnr.next();
			LocalTime time = LocalTime.parse(input);

			System.out.println("Here is a list of all the available seats for the desired show: ");

			System.out.println("\n:: Please Note The Prices May Vary Depending on The Seating ::");

			System.out.println("Price List: \n           Seats m1-m100: $35 \n           Seats m101-m150: $45 \n           Seats sb1-sb25: $50, sb26-sb50: $55 \n           Seats wb1-wb100 & eb1-eb100: $40");

			ArrayList<String> temp = new ArrayList<>();
			Theater.Show desiredShow = null;

			//get the desired show
			for(int i = 0; i<shows.size(); i++) {
				desiredShow = shows.get(i);
				if(desiredShow.date.equals(date) && desiredShow.time.equals(time)) {
					break;
				}
			}

			temp = desiredShow.availableSeats;
			for(int i=0 ; i <desiredShow.availableSeats.size(); i++) {
				System.out.print(temp.get(i) + "\t");
			}
			System.out.println("\n");
			temp = new ArrayList<>();

			System.out.println("Please Enter your Desired Seats one by one: \n[NOTE, Enter 'T' when done selecting seats]");

			while(1==1) {
				System.out.println("Seat: ");
				input = scnr.next();
				if(input.equals("T") || input.equals("t")) {
					break;
				}
				if(desiredShow.availableSeats.contains(input)) {
					desiredShow.availableSeats.remove(input);
					temp.add(input);
				}
				else {
					System.out.println(":: Seat Not Available ::");
				}
			}

			User desiredUser = new User();
			for(int i = 0; i<users.size(); i++) {
				if(users.get(i).id.equals(loggedIn)) {
					desiredUser = users.get(i);
					break;
				}
			}
			desiredUser.createBooking(desiredShow,temp);

			myCurrentBookings = desiredUser.bookings.get(desiredShow);

			System.out.println("Booked Seats: ");
			for(int i = 0; i<myCurrentBookings.size(); i++) {
				System.out.println(myCurrentBookings.get(i));
			}
			System.out.println(":: END ::");
			transactionMode();

		}
		//		View Reservation
		else if(input.equals("V") || input.equals("v")) {
			System.out.println("Type E to view Entire Reservation OR Type D to view Reservation by date");
			input = scnr.next();
			if(input.equals("d") || input.equals("D")) {

				System.out.println("Please Enter The Date to view Reservation for: ");
				input = scnr.next();
				LocalDate date = LocalDate.parse(input);
				User.viewReservationByDate(date);
			}
			else if(input.equals("e") || input.equals("E")){
				User.viewReservation();
			}
			transactionMode();
		}
		//		Cancel Reservation
		else if(input.equals("C") || input.equals("c")) {
			System.out.println("Please Enter the Date of the Show, For which to cancel the seats: ");

			input = scnr.next();
			LocalDate date = LocalDate.parse(input);
			System.out.println("Please Enter the Time of the Show, For which to cancel the seats: ");
			input = scnr.next();
			LocalTime time = LocalTime.parse(input);

			Theater.Show showToCancelSeats = null;
			for(int i = 0; i<Theater.shows.size(); i++) {
				showToCancelSeats = Theater.shows.get(i);
				if(date.equals(showToCancelSeats.date) && time.equals(showToCancelSeats.time)) {
					break;
				}
			}

			ArrayList<String> reservedSeats = loggedInUser.bookings.get(showToCancelSeats);

			if(reservedSeats.size() == 0) {
				System.out.print(":: YOU HAVE NO BOOKINGS FOR THE GIVEN SHOW ::");
				transactionMode();
			}

			System.out.println("Please select the Seats you want to cancel from the List of Seats Booked for the Show:  ");
			for(int i =0; i<reservedSeats.size(); i++) {
				System.out.print(reservedSeats.get(i) + "  ");
			}

			System.out.println("Please Enter the Seats you want to cancel and enter 'T' OR 't' to terminate:  ");

			while(1==1) {
				input = scnr.next();
				if(input.equals("T") || input.equals("t")) {
					break;
				}

				if(!reservedSeats.contains(input)) {
					System.out.println("INVALID ENTRY");
				}
				else {
					showToCancelSeats.availableSeats.add(input);
					reservedSeats.remove(input);
					loggedInUser.myTempBooking.get(showToCancelSeats).remove(input);
				}
				System.out.println("Successfully Cancelled Seat: " + input);
			}
			transactionMode();
		}
		else if(input.equals("O") || input.equals("o")){
			receiptMode(loggedInUser);
			mainMenu();
		}
		else {
			System.out.println(":: INVALID ENTRY PLEASE ENTER A VALID CHARACTER ::");
			transactionMode();
		}
	}



	/*
	 * creates and prints a receipt for the user's transaction
	 * Receipt includes only bookings from current transaction 
	 * @param User the currently logged in user
	 */
	private void receiptMode(User loggedInUser) {
		// TODO Auto-generated method stub

		Random rand = new Random(System.currentTimeMillis());

		int invoiceNumb = 10000 + rand.nextInt(20000);
		System.out.println("Confirmation Number: " + invoiceNumb);
		TreeMap<Theater.Show, ArrayList<String>> bookings = loggedInUser.myTempBooking;

		Theater t = new Theater();
		int seatCounter = 0;
		int total = 0;
		int discount = 0;

		for(Map.Entry<Theater.Show, ArrayList<String>> entry : bookings.entrySet()) {
			Theater.Show show = entry.getKey();
			int showTotal = 0;
			ArrayList<String> currentShowBookings = bookings.get(show);
			System.out.println("Show Date: " + show.date + "\tShow Time: " + show.time);
			for(int i = 0; i<currentShowBookings.size(); i++) {
				String seat = currentShowBookings.get(i);
				seatCounter++;
				if(t.leftMainSeats.contains(seat) || t.rightMainSeats.contains(i)) {
					showTotal = showTotal + 35;
					System.out.println(seat + "\t$35");
				}
				else if(t.mainSeats.contains(seat)) {
					showTotal = showTotal + 45;
					System.out.println(seat + "\t$45");
				}
				else if(t.eastBalconies.contains(seat) || t.westBalconies.contains(seat)) {
					showTotal = showTotal + 40;
					System.out.println(seat + "\t$45");
				}
				else if(t.southBalconies1.contains(seat)) {
					showTotal = showTotal + 50;
					System.out.println(seat + "\t$50");
				}
				else if(t.southBalconies2.contains(seat)) {
					showTotal = showTotal + 55;
					total = total + 55;
					System.out.println(seat + "\t$55");
				}

			}
			System.out.println("Total For The Show: " + showTotal);
			total = total + showTotal;
		}


		if(seatCounter>=5 && seatCounter<=10) {
			discount = (seatCounter *2);
			System.out.println("Discount: " + discount);

		}
		else if(seatCounter>10 && seatCounter<=20) {
			discount = (seatCounter*5);
			System.out.println("Discount: " + discount);
		}

		total = total - discount;

		System.out.println("Total For The Transaction: " + total);
	}


	/**
	 * Method to exit the program
	 * Before exit, it creates/searches for reservation.txt file 
	 * Writes all user transactions and data to the file
	 * Exit the Program
	 */
	private static void exit() throws Exception {
		// TODO Auto-generated method stub
		try {
			File file = new File("reservations.txt");
			FileWriter write = null;
			
			if(!file.exists()) {
				file.createNewFile();
				write = new FileWriter(file.getAbsoluteFile());
			}
			String path = file.getAbsolutePath();
			
			BufferedWriter writer = new BufferedWriter(write);

			for(int i = 0; i<users.size(); i++) {
				writer.write("User: " + users.get(i).id + "\n");

				User user = users.get(i);
				TreeMap<Theater.Show, ArrayList<String>> bookings = users.get(i).bookings;
				for(Map.Entry<Theater.Show, ArrayList<String>> entry: bookings.entrySet()) {
					Theater.Show show = entry.getKey();
					ArrayList<String> list = user.bookings.get(show);
					writer.write("Show Date: " + show.date +"\t" + "Show Time: " + show.time);
					for(int j = 0; j <list.size(); j++) {
						writer.write("\n"+ list.get(j));;
					}
					writer.write("\n\n");
				}
				writer.write("\n\n");
			}


			writer.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
