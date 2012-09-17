package ultraextreme.model.entity;

import ultraextreme.model.util.PlayerID;

/**
 * An abstract class representing a generic Bullet.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractBullet extends Entity implements IBullet {

	PlayerID playerId;
	
	public AbstractBullet(PlayerID playerId) {
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
}