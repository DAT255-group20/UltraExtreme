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