package ultraextreme.model.item;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * This represents the bomb item that is in the player ship's inventory.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class Bomb extends AbstractWeapon{

	public Bomb(BulletManager bulletManager) {
		super(bulletManager);
	}

	@Override
	public void fireShot(Position shipPosition, PlayerID playerId,
			Direction direction) {
		// TODO Auto-generated method stub
		
	}

}
