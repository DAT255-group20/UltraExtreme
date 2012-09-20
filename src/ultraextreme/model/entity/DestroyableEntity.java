package ultraextreme.model.entity;

/**
 * An entity that can be destroyed.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class DestroyableEntity extends AbstractEntity {

	public DestroyableEntity(double x, double y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * @return true if and only if the entity has been destroyed.
	 */
	public abstract boolean isDestroyed();
}