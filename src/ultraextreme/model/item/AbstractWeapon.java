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

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * An ingame weapon.
 * 
 * @author Bjorn Persson Mattsson
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractWeapon {

	private final BulletManager bulletManager;
	private final ObjectName objectName;

	public AbstractWeapon(final BulletManager bulletManager,
			final ObjectName objectName) {
		this.bulletManager = bulletManager;
		this.objectName = objectName;
	}

	// bulletProdqueue.addBullet(new BasicBullet(shipPosition.getX(),
	// shipPosition.getY(), width, height, playerId));
	public abstract void fire(Position shipPosition, PlayerID playerId,
			Rotation rotation, float timeElapsed);

	protected BulletManager getBulletManager() {
		return bulletManager;
	}

	public ObjectName getName() {
		return objectName;
	}

	/**
	 * @return A new weapon of the same sort as this weapon.
	 */
	public abstract AbstractWeapon getNewInstance();

}
