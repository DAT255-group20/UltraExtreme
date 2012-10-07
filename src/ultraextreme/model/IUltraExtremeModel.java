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

package ultraextreme.model;

import java.beans.PropertyChangeListener;

/**
 * An interface for a GameModel that only has get methods.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * 
 */
public interface IUltraExtremeModel {

	/**
	 * @return The human player in the game.
	 */
	IPlayer getPlayer();

	/**
	 * Adds a listener to the model.
	 * 
	 * @param listener
	 * 				The provided listener.
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * Registers the provided listener to the player.
	 * 
	 * @param listener
	 *            Listener
	 */
	void registerPlayerListener(IPlayerListener listener);

	/**
	 * Removes the provided listener from the player.
	 * 
	 * @param listener
	 *            Listener
	 */
	void unregisterPlayerListener(IPlayerListener listener);
	
	/**
	 * @return true if and only if the game is over.
	 */
	boolean isGameOver();
}