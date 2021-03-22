package mvc;

import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Model Class to act as the data model storage class
 * 
 * @author danielsaneel
 *
 */
public class Model {

	private ArrayList<String> data;
	private ArrayList<ChangeListener> listeners;

	/**
	 * Constructor creates a model objec
	 */
	public Model() {
		data = new ArrayList<>();
		listeners = new ArrayList<>();
	}

	/**
	 * Accessor for listeners
	 * 
	 * @return list of listeners
	 */
	public ArrayList<ChangeListener> getListeners() {
		return listeners;
	}

	/**
	 * Mutator to set a list of listeners
	 * 
	 * @param listeners the list
	 */
	public void setListeners(ArrayList<ChangeListener> listeners) {
		this.listeners = listeners;
	}

	/**
	 * Accessor for String data entered
	 * 
	 * @return list of strings
	 */
	public ArrayList<String> getData() {
		return data;
	}

	/**
	 * Mutator to set a list of string as data
	 * 
	 * @param data the list of string
	 */
	public void setData(ArrayList<String> data) {
		this.data = data;
	}

	/**
	 * adds the listener to the list
	 * 
	 * @param l the listener
	 */
	public void addChangeListeners(ChangeListener l) {
		listeners.add(l);
	}

	/**
	 * adds the input string to the data list 
	 * 
	 * @param addMe
	 */
	public void addString(String addMe) {
		data.add(addMe);
		ChangeEvent evnt = new ChangeEvent(this);
		for(ChangeListener l: listeners) {
			l.stateChanged(evnt);
		}

	}

}
