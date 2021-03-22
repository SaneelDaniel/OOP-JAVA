

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *Slider tester class with main method to test 
 					the slider features, with the carIcon.
 *
 *@author danielsaneel
 */
public class SliderTester {

	private static final int frameWidth = 800;
	private static final int frameHeight = 500;
	private static final int sliderMin = -100;
	private static final int sliderMax = 100;

	
	public static void main(String[] args) {

		// create JFrame
		JFrame frame = new JFrame();
		frame.setSize(frameWidth, frameHeight);
		Container contentPane = frame.getContentPane();
		frame.setLayout(new BoxLayout(contentPane, 1)); 

		//create objects
		CarIcon car = new CarIcon(600);;
		JLabel label = new JLabel(car);
		JSlider slider = new JSlider(sliderMin, sliderMax);;

		//add change listener
		slider.addChangeListener(
				new ChangeListener() {
					public void stateChanged(ChangeEvent event) {
						int width = 0;

						if(slider.getValue()>0) { //zoom in
							width = 100 + 5*slider.getValue();
							car.setIconWidth(width);
						}
						else {	//zoom out
							width = 100 + slider.getValue();
							car.setIconWidth(width);
						}

						label.repaint();
					}
				}
				);

		
		//add everything to the frame as per the box layout
		frame.add(slider);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//set slider options
		slider.setMajorTickSpacing(20);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		frame.setVisible(true);
		frame.pack();
	}

}
