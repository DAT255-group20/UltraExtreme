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