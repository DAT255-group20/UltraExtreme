package ultraextreme.model;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * An ingame weapon.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractWeapon {

	private BulletManager bulletManager;

	public AbstractWeapon(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}
	// bulletProdqueue.addBullet(new BasicBullet(shipPosition.getX(),
	// shipPosition.getY(), width, height, playerId));
	public abstract void fireShot(Position shipPosition, PlayerID playerId,
			Direction direction);

	protected BulletManager getBulletManager() {
		return bulletManager;
	}

}