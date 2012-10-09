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

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * An abstract class representing an in-game "physical" entity.
 * 
 * @author Bjorn Persson Mattsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractEntity implements IEntity {

	private Position position;

	private Position prevPosition;

	private Rotation rotation;

	private int width;

	private int height;

	private ObjectName objectName;

	/**
	 * Creates a nameless entity at the position 0,0 and with the side 0
	 */
	public AbstractEntity() {
		this(0, 0, 0, 0, new Rotation(0), null);
	}

	public AbstractEntity(final double x, final double y, final int width,
			final int height, Rotation rotation, final ObjectName objectName) {
		this.rotation = rotation;
		this.position = new Position(x, y);
		this.prevPosition = new Position(x, y);
		this.width = width;
		this.height = height;
		this.objectName = objectName;

	}

	public AbstractEntity(Position position, int width, int height,
			ObjectName name) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.objectName = name;
	}

	/**
	 * Move the entity a given x an y units.
	 * 
	 * @param x
	 *            Number of x units the entity shall move with.
	 * @param y
	 *            Number of y units the entity shall move with.
	 */
	public void move(double x, double y) {
		prevPosition.setPosition(position);
		final Vector2d rotatedVector = rotation.getRotatedCoordinates(x, y);
		position.setX(position.getX() + rotatedVector.x);
		position.setY(position.getY() + rotatedVector.y);
	}

	/**
	 * Sets the position of this entity to the given position.
	 * 
	 * @param position
	 *            The given position.
	 */
	public void setPosition(Position position) {
		this.position = new Position(position);
	}

	/**
	 * Returns this entity's position.
	 * 
	 * @return A new position with the same values as this position.
	 */
	@Override
	public Position getPosition() {
		return new Position(this.position);
	}

	@Override
	public Position getCenteredPosition() {
		return new Position(position.getX() + getWidth() / 2, position.getY()
				+ getHeight() / 2);
	}

	@Override
	public boolean isOutOfScreen() {
		return isOutOfScreen(0);
	}

	@Override
	public boolean isOutOfScreen(double marginal) {
		// TODO Change to fit reversed Y axis. (do tests)
		final Dimension screen = Constants.getLevelDimension();
		return position.getY() - height < -marginal
				|| position.getX() - width < -marginal
				|| position.getX() > screen.getX() + marginal
				|| position.getY() > screen.getY() + marginal;
	}

	/**
	 * Determines whether this entity collides with another entity.
	 * 
	 * @param other
	 *            The other entity.
	 * @return true if and only if the two entities are colliding.
	 */
	@Override
	public boolean collidesWith(IEntity other) {
		// Rectangle collision detection
		final double left1 = this.getPosition().getX();
		final double top1 = this.getPosition().getY();
		final double right1 = left1 + this.getWidth();
		final double bottom1 = top1 + this.getHeight();

		final double left2 = other.getPosition().getX();
		final double top2 = other.getPosition().getY();
		final double right2 = left2 + other.getWidth();
		final double bottom2 = top2 + other.getHeight();

		return !(bottom1 < top2 || top1 > bottom2 || right1 < left2 || left1 > right2);
	}

	// public void getHitbox() {
	// }

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Vector2d getNormalizedDirection() {
		Vector2d v = new Vector2d(position.getX() - prevPosition.getX(),
				position.getY() - prevPosition.getY());
		v.normalize();
		return v;
	}

	/**
	 * Returns The direction this entity are facing.
	 * 
	 * @return The direction this entity are facing.
	 */
	public Rotation getRotation() {
		return rotation;
	}

	@Override
	public ObjectName getObjectName() {
		return objectName;
	}

	public abstract double getSpeedMod();
}