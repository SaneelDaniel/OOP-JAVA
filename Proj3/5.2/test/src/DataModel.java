

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.event.*;

/**
 * class data model
 * @author Arselan
 *
 */
public class DataModel {
	//arrayList that contains data and change listeners
	ArrayList<Double> data;
	ArrayList<ChangeListener> listeners;

	/**
	 * create a datamodel with arrayList of data
	 * @param d, arrayList of data
	 */
	public DataModel(ArrayList<Double> d) {
		data = d;
		listeners = new ArrayList<ChangeListener>();
	}

	/**
	 * get the data from the list
	 * @return, data
	 */
	public ArrayList<Double> getData() {
		return (ArrayList<Double>) (data.clone());
	}

	/**
	 * attach a changeListener
	 * @param c, change listener
	 */
	public void attach(ChangeListener c) {
		listeners.add(c);
	}

	/**
	 * update the location
	 * @param location, location in the graph
	 * @param value, value in the graph
	 */
	public void update(int location, double value) {
		data.set(location, new Double(value));

		//do that for all changelisteners
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * get the maximum entry
	 * @return, maximum double value
	 */
	public double getMax() {

		ArrayList<Double> clone = getData();
		Collections.sort(clone);
		return clone.get(clone.size() - 1);
	}

}
