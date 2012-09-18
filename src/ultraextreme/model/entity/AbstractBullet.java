package ultraextreme.model.entity;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;

/**
 * An abstract class representing a generic Bullet.
 * 
 * @author Bjorn Persson Mattsson, Viktor Anderling
 * 
 */
public abstract class AbstractBullet extends AbstractEntity implements IBullet {

	private PlayerID playerId;

	/**
	 * 
	 * @param playerId
	 * 					The owner of the bullet.
	 * @param direction
	 * 					The direction the bullet is shot at.
	 */
	public AbstractBullet(PlayerID playerId, Direction direction) {
		this.playerId = playerId;
	}
	
	/**
	 * Default direction is up.
	 * 
	 * @param playerId
	 * 					The owner of the bullet.
	 */
	public AbstractBullet(PlayerID playerId) {
		this(playerId, Direction.UP);
	}

	/**
	 * Executes the bullets movement algorithm.
	 * 
	 * @param timePassed
	 * 					Time passed since last update.
	 */
	public abstract void doMovement(float timePassed);

	public PlayerID getPlayerId() {
		return playerId;
	}
}