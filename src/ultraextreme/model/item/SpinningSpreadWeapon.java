package ultraextreme.model.item;

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 *
 */
public class SpinningSpreadWeapon extends AbstractWeapon {

	private static final int BULLET_WIDTH = 20;
	private static final int BULLET_HEIGHT = 40;
	private static float initCooldown = 1 / 6f;
	private float cooldown;
	private double angleStep;
	private double currentAngle;

	public SpinningSpreadWeapon(final BulletManager bulletManager) {
		super(bulletManager, ObjectName.SPINNING_SPREAD_WEAPON);
		cooldown = initCooldown;
		angleStep = Math.PI / 12;
		currentAngle = 0;
	}

	@Override
	public void fire(final Position shipPosition, final PlayerID playerId,
			final Rotation rotation, final float timeElapsed) {
		cooldown = cooldown - timeElapsed;
		if (cooldown < 0) {
			cooldown = cooldown + initCooldown;
			this.getBulletManager().addBullet(
					new BasicBullet(shipPosition.getX(), shipPosition.getY(),
							BULLET_WIDTH, BULLET_HEIGHT, playerId,
							new Rotation(rotation.getAngle() + currentAngle)));
			currentAngle = currentAngle + angleStep;
		}
	}

	public static float getInitCooldown() {
		return initCooldown;
	}

	@Override
	public AbstractWeapon shallowClone() {
		return new SpinningSpreadWeapon(this.getBulletManager());
	}

}
