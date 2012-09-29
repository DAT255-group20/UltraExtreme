package ultraextreme.model.entity;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * An abstract class representing a generic HomingBullet.
 * 
 * @author Bjorn Persson Mattsson, Viktor Anderling
 * 
 */
public abstract class AbstractHomingBullet extends AbstractBullet {

	public AbstractHomingBullet(final double x, final double y, final int width, final int height,
			PlayerID playerId, Rotation direction, final ObjectName bulletType) {
		super(x, y, width, height, playerId, direction, bulletType);
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