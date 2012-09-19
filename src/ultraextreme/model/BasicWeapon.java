package ultraextreme.model;

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;


public class BasicWeapon extends AbstractWeapon {
	private static final int bulletWidth = 5;
	private static final int bulletHeight = 5;
	@Override
	public void fireShot(Position shipPosition, PlayerID playerId, Direction direction) {
		
		this.getBulletManager().addBullet(new BasicBullet(shipPosition.getX(), shipPosition.getY(), bulletWidth, bulletHeight, playerId, direction));
	}
}
