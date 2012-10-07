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

import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.item.ItemBar;
import ultraextreme.model.util.PlayerID;

/**
 * An interface for a player.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public interface IPlayer extends PropertyChangeListener {

	/**
	 * Return a reference to the player's ship.
	 */
	PlayerShip getShip();

	/**
	 * Return the ID of the player.
	 * 
	 * @return The player's ID.
	 */
	PlayerID getPlayerId();

	/**
	 * @return The score of the player.
	 */
	int getScore();

	/**
	 * @return A reference to the player's item bar
	 */
	// TODO Should not return the item bar itself, but instead an interface of the item bar or something
	public ItemBar getItemBar();

	/**
	 * @return The number of lives the player has left.
	 */
	public int getLives();
}