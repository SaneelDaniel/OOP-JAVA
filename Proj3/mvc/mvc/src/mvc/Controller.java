

package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controller class to control 
 * Can be Changed to use listeners to make any necessary controls
 * @author danielsaneel
 *
 */
public class Controller {
	Model model;
	View view;

	/**
	 * Controller for the model and its view, 
	 * 			Currently controls the add button and the action performed for the specific view and model
	 * 
	 * @param m
	 * @param v
	 */
	public Controller(Model m, View v) {

		v.addButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String input = v.inputText.getText();
						m.addString(input);
						String formatted = format(m.getData());
						v.textArea.setText(formatted);
					}
				});
	}
	
	/**
	 * Method Formats the data as desired, 
	 * 							-> currently sets new string on new line
	 * @param data
	 * @return a single string of the list of strings
	 */
	private String format(ArrayList<String> data) {
		String formatted = "";
		for(String s: data) {
			formatted += s + "\n";
		}
		return formatted;
	}	
}


