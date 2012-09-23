package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;

/**
 * An abstract class representing a generic Bullet.
 * 
 * @author Bjorn Persson Mattsson, Viktor Anderling
 * 
 */
public abstract class AbstractBullet extends AbstractEntity implements IBullet {

	// What player shot this bullet
	private PlayerID playerId;

	private static double speedMod = Constants.getInstance()
			.getBulletSpeedModifier();

	/**
	 * 
	 * @param playerId
	 *            The owner of the bullet.
	 * @param direction
	 *            The direction the bullet is shot at.
	 */
	public AbstractBullet(double x, double y, int width, int height,
			PlayerID playerId, Direction direction) {
		super(x, y, width, height, direction);
		this.playerId = playerId;
	}

	/**
	 * Executes the bullets movement algorithm.
	 * 
	 * @param timePassed
	 *            Time passed since last update.
	 */
	public abstract void doMovement(float timePassed);

	public PlayerID getPlayerId() {
		return playerId;
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
	}
}