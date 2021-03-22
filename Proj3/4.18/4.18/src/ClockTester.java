import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ClockTester class to display an analog clock
 * 
 * @author danielsaneel
 *
 */
public class ClockTester {

	public static void main(String[] args) {
		// new frame
		JFrame frame = new JFrame();

		// clock icon
		ClockIcon clock = new ClockIcon(500);

		JLabel label = new JLabel(clock);
		frame.setLayout(new FlowLayout());

		// add label to the frame
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		final int DELAY = 100;
	      // Milliseconds between timer ticks
	      Timer t = new Timer(DELAY, event ->
	         {
	        	clock.setDiameter(frame.getWidth());
	            label.repaint();
	         });
	      t.start();
	}

}
