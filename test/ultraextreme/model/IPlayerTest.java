package ultraextreme.model;

import junit.framework.TestCase;
import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.util.PlayerID;

public class IPlayerTest extends TestCase {

	public void testGetPlayerShip() {
		IPlayer player = new Player(PlayerID.PLAYER1, new BulletProductionQueue());
		AbstractEntity playerShip = player.getShip();
		assertTrue(playerShip == player.getShip());
	}
	
}