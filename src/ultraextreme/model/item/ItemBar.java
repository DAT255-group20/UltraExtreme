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

package ultraextreme.model.item;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * An inventory containing weapons and bombs.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Viktor Anderling
 * 
 */
public class ItemBar {

	/**
	 * Maximum number of items that the item bar can hold.
	 */
	private int maxNumberOfItems;

	/**
	 * Where the next item will be put into the item bar.
	 */
	private int markerPosition;

	/**
	 * A list of all items in the item bar.
	 */
	private List<AbstractWeapon> items;

	/**
	 * The player's ID that the item bar belongs to.
	 */
	private PlayerID playerId;

	private Rotation playerRotation;

	/**
	 * The classes that listen to the item bar.
	 */
	private List<ItemBarUpdateListener> listeners = new ArrayList<ItemBarUpdateListener>();

	// private Bomb bomb;

	// private BulletManager bulletManager;

	/**
	 * Create an item bar.
	 * 
	 * @param playerId
	 *            The player's ID it belongs to.
	 * @param bulletManager
	 *            Reference to the bullet manager.
	 */
	public ItemBar(final PlayerID playerId, BulletManager bulletManager,
			final Rotation playerRotation) {
		this(playerId, bulletManager, playerRotation, 1);
	}

	/**
	 * Create an item bar.
	 * 
	 * @param playerId
	 *            The player's ID it belongs to.
	 * @param bulletManager
	 *            Reference to the bullet manager.
	 * @param maxNumberOfItems
	 *            Maximum number of items that fit in the item bar.
	 */
	public ItemBar(final PlayerID playerId, BulletManager bulletManager,
			Rotation playerRotation, int maxNumberOfItems) {
		// TODO PMD: Avoid passing parameters to methods or constructors and
		// then not using those parameters. (bulletManager)
		this.playerId = playerId;
		// this.bulletManager = bulletManager;
		this.items = new ArrayList<AbstractWeapon>();
		this.maxNumberOfItems = maxNumberOfItems;
		this.playerRotation = playerRotation;
		this.markerPosition = 0;
	}

	/**
	 * Add an item to the item bar.
	 * 
	 * @param item
	 *            The item that should be added.
	 */
	public void addItem(AbstractWeapon item) {
		if (items.size() < maxNumberOfItems) {
			items.add(item);
		} else {
			items.set(markerPosition, item);
		}
		markerPosition++;
		markerPosition = markerPosition % maxNumberOfItems;
		fireItemBarUpdated();
	}

	/**
	 * Get the current position of the marker. First position = 0 and last
	 * position is the item bar's size minus 1.
	 * 
	 * @return Position of the marker.
	 */
	public int getMarkerPosition() {
		return markerPosition;
	}

	public void loseItems() {
		if (!items.isEmpty()) {
			items.remove(items.size() - 1);
			markerPosition = items.size();
			fireItemBarUpdated();
		}
	}

	/**
	 * Tell the listeners that this item bar has been updated.
	 */
	private void fireItemBarUpdated() {
		for (ItemBarUpdateListener listener : listeners) {
			listener.itemBarUpdated(this);
		}
	}

	/**
	 * Return a list of the items in the item bar.
	 * 
	 * @return A list of the items in the item bar.
	 */
	public List<AbstractWeapon> getItems() {
		return this.items;
	}

	/**
	 * Fires all the weapons in the ItemBar
	 * 
	 * @param firePositon
	 *            From where the guns should be fired.
	 */
	public void fireWeapons(Position firePosition, float timeElapsed) {
		for (AbstractWeapon weapon : items) {
			weapon.fire(firePosition, playerId, playerRotation, timeElapsed);
		}
	}

	/**
	 * Add a listener that wants to know when the item bar is updated.
	 * 
	 * @param listener
	 *            The listener to be added.
	 */
	public void addListener(ItemBarUpdateListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * Remove a listener from the item bar.
	 * 
	 * @param listener
	 *            The listener to be removed.
	 */
	public void removeListener(ItemBarUpdateListener listener) {
		this.listeners.remove(listener);
	}
}
