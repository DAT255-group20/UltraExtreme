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

import javax.vecmath.Vector2d;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IEntity {

	/**
	 * Determines whether this entity is colliding with another entity.
	 * 
	 * @param other
	 *            The other entity.
	 * @return true if this entity is colliding with the other entity.
	 */
	boolean collidesWith(IEntity other);

	/**
	 * @return The position of this entity.
	 */

	Position getPositionClone();

	/**
	 * @return A position at the center of this entity.
	 */
	Position getCenteredPositionClone();


	/**
	 * @return The height of this entity.
	 */
	int getHeight();

	/**
	 * @return The width of this entity.
	 */
	int getWidth();

	/**
	 * Determines whether the entity is off the screen.
	 * 
	 * @return true if and only if the entity is entirely outside of the screen
	 */
	boolean isOutOfScreen();

	/**
	 * Determines whether the entity is off the screen + the provided marginal.
	 * For an example, say that the screen is 100x150 pixels. Then, if the
	 * marginal is 0, this method would return true if and only if the entity is
	 * entirely outside a rectangle of 100x150 pixels. If the margin was 20,
	 * then the rectangle to compare with would be 120x170 pixels. If the margin
	 * was -10, the rectangle to compare with would be 90x140 pixels.
	 * 
	 * @return true if and only if the entity is entirely outside the screen
	 *         borders + the marginal
	 */
	boolean isOutOfScreen(double marginal);

	/**
	 * Returns what kind of entity this is as an ObjectName
	 * 
	 * @return what kind of entity this is as an ObjectName
	 */
	ObjectName getObjectName();

	Vector2d getNormalizedDirection();
}