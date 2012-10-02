package ultraextreme.model;

import junit.framework.TestCase;
import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.ItemBar;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class IPlayerTest extends TestCase {

	private IPlayer player;
	private BulletManager bulletManager;
	private PlayerID playerId;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resetInstanceVariables();
	}

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		playerId = PlayerID.PLAYER1;
		player = new Player(playerId, bulletManager);
	}

	/**
	 * Test if the get method works.
	 */
	public void testGetShip() {
		AbstractEntity playerShip = player.getShip();
		assertTrue(playerShip == player.getShip());
	}
	
	/**
	 * Test if the get method works.
	 */
	public void testGetLives() {
		int lives = player.getLives();
		assertTrue(lives == player.getLives());
	}

	/**
	 * Test if it's possible to get the player ID.
	 */
	public void testGetPlayerId() {
		assertEquals(player.getPlayerId(), PlayerID.PLAYER1);
	}
	
	public void testGetItemBar() {
		ItemBar itemBar = player.getItemBar();
		itemBar.addItem(new BasicWeapon(bulletManager));
		assertEquals(itemBar.getItems().size(), player.getItemBar().getItems().size());
	}
	
	public void testGiveWeapon() {
		ItemBar itemBar = player.getItemBar();
		int preNoOfWeapons = itemBar.getItems().size();
		player.giveWeapon(new BasicWeapon(bulletManager));
		assertEquals(preNoOfWeapons, itemBar.getItems().size() - 1);
	}

	/**
	 * Test the update method with a lot of different values.
	 */
	public void testUpdate() {
		// FIXME: Doesn't test the drop bomb feature yet.
		updateTester(0, 0, false, false);
		updateTester(5, 10, false, false);
		updateTester(-5, -10, false, false);
		updateTester(0, 0, true, true);
		updateTester(5, 10, true, true);
		updateTester(-5, -10, true, true);
		updateTester(100, -100, true, true);
		
		// Testing to see if the player dies.
		ModelInput m = new ModelInput(0, 0, false, false);
		ItemBar itemBar = player.getItemBar();
		itemBar.addItem(new BasicWeapon(bulletManager));
		int nOfPreWeapons = itemBar.getItems().size();
		player.getShip().receiveDamage(1);
		player.update(m, 1);
		assertEquals(nOfPreWeapons, itemBar.getItems().size() + 1);
		this.resetInstanceVariables();
		
		itemBar = player.getItemBar();
		int preLives = player.getLives();
		itemBar.addItem(new BasicWeapon(bulletManager));
		while(itemBar.getItems().size() > 1) {
			player.getShip().receiveDamage(1);
			player.update(m, 1);
		}
		assertEquals(preLives, player.getLives());
		assertFalse(player.getShip().isDestroyed());
		
		player.getShip().receiveDamage(1);
		player.update(m, 1);
		assertEquals(preLives, player.getLives());
		assertFalse(player.getShip().isDestroyed());
		
		while(player.getLives() > 0) {
			player.getShip().receiveDamage(1);
			player.update(m, 1);
		}
		assertTrue(player.getShip().isDestroyed());
	}

	/**
	 * Helper method that runs an update on the player and checks wether his
	 * ship has moved and if his weapons have been fired.
	 * 
	 * @param dX
	 *            Delta X distance that he has moved.
	 * @param dY
	 *            Delta Y distance that he has moved.
	 * @param fireWeapons
	 *            If he has fired his guns.
	 * @param dropBomb
	 *            If he has dropped a bomb.
	 */
	private void updateTester(int dX, int dY, boolean fireWeapons,
			boolean dropBomb) {
		this.resetInstanceVariables();
		Position pOld = new Position(player.getShip().getPosition());
		ModelInput m = new ModelInput(dX, dY, fireWeapons, dropBomb);
		player.update(m, 1);
		Position pNew = player.getShip().getPosition();
		assertEquals(pOld.getX() + dX, pNew.getX());
		assertEquals(pOld.getY() + dY, pNew.getY());
		if (fireWeapons)
			assertTrue(bulletManager.getBullets().size() > 0);
		else
			assertTrue(bulletManager.getBullets().size() == 0);
		// assertEquals(bulletManager.isBombDropped(), dropBomb);
	}
}