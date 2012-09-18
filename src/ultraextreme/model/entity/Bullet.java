package ultraextreme.model.entity;

import ultraextreme.model.util.BulletID;
import ultraextreme.model.util.PlayerID;

/**
 * A basic bullet.
 * 
 * @author Daniel Jonsson
 * 
 */
public class Bullet extends AbstractBullet {

	public Bullet(PlayerID playerId) {
		super(playerId, BulletID.BASIC_BULLET);
	}

	@Override
	public void doMovement(float timePassed) {
		this.move(1, 1);
	}
}
