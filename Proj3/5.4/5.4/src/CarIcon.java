

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;

/**
 * class CarIcon that implements Icon interface and creates a car
 * 
 * @author Cay Horstmann
 *
 */

public class CarIcon implements Icon {

	// width of the car
	private int width;

	/**
	 * create a car with width as parameter
	 * 
	 * @param widthOfIcon,
	 *            width of car
	 */
	public CarIcon(int widthOfIcon) {
		width = widthOfIcon;
	}

	/**
	 * set icon width
	 * 
	 * @param widthOfIcon,
	 *            width of the icon
	 */
	public void setIconWidth(int widthOfIcon) {
		width = widthOfIcon;
	}

	/**
	 * get the icon width
	 * 
	 * @return width, the icon width
	 */
	public int getIconWidth() {
		return width;
	}

	/**
	 * get Icon Height
	 * 
	 * @return height, height of the icon which is half the width
	 */
	public int getIconHeight() {
		return width / 2;
	}

	/**
	 * Draw the car. Based on the code from the Animation Program in the book.
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width - 1, width / 6);
		Ellipse2D.Double frontTire = new Ellipse2D.Double(x + width / 6, y + width / 3, width / 6, width / 6);
		Ellipse2D.Double rearTire = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3, width / 6, width / 6);
		Point2D.Double r1 = new Point2D.Double(x + width / 6, y + width / 6);
		Point2D.Double r2 = new Point2D.Double(x + width / 3, y);
		Point2D.Double r3 = new Point2D.Double(x + width * 2 / 3, y);
		Point2D.Double r4 = new Point2D.Double(x + width * 5 / 6, y + width / 6);
		Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
		Line2D.Double roofTop = new Line2D.Double(r2, r3);
		Line2D.Double rearWindshield = new Line2D.Double(r3, r4);
		g2.fill(frontTire);
		g2.fill(rearTire);
		g2.setColor(Color.GRAY);
		g2.fill(body);
		g2.draw(frontWindshield);
		g2.draw(roofTop);
		g2.draw(rearWindshield);

	}
}
