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
	private static final double ANGLE_DELTA = Math.PI / 8;
	private static final int NR_OF_SHOTS = 4;
	private static final float INIT_COOLDOWN = 0.5f;
	private float cooldown;

	public SpreadWeapon(BulletManager bulletManager) {
		super(bulletManager, ObjectName.SPREAD_WEAPON);
		cooldown = INIT_COOLDOWN;
	}

	@Override
	public void fire(Position shipPosition, PlayerID playerId,
			Rotation rotation, float timeElapsed) {
		cooldown = cooldown - timeElapsed;
		if (cooldown < 0) {
			cooldown = cooldown + INIT_COOLDOWN;
			int shotToFire = NR_OF_SHOTS;
			if (shotToFire % 2 == 1) {
				// Shoots one straight forward if shots are uneven
				this.getBulletManager().addBullet(
						new BasicBullet(shipPosition.getX(), shipPosition
								.getY(), BULLET_WIDTH, BULLET_HEIGHT, playerId,
								rotation, BULLET_DAMAGE));
				shotToFire = shotToFire - 1;
			}
			for (int i = 1; i <= shotToFire / 2; i++) {
				this.getBulletManager().addBullet(
						new BasicBullet(shipPosition.getX(), shipPosition
								.getY(), BULLET_WIDTH, BULLET_HEIGHT, playerId,
								new Rotation(rotation.getAngle() + ANGLE_DELTA
										* i), BULLET_DAMAGE));

				this.getBulletManager().addBullet(
						new BasicBullet(shipPosition.getX(), shipPosition
								.getY(), BULLET_WIDTH, BULLET_HEIGHT, playerId,
								new Rotation(rotation.getAngle() - ANGLE_DELTA
										* i), BULLET_DAMAGE));
			}
		}
	}

	public static float getInitCooldown() {
		return INIT_COOLDOWN;
	}

	@Override
	public AbstractWeapon getNewInstance() {
		return new SpreadWeapon(this.getBulletManager());
	}

}
