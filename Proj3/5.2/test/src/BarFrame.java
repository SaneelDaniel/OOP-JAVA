

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * create a barFrame which is also a JFrame and change Listener
 * 
 * @author Arselan
 *
 */
public class BarFrame extends JFrame implements ChangeListener {
	// create arrayList and a dataModel object
	private ArrayList<Double> a;
	private DataModel dataModel;

	// initialize icon width and height
	private static final int ICON_WIDTH = 200;
	private static final int ICON_HEIGHT = 200;

	/**
	 * create barFrame which takes a dataModel object
	 * 
	 * @param dataModel,
	 *            dataModel object
	 */
	public BarFrame(DataModel dataModel) {
		this.dataModel = dataModel;
		a = dataModel.getData();

		// set the location and layout
		setLocation(0, 200);
		setLayout(new BorderLayout());

		/**
		 * create an icon
		 */
		Icon barIcon = new Icon() {
			/**
			 * get icon width @return, icon width
			 */
			public int getIconWidth() {
				return ICON_WIDTH;
			}

			/**
			 * get icon height
			 * 
			 * @return , icon height
			 */
			public int getIconHeight() {
				return ICON_HEIGHT;
			}

			/**
			 * paint icon method to draw the rectangles in the bar graph
			 */
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;

				// color rectangle red
				g2.setColor(Color.red);

				double max = (a.get(0)).doubleValue();
				// read values in the list a
				for (Double v : a) {
					double val = v.doubleValue();
					if (val > max)
						max = val;
				}

				// height of the bar
				double barHeight = getIconHeight() / a.size();

				int i = 0;
				for (Double v : a) {
					double value = v.doubleValue();

					double barLength = getIconWidth() * value / max;

					Rectangle2D.Double rectangle = new Rectangle2D.Double(0, barHeight * i, barLength, barHeight);
					i++;
					// fill the rectangle
					g2.fill(rectangle);
				}
			}
		};

		add(new JLabel(barIcon));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}

	/**
	 * listens to the change in the event and then repaints
	 */
	public void stateChanged(ChangeEvent e) {
		a = dataModel.getData();
		repaint();
	}

}
