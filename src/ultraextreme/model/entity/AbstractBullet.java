package ultraextreme.model.entity;

import ultraextreme.model.util.BulletID;
import ultraextreme.model.util.PlayerID;

/**
 * An abstract class representing a generic Bullet.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractBullet extends AbstractEntity implements IBullet {
	
	//What player shot this bullet
	private PlayerID playerId;
	
	//The name of this bullet
	private BulletID bulletId;
	
	public AbstractBullet(PlayerID playerId, BulletID bulletId) {
		this.playerId = playerId;
		this.bulletId = bulletId;
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
	
	public BulletID getBulletID() {
		return bulletId;
	}
}