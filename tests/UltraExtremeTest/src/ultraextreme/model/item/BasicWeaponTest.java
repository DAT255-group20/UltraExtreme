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
import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class BasicWeaponTest extends TestCase {

	BulletManager bulletManager;
	BasicWeapon basicWeapon;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resetInstanceVariables();
	}

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		basicWeapon = new BasicWeapon(bulletManager);
	}

	/**
	 * Test to fire some shots with the weapon.
	 */
	public void testFireShot() {
		float epsilon = 0.001f;
		assertTrue(bulletManager.getBullets().size() == 0);
		float cooldown = BasicWeapon.getInitCooldown();

		basicWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 1);

		basicWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 2);

		basicWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 3);
	}

	/**
	 * Test so the properties of the bullet that the weapon fires are correct.
	 */
	public void testBulletProperties() {
		float cooldown = BasicWeapon.getInitCooldown();
		basicWeapon.fire(new Position(10, 5), PlayerID.PLAYER1,
				new Rotation(0), cooldown + cooldown / 1000);
		IBullet bullet = bulletManager.getBullets().get(0);
		assertTrue(bullet instanceof BasicBullet);
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);
	}
}
