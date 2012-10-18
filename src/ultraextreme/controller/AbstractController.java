/* ============================================================
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,
 * Viktor Anderling
 *
 * This file is part of UltraExtreme.
 *
 * UltraExtreme is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UltraExtreme is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.
 * ============================================================ */

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

	private final List<IControllerListener> listeners = new ArrayList<IControllerListener>();

	/**
	 * Adds a listener to this controller.
	 * 
	 * @param listener
	 *            Listener
	 */
	public void addListener(final IControllerListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes the provided listener from the list if it exists.
	 * 
	 * @param listener
	 *            Listener to be removed.
	 */
	public void removeListener(final IControllerListener listener) {
		listeners.remove(listener);
	}

	/**
	 * @return The scene owned by this controller.
	 */
	public abstract Scene getScene();

	/**
	 * Fires an event and alerts all listeners to this controller.
	 * 
	 * @param event
	 *            The event that is fired.
	 */
	protected void fireEvent(final ControllerEvent event) {
		for (IControllerListener listener : listeners) {
			listener.controllerListenerUpdate(event);
		}
	}

	/**
	 * Does what is necessary to activate this controller.
	 */
	public abstract void activateController();

	/**
	 * Does what is necessary to deactivate this controller, that is make it
	 * inactive.
	 */
	public abstract void deactivateController();

	/**
	 * Android's back button has been pressed.
	 */
	public abstract void backButtonPressed();

}