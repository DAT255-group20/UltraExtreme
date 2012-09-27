package ultraextreme.model.item;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * An ingame weapon.
 * 
 * @author Bjorn Persson Mattsson
 * @author Viktor Anderling
 * 
 */
public abstract class AbstractWeapon {

	private BulletManager bulletManager;
	private ObjectName objectName;
	
	public AbstractWeapon(BulletManager bulletManager, ObjectName objectName) {
		this.bulletManager = bulletManager;
		this.objectName = objectName;
	}

	// bulletProdqueue.addBullet(new BasicBullet(shipPosition.getX(),
	// shipPosition.getY(), width, height, playerId));
	public abstract void fire(Position shipPosition, PlayerID playerId,
			Rotation rotation, float timeElapsed);

	protected BulletManager getBulletManager() {
		return bulletManager;
	}
	public ObjectName getName() {
		return objectName;
	}
}