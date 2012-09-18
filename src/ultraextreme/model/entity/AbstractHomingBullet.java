package ultraextreme.model.entity;

import ultraextreme.model.util.BulletID;
import ultraextreme.model.util.PlayerID;

/**
 * An abstract class representing a generic HomingBullet.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractHomingBullet extends AbstractBullet {

	public AbstractHomingBullet(PlayerID playerId, BulletID bulletId) {
		super(playerId, bulletId);
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