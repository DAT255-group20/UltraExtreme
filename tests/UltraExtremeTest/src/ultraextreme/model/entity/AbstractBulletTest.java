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

import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public abstract class AbstractBulletTest extends AbstractEntityTest {

	protected abstract AbstractBullet getNewAbstractBullet(double x, double y,
			int width, int height, PlayerID playerId, Rotation direction);

	private AbstractBullet getNewAbstractBullet(PlayerID playerId) {
		return getNewAbstractBullet(10, 20, 30, 40, playerId, new Rotation(0));
	}

	@Override
	protected AbstractEntity getNewAbstractEntity(double x, double y,
			int width, int height, Rotation direction) {
		return getNewAbstractBullet(x, y, width, height, PlayerID.PLAYER1,
				direction);
	}

	public abstract void testDoMovement();

	public abstract void testGetDamage();

	public void testGetPlayerId() {
		AbstractBullet bullet = getNewAbstractBullet(PlayerID.PLAYER1);
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);

		bullet = getNewAbstractBullet(PlayerID.PLAYER1);
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);

		bullet = getNewAbstractBullet(PlayerID.ENEMY);
		assertEquals(bullet.getPlayerId(), PlayerID.ENEMY);
	}

	public void testMarkForRemoval() {
		AbstractBullet bullet = getNewAbstractBullet(PlayerID.PLAYER1);
		assertFalse(bullet.isMarkedForRemoval());
		assertFalse(bullet.isMarkedForRemoval());
		bullet.markForRemoval();
		assertTrue(bullet.isMarkedForRemoval());
		assertTrue(bullet.isMarkedForRemoval());
		bullet.markForRemoval();
		assertTrue(bullet.isMarkedForRemoval());
		assertTrue(bullet.isMarkedForRemoval());
	}
}
