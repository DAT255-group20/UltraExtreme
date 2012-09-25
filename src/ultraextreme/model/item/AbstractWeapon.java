package ultraextreme.model.item;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.ObjectName;
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
	private ObjectName objectName;
	
	public AbstractWeapon(BulletManager bulletManager, ObjectName objectName) {
		this.bulletManager = bulletManager;
		this.objectName = objectName;
	}

	// bulletProdqueue.addBullet(new BasicBullet(shipPosition.getX(),
	// shipPosition.getY(), width, height, playerId));
	public abstract void fireShot(Position shipPosition, PlayerID playerId,
			Direction direction);

	protected BulletManager getBulletManager() {
		return bulletManager;
	}
	
	public ObjectName getName() {
		return objectName;
	}

}