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

	private static final int BULLET_WIDTH = 20;
	private static final int BULLET_HEIGHT = 40;
	private static float initCooldown = 0.5f;
	private float cooldown;

	public BasicWeapon(final BulletManager bulletManager) {
		super(bulletManager, ObjectName.BASIC_WEAPON);
		cooldown = initCooldown;
	}

	@Override
	public void fire(final Position shipPosition, final PlayerID playerId,
			final Rotation rotation, final float timeElapsed) {
		cooldown = cooldown - timeElapsed;
		if (cooldown < 0) {
			cooldown = cooldown + initCooldown;
			this.getBulletManager().addBullet(
					new BasicBullet(shipPosition.getX(), shipPosition.getY(),
							BULLET_WIDTH, BULLET_HEIGHT, playerId, rotation));
		}
	}

	public static float getInitCooldown() {
		return initCooldown;
	}
}
