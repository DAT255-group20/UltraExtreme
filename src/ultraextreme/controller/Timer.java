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
	 * @param time
	 * @param o
	 * @param repeats
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
	
	public void addListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	public void removeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
	}
}
