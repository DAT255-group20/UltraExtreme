package ultraextreme.model.item;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.entity.WeaponPickup;

/**
 * In charge of storing and handling all pickups. (WeaponPickUpables)
 * 
 * @author Johan Gronvall
 * 
 */
public class PickupManager {
	private final List<WeaponPickup> pickups;
	private final PropertyChangeSupport pcs;

	private static final String NEW_PICKUP = "add";
	private static final String REMOVE_PICKUP = "remove";

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
		pcs.firePropertyChange(NEW_PICKUP, null, pickup);

	}

	/**
	 * Removes the assigned pickup
	 * 
	 * @param pickup
	 *            item which is to be removed
	 */
	public void removePickUp(final WeaponPickup pickup) {
		pcs.firePropertyChange(REMOVE_PICKUP, null, pickup);
		pickups.remove(pickup);
	}

	/**
	 * removes the pickup at the assigned position of the list
	 * 
	 * @param index
	 *            what item is to be removed [0, n]
	 */
	public void removePickUp(int index) {
		pcs.firePropertyChange(REMOVE_PICKUP, null, pickups.get(index));
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
}
