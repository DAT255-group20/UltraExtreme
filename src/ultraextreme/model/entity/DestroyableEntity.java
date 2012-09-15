package ultraextreme.model.entity;


/**
 * An entity that can be destroyed.
 * @author Bjorn Persson Mattsson
 *
 */
public abstract class DestroyableEntity extends Entity {

	/**
	 * @return true if and only if the entity has been destroyed.
	 */
	public abstract boolean isDestroyed();
}