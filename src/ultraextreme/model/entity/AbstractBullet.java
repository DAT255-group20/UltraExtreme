package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * An abstract class representing a generic Bullet.
 * 
 * @author Bjorn Persson Mattsson, Viktor Anderling
 * 
 */
public abstract class AbstractBullet extends AbstractEntity implements IBullet {

	// What player shot this bullet
	private PlayerID playerId;
	
	private boolean markedForRemoval = false;

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
			PlayerID playerId, Rotation rotation, ObjectName bulletType) {
		super(x, y, width, height, rotation, bulletType);
		this.playerId = playerId;
	}

	/**
	 * Executes the bullets movement algorithm.
	 * 
	 * @param timePassed
	 *            Time passed since last update.
	 */
	@Override
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