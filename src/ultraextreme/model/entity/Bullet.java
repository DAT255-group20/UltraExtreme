package ultraextreme.model.entity;

import ultraextreme.model.util.PlayerID;

public class Bullet extends AbstractBullet {

	public Bullet(PlayerID playerId) {
		super(playerId);
	}

	@Override
	public void doMovement(float timePassed) {
		this.move(1, 1);
	}
}
