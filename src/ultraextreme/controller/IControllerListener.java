package ultraextreme.controller;

/**
 * Controller listener
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IControllerListener {

	/**
	 * This method is called whenever a controller event occurs.
	 * 
	 * @param event
	 */
	void controllerListenerUpdate(ControllerEvent event);
}