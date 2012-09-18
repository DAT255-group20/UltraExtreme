package ultraextreme.model.entity;

import ultraextreme.model.util.PlayerID;

/**
 * A basic bullet.
 * 
 * @author Daniel Jonsson
 * 
 */
public class BasicBullet extends AbstractBullet {

	public BasicBullet(PlayerID playerId) {
		super(playerId);
	}

	@Override
	public void doMovement(float timePassed) {
		this.move(1, 1);
	}
}
