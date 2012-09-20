package ultraextreme.controller;

import org.andengine.entity.scene.Scene;

/**
 * All controllers should implement this class.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractController {

	/**
	 * Adds a listener to this controller.
	 * 
	 * @param listener
	 *            Listener
	 */
	public void addListener(IControllerListener listener) {
	}
	
	public abstract Scene getScene();
}