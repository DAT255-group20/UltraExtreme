package ultraextreme.model.entity;

import ultraextreme.model.util.Rotation;

/**
 * An entity that can be destroyed.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class DestroyableEntity extends AbstractEntity {

	public DestroyableEntity(double x, double y, int width, int height,
			Rotation direction) {
		super(x, y, width, height, direction);
	}

	/**
	 * @return true if and only if the entity has been destroyed.
	 */
	public abstract boolean isDestroyed();
}