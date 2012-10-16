package ultraextreme.model.item;

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * @author Viktor Anderling
 */
public class SpreadWeapon extends AbstractWeapon {

	private static final int BULLET_WIDTH = 20;
	private static final int BULLET_HEIGHT = 40;
	private static final int BULLET_DAMAGE = 10;
	private static final double angleDelta = Math.PI/8;
	private static final int numberOfShots = 4;
	private static final float initCooldown = 0.5f;
	private float cooldown;
	
	public SpreadWeapon(BulletManager bulletManager) {
		super(bulletManager, ObjectName.BASIC_SPREAD_WEAPON);
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
								BULLET_WIDTH, BULLET_HEIGHT, playerId, rotation, BULLET_DAMAGE));
				shotToFire = shotToFire - 1;
			}
			for(int i = 1; i <= shotToFire / 2; i++) {
				this.getBulletManager().addBullet(
						new BasicBullet(shipPosition.getX(), shipPosition.getY(), 
								BULLET_WIDTH, BULLET_HEIGHT, playerId, 
								new Rotation(rotation.getAngle() + angleDelta * i), BULLET_DAMAGE));
				
				this.getBulletManager().addBullet(
						new BasicBullet(shipPosition.getX(), shipPosition.getY(), 
								BULLET_WIDTH, BULLET_HEIGHT, playerId, 
								new Rotation(rotation.getAngle() - angleDelta * i), BULLET_DAMAGE));
			}
		}
	}
	
	public static float getInitCooldown() {
		return initCooldown;
	}

	@Override
	public AbstractWeapon shallowClone() {
		return new SpreadWeapon(this.getBulletManager());
	}


}
