package ultraextreme.model.item;

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.Rotation;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

public class SpinningSpreadWeapon extends AbstractWeapon {

	private static final int bulletWidth = 5;
	private static final int bulletHeight = 5;
	private static final float initCooldown = 1/6f;
	private float cooldown;
	private static final double angleStep = Math.PI / 12;
	private double currentAngle;
	
	public SpinningSpreadWeapon(BulletManager bulletManager) {
		super(bulletManager);
		cooldown = initCooldown;
		currentAngle = 0;
	}

	@Override
	public void fire(Position shipPosition, PlayerID playerId,
			Rotation rotation, float timeElapsed) {
		cooldown = cooldown - timeElapsed;
		if(cooldown < 0) {
			cooldown = cooldown + initCooldown;
			this.getBulletManager().addBullet(
					new BasicBullet(shipPosition.getX(), shipPosition.getY(), 
							bulletWidth, bulletHeight, playerId, new Rotation(rotation.getAngle() + currentAngle)));
			currentAngle = currentAngle + angleStep;
		}
	}
	
	public static float getInitCooldown() {
		return initCooldown;
	}

}
