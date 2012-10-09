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

package ultraextreme.model.entity;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;

public class WeaponPickupTest extends TestCase {
	WeaponPickup pickup;

	@Override
	public void setUp() {
		pickup = new WeaponPickup(0, 0, ObjectName.BASIC_WEAPON);
	}

	@Test
	public void testWeaponPickupDoubleDoubleObjectName() {
		assertEquals(pickup.getPosition(), new Position(0, 0));
		assertEquals(pickup.getHeight(), pickup.getWidth());
		assertEquals(pickup.getObjectName(), ObjectName.BASIC_WEAPON);
	}

	@Test
	public void testWeaponPickupPositionObjectName() {
		pickup = new WeaponPickup(new Position(0, 0), ObjectName.BASIC_WEAPON);
		testWeaponPickupDoubleDoubleObjectName();
	}
}
