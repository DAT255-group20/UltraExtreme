package ultraextreme.model.entity;

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

	public AbstractEntity() {
		this(0, 0, Direction.UP);
	}
	
	public AbstractEntity(Direction direction) {
		this(0, 0, direction);
	}

	public AbstractEntity(double x, double y, Direction direction) {
		position = new Position(x, y);
		this.direction = direction;
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
		position.setX(position.getX() + x * direction.getXMod());
		position.setY(position.getY() + y * direction.getYMod());
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