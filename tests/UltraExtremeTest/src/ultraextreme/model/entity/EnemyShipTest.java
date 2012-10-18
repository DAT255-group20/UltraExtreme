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

import org.junit.Test;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemyShipTest extends AbstractEntityTest {

	private EnemyShip enemyShip;

	@Override
	protected AbstractEntity getNewAbstractEntity(double x, double y,
			int width, int height, Rotation direction) {
		resetShip(x, y, width, height, 0, 5);
		return enemyShip;
	}

	private void resetShip(double x, double y, int width, int height,
			double rotation, int hitpoints) {
		enemyShip = new EnemyShip(x, y, width, height, new Rotation(rotation),
				hitpoints, ObjectName.BASIC_ENEMYSHIP);
	}

	@Override
	public void setUp() {
		resetShip(0, 0, 100, 50, 0, 10);
	}

	@Test
	public void testReceiveDamage() {
		// Create an enemy ship and kill it
		resetShip(0, 0, 100, 100, 0, 100);
		enemyShip.receiveDamage(100);
		assertTrue(enemyShip.isDestroyed());

		// Create a new enemy ship and kill it more slowly
		resetShip(0, 0, 100, 100, 0, 1000);
		enemyShip.receiveDamage(100);
		assertFalse(enemyShip.isDestroyed());
		enemyShip.receiveDamage(899);
		assertFalse(enemyShip.isDestroyed());
		enemyShip.receiveDamage(1);
		assertTrue(enemyShip.isDestroyed());

		// Kill a third enemy even more slowly
		resetShip(0, 0, 100, 100, 0, 1000);
		for (int i = 999; i > 0; --i) {
			enemyShip.receiveDamage(1);
			assertFalse(enemyShip.isDestroyed());
		}
		enemyShip.receiveDamage(1);
		assertTrue(enemyShip.isDestroyed());
	}
	
	public void testIsDestroyed()
	{
		fail("Not yet tested");
	}
	
	@Override
	@Test
	public void testGetObjectName() {
		fail("lol");
	}
}
