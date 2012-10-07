/* ============================================================
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,
 * Viktor Anderling
 *
 * This file is part of UltraExtreme.
 *
 * UltraExtreme is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UltraExtreme is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.
 * ============================================================ */

package ultraextreme.model;

import java.beans.PropertyChangeSupport;

import junit.framework.TestCase;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.ItemBar;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class PlayerTest extends TestCase {

	private Player player;
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
		assertEquals(itemBar.getItems().size(), player.getItemBar().getItems()
				.size());
	}

	public void testGiveWeapon() {
		ItemBar itemBar = player.getItemBar();
		int preNoOfWeapons = itemBar.getItems().size();
		player.giveWeapon(new BasicWeapon(bulletManager));
		assertEquals(preNoOfWeapons, itemBar.getItems().size() - 1);
	}

	public void testScore() {
		final int scoreValue = 12;
		PropertyChangeSupport pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(player);
		IEnemy enemy = new IEnemy() {

			@Override
			public boolean shouldSpawnPickup() {
				return false;
			}

			@Override
			public boolean isDead() {
				return false;
			}

			@Override
			public AbstractWeapon getWeapon() {
				return null;
			}

			@Override
			public EnemyShip getShip() {
				return null;
			}

			@Override
			public int getScoreValue() {
				return scoreValue;
			}
		};
		assertTrue(player.getScore() == 0);

		pcs.firePropertyChange(Constants.EVENT_ENEMY_KILLED, null, enemy);
		assertTrue(player.getScore() == scoreValue);

		pcs.firePropertyChange(Constants.EVENT_ENEMY_KILLED, null, enemy);
		assertTrue(player.getScore() == 2 * scoreValue);
	}
	
	public void testReset()
	{
		fail("Not yet tested");
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
		while (itemBar.getItems().size() > 1) {
			player.getShip().receiveDamage(1);
			player.update(m, 1);
		}
		assertEquals(preLives, player.getLives());
		assertFalse(player.getShip().isDestroyed());

		player.getShip().receiveDamage(1);
		player.update(m, 1);
		assertEquals(preLives, player.getLives());
		assertFalse(player.getShip().isDestroyed());

		while (player.getLives() > 0) {
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