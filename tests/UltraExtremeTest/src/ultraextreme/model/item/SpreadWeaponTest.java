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

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * @author Viktor Anderling
 */
public class SpreadWeaponTest extends TestCase {

	private BulletManager bulletManager;
	private SpreadWeapon spreadWeapon;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resetInstanceVariables();
	}

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		spreadWeapon = new SpreadWeapon(bulletManager);
	}

	@Test
	public void testFire() {
		float epsilon = 0.001f;
		assertTrue(bulletManager.getBullets().size() == 0);
		float cooldown = SpreadWeapon.getInitCooldown();

		spreadWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 4);

		spreadWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 8);

		spreadWeapon.fire(new Position(), PlayerID.PLAYER1, new Rotation(0),
				cooldown * (1 + epsilon));
		assertTrue(bulletManager.getBullets().size() == 12);
	}

	/**
	 * Test so the properties of the bullet that the weapon fires are correct.
	 */
	public void testBulletProperties() {
		float cooldown = SpreadWeapon.getInitCooldown();
		spreadWeapon.fire(new Position(10, 5), PlayerID.PLAYER1,
				new Rotation(0), cooldown + cooldown / 1000);
		IBullet bullet = bulletManager.getBullets().get(0);
		assertTrue(bullet instanceof BasicBullet);
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);

		List<AbstractBullet> bulletList = bulletManager.getBullets();

		// Make sure the bullets move a bit.
		for (AbstractBullet b : bulletList) {
			b.doMovement(0.1f);
		}

		// Check so that the bullets are not along the same line.
		double epsilon = 0.00001;
		for (AbstractBullet b1 : bulletList) {
			for (AbstractBullet b2 : bulletList) {
				if (b1 != b2) {
					assertFalse(Math.abs(b1.getPositionClone().getX()
							- b2.getPositionClone().getX()) < epsilon);
				}
			}
		}
	}

	@Test
	public void testGetNewInstance() {
		AbstractWeapon s2 = spreadWeapon.getNewInstance();
		assertTrue(s2 instanceof SpreadWeapon);
		assertNotSame(s2, spreadWeapon);
	}
}
