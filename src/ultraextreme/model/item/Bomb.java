package ultraextreme.model.item;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * This represents the bomb item that is in the player ship's inventory.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class Bomb extends AbstractWeapon {

	public Bomb(BulletManager bulletManager) {
		super(bulletManager, ObjectName.BOMB);
	}

	@Override
	public void fire(Position shipPosition, PlayerID playerId,
			Rotation rotation, float timeElapsed) {
		// TODO Auto-generated method stub

	}

}
