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

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * Class representing a hostile entity which can be destroyed by the player
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class EnemyShip extends AbstractDestroyableEntity {

	private int hitPoints;

	public EnemyShip(final double x, final double y, final int width,
			final int height, final Rotation rotation, int hitpoints,
			ObjectName objectName) {
		super(x, y, width, height, rotation, objectName);
		this.hitPoints = hitpoints;
	}

	public EnemyShip(Position position, int width, int height, int hitPoints,
			ObjectName name) {
		super(position, width, height, new Rotation(0), name);
		this.hitPoints = hitPoints;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isDestroyed() {
		return hitPoints < 1;
	}

	/**
	 * Damages an enemy by subtracting the given parameter from the Enemy's
	 * hitPoints
	 * 
	 * @param damage
	 *            how many hitPoints are to be lost
	 */
	@Override
	public void receiveDamage(int damage) {
		hitPoints -= damage;
	}
}