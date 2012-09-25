package ultraextreme.model.item;

import ultraextreme.model.util.Rotation;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * An ingame weapon.
 * 
 * @author Bjorn Persson Mattsson
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractWeapon {

	private BulletManager bulletManager;

	public AbstractWeapon(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}

	// bulletProdqueue.addBullet(new BasicBullet(shipPosition.getX(),
	// shipPosition.getY(), width, height, playerId));
	public abstract void fire(Position shipPosition, PlayerID playerId,
			Rotation rotation, float timeElapsed);

	protected BulletManager getBulletManager() {
		return bulletManager;
	}
}