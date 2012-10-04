package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * An abstract class representing a generic Bullet.
 * 
 * @author Bjorn Persson Mattsson
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractBullet extends AbstractEntity implements IBullet {

	// What player shot this bullet
	private final PlayerID playerId;

	private boolean markedForRemoval = false;

	private static double speedMod = Constants.getBulletSpeedModifier();

	/**
	 * 
	 * @param playerId
	 *            The owner of the bullet.
	 * @param direction
	 *            The direction the bullet is shot at.
	 */
	public AbstractBullet(final double x, final double y, final int width,
			final int height, PlayerID playerId, Rotation rotation,
			final ObjectName bulletType) {
		super(x, y, width, height, rotation, bulletType);
		this.playerId = playerId;
	}

	/**
	 * Executes the bullets movement algorithm.
	 * 
	 * @param timePassed
	 *            Time passed since last update.
	 */
	public abstract void doMovement(float timePassed);

	@Override
	public PlayerID getPlayerId() {
		return playerId;
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
	}

	@Override
	public void markForRemoval() {
		markedForRemoval = true;
	}

	@Override
	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}

	@Override
	public int getDamage() {
		return 10;
	}
}