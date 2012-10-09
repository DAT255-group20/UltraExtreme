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
 * This represents the bomb item that is in the player ship's inventory.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class Bomb extends AbstractWeapon {

	public Bomb(final BulletManager bulletManager) {
		super(bulletManager, ObjectName.BOMB);
	}

	@Override
	public void fire(final Position shipPosition, final PlayerID playerId,
			final Rotation rotation, final float timeElapsed) {
		// TODO Bomb.fire()

	}

	@Override
	public AbstractWeapon shallowClone() {
		return new Bomb(this.getBulletManager());
	}

}