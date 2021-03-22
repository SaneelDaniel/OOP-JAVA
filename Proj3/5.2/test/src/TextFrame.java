

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * text frame for the observer tester
 * 
 * @author Arselan
 *
 */
public class TextFrame extends JFrame {
	// datamodel object and array of fieldlist
	DataModel dataModel;
	JTextField[] fieldList;

	/**
	 */
	public TextFrame(DataModel d) {
		dataModel = d;

		// create container and set layout
		final Container contentPane = this.getContentPane();
		setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		ArrayList<Double> a = dataModel.getData();
		fieldList = new JTextField[a.size()];

		/**
		 * Action Listener class which creates an action listener and gets the
		 * action performed
		 */
		ActionListener l = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField c = (JTextField) e.getSource();
				int i = 0;
				int count = fieldList.length;
				while (i < count && fieldList[i] != c)
					i++;

				String text = c.getText().trim();

				try {
					double value = Double.parseDouble(text);
					dataModel.update(i, value);
				} catch (Exception exc) {
					c.setText("Error.  No update");
				}
			}
		};

		final int FIELD_WIDTH = 11;

		for (int i = 0; i < a.size(); i++) {
			JTextField textField = new JTextField(a.get(i).toString(), FIELD_WIDTH);
			textField.addActionListener(l);
			add(textField);
			fieldList[i] = textField;
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
