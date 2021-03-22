package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

/**
   Class defines a View for the Application
 
   @author danielsaneel
 *
 */
public class View {
	
	public JFrame frame;
	private int frameWidth;
	private int frameHeight;
	public JTextArea textArea;
	public JButton addButton;
	public JTextField inputText;
	
	/**
	 * 
	   creates a view object with given initial width and height and initializes the view
	 
	   @param frameWidth
	   @param frameHeight
	 */
	public View(int frameWidth, int frameHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		initFrame();
				
	}

	/**
	 * Initializer method that creates actual frames and buttons for the view, 
	 * 
	 */
	private void initFrame() {
		frame = new JFrame();
		frame.setSize(frameWidth, frameHeight);

		addButton = new JButton();
		textArea = new JTextArea(30,30);
		inputText = new JTextField();
	
		addButton.setSize(frameWidth, 20);
		addButton.setBackground(Color.GRAY);		
		addButton.setText("ADD");
		addButton.setOpaque(true);	

		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setText("Weclome To The MVC Tester" + "\n \tAuthor: Daniel Saneel" + "\n\n\n - Please Type Into the Text Field Below, Then  \n - Press The Button Above To Display ");
		
		frame.add(addButton, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);
        frame.add(inputText, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}


	/**
	 * Accessor to get the frame height
	 * @return height
	 */
	public int getFrameHeight() {
		return frameHeight;
	}

	/**
	 * Mutator to set the frame height
	 * @param frameHeight
	 */
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	/**
	 * Accessor to get the frame width
	 * @return width 
	 */
	public int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * Mutator to set the frame width
	 * @param frameWidth
	 */
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}
}
