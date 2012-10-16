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
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;

/**
 * 
 * @author Johan Gronvall
 * 
 */
public class AbstractEnemyTest extends TestCase {

	BulletManager bulletManager;
	AbstractEnemy enemy;

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		WeaponFactory.initialize(bulletManager);
		enemy = new BasicEnemy(5, 5);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		resetInstanceVariables();
	}

	/**
	 * Tests the isDead() method
	 */
	public void testIsDead() {
		// TODO What does this first code line do?
		assertTrue(enemy.getShip().isDestroyed() == enemy.isDead());
		enemy.getShip().receiveDamage(10000);
		assertTrue(enemy.isDead());
	}

	/**
	 * Tests the shoot() Method
	 */
	public void testShoot() {
		enemy.shoot(BasicWeapon.getInitCooldown() + 0.0000001f);
		assertTrue(!bulletManager.getBullets().isEmpty());
	}

	public void testGetShip()
	{
		fail("Not yet tested");
	}
	
	public void testGetWeapon()
	{
		fail("Not yet tested");
	}
	
	public void testShouldSpawnPickup()
	{
		fail("Not yet tested");
	}
}
