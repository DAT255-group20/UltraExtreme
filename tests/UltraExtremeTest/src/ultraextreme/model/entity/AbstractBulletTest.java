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

	@Override
	protected AbstractEntity getNewAbstractEntity(double x, double y,
			int width, int height, Rotation direction) {
		return getNewAbstractBullet(x, y, width, height, PlayerID.PLAYER1,
				direction);
	}

	private AbstractBullet newBullet(PlayerID playerId) {
		return getNewAbstractBullet(10, 20, 30, 40, playerId, new Rotation(0));
	}

	public abstract void testDoMovement();

	public void testGetPlayerId() {
		AbstractBullet bullet = newBullet(PlayerID.PLAYER1);
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);

		bullet = newBullet(PlayerID.PLAYER1);
		assertEquals(bullet.getPlayerId(), PlayerID.PLAYER1);

		bullet = newBullet(PlayerID.ENEMY);
		assertEquals(bullet.getPlayerId(), PlayerID.ENEMY);
	}
	
	public void testGetDamage()
	{
		fail("Not yet tested");
	}
	
	public void testMarkForRemoval()
	{
		fail("Not yet tested");
	}
}
