package ultraextreme.model.item;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.entity.WeaponPickup;
import ultraextreme.model.util.ObjectName;

/**
 * Test for the pickupManager
 * 
 * @author Johan Gronvall
 * 
 */
public class PickupManagerTest extends TestCase {
	PickupManager manager;
	WeaponPickup pickup;
	WeaponPickup pickup2;
	PickupCollector collector;

	@Override
	public void setUp() {

		manager = new PickupManager();
		pickup = new WeaponPickup(0, 0, ObjectName.BOMB);
		pickup2 = new WeaponPickup(0, 0, ObjectName.BASIC_WEAPON);
		collector = new PickupCollector();
		manager.addPropertyChangeListener(collector);
		manager.addPickup(pickup);
		manager.addPickup(pickup2);
	}

	@Test
	public void testAddPickup() {
		// addPickup called in setup
		assertTrue(manager.getPickups().get(0).equals(pickup));
		assertTrue(manager.getPickups().get(1).equals(pickup2));
		assertTrue(collector.getPickupMap().get("add").equals(pickup));
		//check if the map has stored an event for the secondary addPickup call
		assertTrue(collector.getPickupMap().get("additionalAdd").equals(pickup2));
	}

	@Test
	public void testRemovePickupWeaponPickup() {
		manager.removePickUp(pickup);
		assertTrue(manager.getPickups().get(0).equals(pickup2));
		assertTrue(collector.getPickupMap().get("remove").equals(pickup));
	}

	@Test
	public void testRemovePickUpInt() {
		manager.removePickUp(0);
		assertTrue(manager.getPickups().get(0).equals(pickup2));
		assertTrue(collector.getPickupMap().get("remove").equals(pickup));

	}

	@Test
	public void testGetPickups() {
		assertTrue(manager.getPickups() != null);
		assertTrue(manager.getPickups().get(0).equals(pickup));
		assertTrue(manager.getPickups().get(1).equals(pickup2));
	}

	/**
	 * Add this as a listener to the pickupManager and collects its pickups
	 * 
	 * @author Daniel Jonsson, Johan Gronvall
	 * 
	 */
	public class PickupCollector implements PropertyChangeListener {

		private Map<String, WeaponPickup> map;

		public PickupCollector() {
			map = new HashMap<String, WeaponPickup>();
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			
			//if an add has already been performed, save instead an additionalAdd in the map
			//TODO replace "add" with the static string variable
			if(map.containsKey(event.getPropertyName()) && (event.getPropertyName().equals("add"))) {
				map.put("additionalAdd", (WeaponPickup)event.getNewValue());
			} else {
				map.put(event.getPropertyName(), (WeaponPickup) event.getNewValue());
			}
		}

		public Map<String, WeaponPickup> getPickupMap() {
			return map;
		}
	}
}
