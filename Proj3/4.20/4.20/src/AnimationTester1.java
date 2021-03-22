import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * 
   This program implements an animation that moves
   a car shape.
   
   @author danielsaneel
 */
public class AnimationTester1
{
	private static final int ICON_WIDTH = 1000;
	private static final int ICON_HEIGHT = 800;
	private static final int CAR_WIDTH = 100;
	
	public static void main(String[] args)
	{
		ArrayList<MoveableShape> list = new ArrayList<MoveableShape>();

		JFrame frame = new JFrame();

		int y = 20;
		for(int i = 0; i< 8; i++) {
			MoveableShape car = new CarShape(0, y, CAR_WIDTH);
			list.add(car);
			y += 100;
		}
		
		ShapeIcon icon = new ShapeIcon(list,
				ICON_WIDTH, ICON_HEIGHT);

		JLabel label = new JLabel(icon);
		
		frame.setLayout(new FlowLayout());
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		final int DELAY = 10;
		// Milliseconds between timer ticks
		Timer t = new Timer(DELAY, event ->
		{
			for(int i = 0; i<list.size(); i++) {
				MoveableShape shape = list.get(i);
				shape.move();
			}
			label.repaint();
		});
		t.start();
	}

}
