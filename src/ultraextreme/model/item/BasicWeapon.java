package ultraextreme.model.item;

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.Rotation;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

public class BasicWeapon extends AbstractWeapon {

	private static final int bulletWidth = 5;
	private static final int bulletHeight = 5;
	private static double initCooldown = 0.5;
	private double cooldown;
	
	public BasicWeapon(BulletManager bulletManager) {
		super(bulletManager);
		cooldown = initCooldown;
	}

	@Override
	public void fire(Position shipPosition, PlayerID playerId,
			Rotation direction, float timeElapsed) {
		cooldown = cooldown - timeElapsed;
		if(cooldown < 0) {
			cooldown = cooldown + initCooldown;
			this.getBulletManager().addBullet(
					new BasicBullet(shipPosition.getX(), shipPosition.getY(), 
							bulletWidth, bulletHeight, playerId, direction));
		}
	}
	
	public double getInitCooldown() {
		return initCooldown;
	}
}
