package ultraextreme.controller;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.scene.Scene;

/**
 * All controllers should implement this class.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractController {

	private List<IControllerListener> listeners = new ArrayList<IControllerListener>();
	
	/**
	 * Adds a listener to this controller.
	 * 
	 * @param listener
	 *            Listener
	 */
	public void addListener(IControllerListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes the provided listener from the list if it exists.
	 * @param listener Listener to be removed.
	 */
	public void removeListener(IControllerListener listener)
	{
		listeners.remove(listener);
	}

	public abstract Scene getScene();
	
	protected void fireEvent(ControllerEvent event)
	{
		for (IControllerListener listener : listeners)
		{
			listener.controllerListenerUpdate(event);
		}
	}
}