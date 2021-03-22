

import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Cay Horstmannn
 * Updated by @author danielsaneel
 	A class for testing an implementation of the Observer pattern.
 *
 */
public class ObserverTester {

	public static void main(String[] args) {
		// list of data
		ArrayList<Double> data = new ArrayList<Double>();

		// add default values
		data.add(new Double(33.0));
		data.add(new Double(44.0));
		data.add(new Double(22.0));
		data.add(new Double(22.0));

		// create data model and text frame
		final DataModel model = new DataModel(data);
		final TextFrame frame = new TextFrame(model);

		// create a barframe
		final BarFrame barFrame = new BarFrame(model);

		// add mouse listener and attach it to model
		barFrame.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {

				double x = e.getX();
				double y = e.getY();

				double barNo = (y / (double)barFrame.getHeight()) * data.size();
				ArrayList<Double> temp = new ArrayList<>(model.data);
				temp = model.getData();
				Collections.sort(temp);
				
				double currentMaxValue = temp.get(temp.size()-1);
				
				double currentValue = (x / (double) barFrame.getWidth()) * currentMaxValue;

				if (barNo > 0 && barNo < 1) {
					model.update(0, currentValue);
				}

				else if (barNo > 1 && barNo < 2) {
					model.update(1, currentValue);
				} else if (barNo > 2 && barNo < 3) {
					model.update(2, currentValue);
				} else {
					model.update(3, currentValue);
				}

				for (int i = 0; i != model.data.size(); i++) {
					String s = model.data.get(i).toString();
					frame.fieldList[i].setText(s);
				}
				frame.repaint();
			}
		});
		model.attach(barFrame);
	}
}
