package ultraextreme.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A timer that is dependent on an external update.
 * When it fires a change, it will return the property name, the given 
 * object as the newValue and oldValue will always return null.
 * 
 * @author Viktor Anderling
 *
 */
public class Timer {

	/**
	 * The number of runs left.
	 */
	private int runs;
	
	/**
	 * The elapsed time since last repeat.
	 */
	private float timeElapsed;
	
	/**
	 * The amount of time it takes for a fire change.
	 */
	private float totalTime;
	
	private PropertyChangeSupport pcs;
	
	/**
	 * The name of this timers property.
	 */
	private String propertyName;
	
	/**
	 * The object that is connected to the property.
	 */
	private Object object;
	
	private boolean isRunning;
	
	/**
	 * Creates a timer that has a property, a time, an object and a number of runs.
	 * 
	 * @param propertyName
	 * 					The name of the property.
	 * @param totalTime 
	 * 					The time before a property change.
	 * @param o
	 * 					The object that is related to the property change.
	 * @param runs
	 * 					How many times the timer will run.
	 * 
	 * @throws IllegalArgumentException if the number of given runs are less than one.
	 */
	public Timer(String propertyName, float totalTime, Object o, int runs) throws IllegalArgumentException {
		if(runs < 1) {
			throw new IllegalArgumentException("Must run at least one time");
		}
		this.propertyName = propertyName;
		this.object = o;
		pcs = new PropertyChangeSupport(this);
		this.runs = runs;
		this.isRunning = true;
		this.totalTime = totalTime;
	}
	
	/**
	 * Creates a timer which only runs one time.
	 */
	public Timer(String propertyName, float time, Object o) {
		this(propertyName, time, o, 1);
	}
	
	/**
	 * Updates the timer with the time that has elapsed since the last update.
	 * If the totalTime has been reached, and the timer is still running, it 
	 * will fire a propertyChange.
	 * 
	 * @param timeElapsed
	 * 					The time that has elapsed since last update.
	 */
	public void update(float timeElapsed) {
		if(isRunning) {
			this.timeElapsed += timeElapsed;
			if(this.timeElapsed >= totalTime) {
				pcs.firePropertyChange(propertyName, null, object);
				runs -= 1;
			}
			if(runs == 0) {
				isRunning = false;
			} else {
				this.timeElapsed -= totalTime;
			}
		}
	}
	
	/**
	 * Adds a PropertyChangeListener to the timer.
	 * 
	 * @param pcl
	 * 			The PropertyChangeListener that is to be added.
	 */
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	/**
	 * Removes a PropertyChangeListener from the timer.
	 * 
	 * @param pcl
	 * 			The PropertyChangeListener that is to be removed.
	 */
	public void removeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}
}
