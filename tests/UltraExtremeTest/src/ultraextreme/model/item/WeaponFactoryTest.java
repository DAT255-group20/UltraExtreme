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

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.util.ObjectName;

public class WeaponFactoryTest extends TestCase {
	BulletManager manager;
	@Override
	public void setUp() {
		manager = new BulletManager();
	}
	
	@Test
	public void testInitalize() {
		boolean exceptionHasBeenThrown = false;
		try {
		WeaponFactory.getInstance();
		} catch (IllegalStateException e) {
			exceptionHasBeenThrown = true;
		}
		
		assertTrue(exceptionHasBeenThrown);
		WeaponFactory.initialize(manager);
		assertFalse(WeaponFactory.getInstance() == null);
	}

	@Test
	public void testGetNewWeapon() {
		WeaponFactory.initialize(manager);
		AbstractWeapon weapon = WeaponFactory.getInstance().getNewWeapon(ObjectName.BASIC_WEAPON);
		assertTrue(weapon != null);
		assertEquals(weapon.getName(), ObjectName.BASIC_WEAPON);
		assertTrue(weapon.getBulletManager().equals(manager));
	}

}
