package ultraextreme.model.entity;

import javax.vecmath.Vector2d;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.Position;

/**
 * An abstract class representing an ingame "physical" entity.
 * 
 * @author Bjorn Persson Mattsson, Viktor Anderling
 * 
 */
public abstract class AbstractEntity {

	private Position position;
	private Direction direction;
	
	/**
	 * A positive double that impacts on the entity's movement speed.
	 */
	private double speedModifier;

	public AbstractEntity() {
		this(0, 0, Direction.UP, 1);
	}

	public AbstractEntity(double x, double y, Direction direction, double speedModifier) {
		position = new Position(x, y);
		this.direction = direction;
		this.speedModifier = speedModifier;
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
		position.setX(position.getX() + x * direction.getXMod() * speedModifier);
		position.setY(position.getY() + y * direction.getYMod() * speedModifier);
	}
	
	/**
	 * Sets the position of this entity to the given position.
	 * 
	 * @param position
	 * 					The given position.
	 */
	public void setPosition(Position position) {
		this.position = new Position(position);
	}
	
	/**
	 * Returns this entity's position.
	 * 
	 * @return
	 * 			A new position with the same values as this position.
	 */
	public Position getPosition() {
		return new Position(this.position);
	}

	/**
	 * Determines whether this entity collides with another entity.
	 * 
	 * @param other
	 *            The other entity.
	 * @return true if and only if the two entities are colliding.
	 */
	public boolean collidesWith(AbstractEntity other) {
		return false;
	}

	public void getHitboxes() {
	}
	
	/**
	 * Returns The direction this entity are facing.
	 * 
	 * @return The direction this entity are facing.
	 */
	public Direction getDirection() {
		return direction;
	}
}