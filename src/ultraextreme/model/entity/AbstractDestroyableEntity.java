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
import ultraextreme.model.util.Rotation;

/**
 * An entity that can be destroyed.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractDestroyableEntity extends AbstractEntity {

	public AbstractDestroyableEntity(double x, double y, int width, int height,
			Rotation direction, ObjectName objectName) {
		super(x, y, width, height, direction, objectName);
	}

	/**
	 * @return true if and only if the entity has been destroyed.
	 */
	public abstract boolean isDestroyed();

	public abstract void receiveDamage(int damage);
}