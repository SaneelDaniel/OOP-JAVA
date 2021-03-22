

import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
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

		// add mouse listener and attach it to barFrame
		barFrame.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {

				double x = e.getX();
				double y = e.getY();

				double barNo = (y / (double)barFrame.getHeight()) * data.size();
				ArrayList<Double> temp = new ArrayList<>(model.data);
				Collections.sort(temp);
				double currentMaxBar = temp.get(temp.size()-1);
				double value = (x / (double) barFrame.getWidth()) * currentMaxBar;

				if (barNo > 0 && barNo < 1) {
					model.update(0, value);
				}

				else if (barNo > 1 && barNo < 2) {
					model.update(1, value);
				} else if (barNo > 2 && barNo < 3) {
					model.update(2, value);
				} else {
					model.update(3, value);
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
