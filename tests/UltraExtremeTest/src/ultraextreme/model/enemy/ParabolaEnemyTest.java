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

package ultraextreme.model.enemy;

import junit.framework.TestCase;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;

public class ParabolaEnemyTest extends TestCase {
	private BulletManager manager;

	@Override
	public void setUp() {
		manager = new BulletManager();
		WeaponFactory.initialize(manager);
	}

	/**
	 * Test for a ParabolaEnemy moving in a Positive quadratic function
	 */
	public void testUpdate1() {

		Position startPos = new Position(200, 0);
		Position midPos = new Position(100, 0);
		Position endPos = new Position(0, 150);
		ParabolaEnemy enemy = new ParabolaEnemy(startPos, midPos, endPos,
				ObjectName.BASIC_WEAPON);

		boolean yWasNegative = false;
		boolean yWasPositive = false;

		for (int i = 0; i < 1000; i++) {
			// Test
			enemy.update(i * 0.1f);
			double x = enemy.getShip().getPositionClone().getX();
			double y = enemy.getShip().getPositionClone().getY();

			if (x < 200 && x > 100) {
				yWasNegative = (y <= 0);
			} else if (x < 100) {
				yWasPositive = (y >= 0);
			}
		}
		assertTrue(yWasNegative);
		assertTrue(yWasPositive);
		assertFalse(manager.getBullets().isEmpty());
	}

	/**
	 * Test for a ParabolaEnemy moving in a negative quadratic function
	 */
	public void testUpdate2() {

		Position startPos = new Position(-20, -20);
		Position midPos = new Position(0, 200);
		Position endPos = new Position(200, 0);
		ParabolaEnemy enemy = new ParabolaEnemy(startPos, midPos, endPos,
				ObjectName.SPINNING_WEAPON);
		boolean yWasNegative = false;
		boolean yWasPositive = false;

		for (int i = 0; i < 1000; i++) {
			enemy.update(i * 0.1f);
			double x = enemy.getShip().getPositionClone().getX();
			double y = enemy.getShip().getPositionClone().getY();
			if ((x > 200) && (y < 0)) {
				yWasNegative = true;
			} else if (x > 0 && y > 0) {
				yWasPositive = true;
			}
		}
		assertTrue(yWasNegative);
		assertTrue(yWasPositive);
		assertFalse(manager.getBullets().isEmpty());
	}

	public void testException() {
		Position startPos = new Position(0, 0);
		Position endPos = new Position(0, 0);
		Position midPos = new Position(1, 1);
		boolean exceptionThrown = false;
		try {
			new ParabolaEnemy(startPos, midPos, endPos,
					ObjectName.SPINNING_WEAPON);

		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
}
