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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.entity.WeaponPickup;
import ultraextreme.model.util.Constants;

/**
 * In charge of storing and handling all pickups. (WeaponPickUpables)
 * 
 * @author Johan Gronvall
 * 
 */
public class PickupManager {
	private final List<WeaponPickup> pickups;
	private final PropertyChangeSupport pcs;

	public PickupManager() {
		pickups = new ArrayList<WeaponPickup>();
		pcs = new PropertyChangeSupport(this);
	}

	/**
	 * Adds a pickup to the list of pickups
	 * 
	 * @param pickup
	 */
	public void addPickup(final WeaponPickup pickup) {
		pickups.add(pickup);
		pcs.firePropertyChange(Constants.EVENT_NEW_ENTITY, null, pickup);

	}

	/**
	 * Removes the assigned pickup
	 * 
	 * @param pickup
	 *            item which is to be removed
	 */
	public void removePickup(final WeaponPickup pickup) {
		pcs.firePropertyChange(Constants.EVENT_REMOVED_ENTITY, null, pickup);
		pickups.remove(pickup);
	}

	/**
	 * removes the pickup at the assigned position of the list
	 * 
	 * @param index
	 *            what item is to be removed [0, n]
	 */
	public void removePickup(int index) {
		pcs.firePropertyChange(Constants.EVENT_REMOVED_ENTITY, null,
				pickups.get(index));
		pickups.remove(index);
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(
			final PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	public List<WeaponPickup> getPickups() {

		return pickups;
	}

	public void clearAllPickups() {
		for (int i = 0; i < pickups.size(); i++) {
			removePickup(i);
			i--;
		}
		pickups.clear();
	}
}
