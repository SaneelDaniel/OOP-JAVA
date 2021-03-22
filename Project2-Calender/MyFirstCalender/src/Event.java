import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class Event  {
	
	LocalDate recurringStartDate;
	LocalDate sDate;
	LocalDate eDate;
	LocalTime sTime;
	LocalTime eTime;
	String name;
	boolean recurring = false;
	String rdays = null;
	
	
	/*
	 * Constructor for Recurring Event
	 */
	public Event(String name, LocalDate rsDate, LocalDate sDate, LocalDate eDate, LocalTime sTime, LocalTime eTime, String days) {
		this.name = name;
		recurringStartDate = rsDate;
		this.sDate = sDate;
		this.eDate = eDate;
		this.sTime = sTime;
		this.eTime = eTime;
		recurring = true;
		rdays = days;
	}
	
	public Event(String name, LocalDate date, LocalTime stime, LocalTime etime) {
		this.name = name;
		this.sDate = date;
		this.sTime = stime;
		this.eTime = etime;
	}
}
