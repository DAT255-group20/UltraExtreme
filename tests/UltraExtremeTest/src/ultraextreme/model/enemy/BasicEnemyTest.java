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

import ultraextreme.model.util.Position;

/**
 * @author Johan Gronvall
 */
public class BasicEnemyTest extends AbstractEnemyTest {

	/**
	 * Tests if the enemy has moved forward and test if he has shot after a
	 * period of 10seconds
	 * 
	 */
	public void testUpdate() {
		Position position1 = enemy.getShip().getPositionClone();
		enemy.update(10f);
		assertTrue(position1.getY() < enemy.getShip().getPositionClone().getY());
		assertTrue(!bulletManager.getBullets().isEmpty());
	}
	
	public void testGetScoreValue()
	{
		fail("Not yet tested");
	}
}
