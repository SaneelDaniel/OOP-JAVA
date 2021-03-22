import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
   A class for using buttons to change the size of an icon.
   
   @author danielsaneel
 */
public class ZoomInOutTester
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(frameWidth, frameHeight);
		car = new CarIcon(500);
		label = new JLabel(car);
		
		Container cpane = frame.getContentPane();
		
		//Buttons
		JButton zoomIn = new JButton("Zoom In");
		JButton zoomOut = new JButton("Zoom Out");
		
		//Add Action Listeners
		zoomIn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						int width = car.getIconWidth() + 20;

						car.setIconWidth(width);
						label.repaint();
					}
				}
				);
		zoomIn.setAlignmentX(550);
		zoomIn.setAlignmentY(450);
	
		zoomOut.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						int width = car.getIconWidth() - 30;

						car.setIconWidth(width);
						label.repaint();
					}
				}
				);
		zoomOut.setAlignmentX(550);
		zoomOut.setAlignmentY(450);
		//Action Listeners Added

		//Set Frame Options
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.CENTER);
		frame.add(zoomIn, BorderLayout.NORTH);
		frame.add(zoomOut, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private static CarIcon car;
	private static JLabel label;
	private static final int frameWidth = 700;
	private static final int frameHeight = 500;

}