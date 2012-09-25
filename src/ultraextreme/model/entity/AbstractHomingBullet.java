package ultraextreme.model.entity;

import ultraextreme.model.util.Rotation;
import ultraextreme.model.util.PlayerID;

/**
 * An abstract class representing a generic HomingBullet.
 * 
 * @author Bjorn Persson Mattsson, Viktor Anderling
 * 
 */
public abstract class AbstractHomingBullet extends AbstractBullet {

	public AbstractHomingBullet(double x, double y, int width, int height,
			PlayerID playerId, Rotation direction) {
		super(x, y, width, height, playerId, direction);
	}

	protected AbstractEntity target;

	/**
	 * Sets the target for the homing bullet.
	 * 
	 * @param target
	 *            Target
	 */
	public void setTarget(AbstractEntity target) {
		this.target = target;
	}
}