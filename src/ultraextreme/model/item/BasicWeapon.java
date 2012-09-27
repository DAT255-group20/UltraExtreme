package ultraextreme.model.item;

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Viktor Anderling
 *
 */
public class BasicWeapon extends AbstractWeapon {

	private static final int bulletWidth = 20;
	private static final int bulletHeight = 40;
	private static float initCooldown = 0.5f;
	private float cooldown;
	
	public BasicWeapon(BulletManager bulletManager) {
		super(bulletManager, ObjectName.BASIC_WEAPON);
		cooldown = initCooldown;
	}

	@Override
	public void fire(Position shipPosition, PlayerID playerId,
			Rotation rotation, float timeElapsed) {
		cooldown = cooldown - timeElapsed;
		if(cooldown < 0) {
			cooldown = cooldown + initCooldown;
			this.getBulletManager().addBullet(
					new BasicBullet(shipPosition.getX(), shipPosition.getY(), 
							bulletWidth, bulletHeight, playerId, rotation));
		}
	}
	
	public static float getInitCooldown() {
		return initCooldown;
	}
}
