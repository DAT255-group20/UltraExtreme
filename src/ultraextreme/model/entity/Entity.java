package ultraextreme.model.entity;

import ultraextreme.model.util.Position;

/**
 * An abstract class representing an ingame "physical" entity.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class Entity {

	Position position;

	public Entity() {
		this(0, 0);
	}

	public Entity(double x, double y) {
		new Position(x, y);
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
		position.setX(position.getX() + x);
		position.setY(position.getY() + y);
	}

	/**
	 * Determines whether this entity collides with another entity.
	 * 
	 * @param other
	 *            The other entity.
	 * @return true if and only if the two entities are colliding.
	 */
	public boolean collidesWith(Entity other) {
		return false;
	}

	public void getHitboxes() {
	}

}