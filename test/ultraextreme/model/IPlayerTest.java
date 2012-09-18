package ultraextreme.model;

import junit.framework.TestCase;
import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.util.PlayerID;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class IPlayerTest extends TestCase {

	public void testGetPlayerShip() {
		IPlayer player = new Player(PlayerID.PLAYER1, new BulletProductionQueue());
		AbstractEntity playerShip = player.getShip();
		assertTrue(playerShip == player.getShip());
	}
	
	public void testGetPlayerId() {
		IPlayer player = new Player(PlayerID.PLAYER1, new BulletProductionQueue());
		assertEquals(player.getPlayerId(), PlayerID.PLAYER1);
	}
}