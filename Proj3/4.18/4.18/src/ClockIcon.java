

import java.awt.*;
import java.awt.geom.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Icon;

/**
 * Class ClockIcon to create a clock object and implements Icon interface
 *
 */
public class ClockIcon implements Icon {
	private int diameter;
	private int radius;
	private Point2D.Double center;

	/**
	 * creates a clock object with given width
	 * @param width
	 */
	public ClockIcon(int width) {
		diameter = width - 30;
	}

	@Override
	/**
	 * Accessor to return the height of the clock
	 */
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return diameter;
	}

	@Override
	/**
	 * return the height of clock
	 */
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return diameter;
	}
	
	/**
	 * @author Cay Horstmann
	 * method to calculate and return the co-ordinates of x and y points of the
	 * clock
	 * @param proportion, proportion of the clock
	 * @param ticks,used to get the co-ordinates
	 * @return , the point( x and y coordinates)
	 */
	private Point2D.Double endPoint(double proportion, int ticks) {
		double length = proportion * radius;
		double radians = ticks * 6 * Math.PI / 180;
		double xEnd = center.x + length * Math.sin(radians);
		double yend = center.y - length * Math.cos(radians);
		return new Point2D.Double(xEnd, yend);
	}

	/**
	 * mutator to set the diameter of the clock
	 * @param width - the width of the current frame
	 */
	public void setDiameter(int width) {
		this.diameter = width;
	}

	@Override
	/**
	 * paint a Clock Icon
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;

		
		// center of clock
		center = new Point2D.Double(x + diameter / 2, y + diameter / 2);
		// radius of the clock
		radius = (diameter / 2);
		 
		final double secondSize = 0.75;
		final double minuteSize = 0.95;
		final double hourSize = 0.55;

		Ellipse2D.Double face = new Ellipse2D.Double(x, y, diameter, diameter);
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(face);

		GregorianCalendar now = new GregorianCalendar();
		int minutes = now.get(Calendar.MINUTE);
		int seconds = now.get(Calendar.SECOND);
		int hours = now.get(Calendar.HOUR);

		hours = hours % 12;
		hours = hours * 5;
		hours = hours + (int) (5 * minutes / 60.0);

		//getting points for hands of clock
		Line2D.Double hourHand = new Line2D.Double(center, endPoint(hourSize, hours));
		Line2D.Double minuteHand = new Line2D.Double(center, endPoint(minuteSize, minutes));
		Line2D.Double secondHand = new Line2D.Double(center, endPoint(secondSize, seconds));

		//draw the hands
		g2.setColor(Color.BLACK);
		g2.draw(hourHand);
		
		g2.setColor(Color.BLACK);
		g2.draw(minuteHand);
		
		g2.setColor(Color.BLACK);
		g2.draw(secondHand);
	}

	
}
