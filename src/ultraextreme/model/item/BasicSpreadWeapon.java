package ultraextreme.model.item;

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

public class BasicSpreadWeapon extends AbstractWeapon {

	private static final int bulletWidth = 5;
	private static final int bulletHeight = 5;
	private static final double angleDelta = Math.PI/8;
	private static final int numberOfShots = 5;
	private static final float initCooldown = 1/6f;
	private float cooldown;
	
	public BasicSpreadWeapon(BulletManager bulletManager) {
		super(bulletManager);
		cooldown = initCooldown;
	}

	@Override
	public void fire(Position shipPosition, PlayerID playerId,
			Rotation rotation, float timeElapsed) {
		cooldown = cooldown - timeElapsed;
		if(cooldown < 0) {
			cooldown = cooldown + initCooldown;
			int shotToFire = numberOfShots;
			if(shotToFire % 2 == 1) { // Shoots one straight forward if shots are uneven;
				this.getBulletManager().addBullet(
						new BasicBullet(shipPosition.getX(), shipPosition.getY(), 
								bulletWidth, bulletHeight, playerId, rotation));
				shotToFire = shotToFire - 1;
			}
			for(int i = 1; i <= shotToFire / 2; i++) {
				this.getBulletManager().addBullet(
						new BasicBullet(shipPosition.getX(), shipPosition.getY(), 
								bulletWidth, bulletHeight, playerId, new Rotation(rotation.getAngle() + angleDelta)));
				
				this.getBulletManager().addBullet(
						new BasicBullet(shipPosition.getX(), shipPosition.getY(), 
								bulletWidth, bulletHeight, playerId, new Rotation(rotation.getAngle() - angleDelta)));
			}
		}
	}


}
