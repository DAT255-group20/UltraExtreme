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
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * An interface for a Bullet
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IBullet extends IEntity {

	@Override
	Position getPositionClone();

	@Override
	int getWidth();

	@Override
	int getHeight();

	/**
	 * @return The rotation of the bullet.
	 */
	Rotation getRotation();

	/**
	 * @return The ID of the player that shot the bullet.
	 */
	PlayerID getPlayerId();

	/**
	 * @return true if the bullet is marked for removal.
	 */
	boolean isMarkedForRemoval();

	/**
	 * @return The amount of damage that this bullet inflicts.
	 */
	int getDamage();

	/**
	 * Marks this bullet for removal.
	 */
	void markForRemoval();
}