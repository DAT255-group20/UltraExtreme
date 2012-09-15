package ultraextreme.model.entity;

/**
 * An abstract class representing a generic HomingBullet.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractHomingBullet extends AbstractBullet {

	protected Entity target;

	/**
	 * Sets the target for the homing bullet.
	 * 
	 * @param target
	 *            Target
	 */
	public void setTarget(Entity target) {
		this.target = target;
	}
}