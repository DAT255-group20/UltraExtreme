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

package ultraextreme.model.item;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class BulletManagerTest extends TestCase {

	private BulletManager bulletManager;
	private BulletCollector bulletCollector;

	@Override
	public void setUp() {
		bulletManager = new BulletManager();
		bulletCollector = new BulletCollector();
		bulletManager.addPropertyChangeListener(bulletCollector);
	}

	private List<AbstractBullet> generateBulletList(int bullets) {
		return generateBulletList(bullets, 0, 0, PlayerID.PLAYER1);
	}

	private List<AbstractBullet> generateBulletList(int bullets,
			PlayerID playerId) {
		return generateBulletList(bullets, 0, 0, playerId);
	}

	private List<AbstractBullet> generateBulletList(int bullets, int x, int y) {
		return generateBulletList(bullets, x, y, PlayerID.PLAYER1);
	}

	private List<AbstractBullet> generateBulletList(int bullets, int x, int y,
			PlayerID playerId) {
		List<AbstractBullet> bulletList = new ArrayList<AbstractBullet>();
		for (int i = 0; i < bullets; i++) {
			AbstractBullet bullet = new BasicBullet(x, y, 0, 0, playerId,
					new Rotation(0));
			bulletList.add(bullet);
		}
		return bulletList;
	}

	/**
	 * This test adds and gets bullets from the bullet manager.
	 */
	@Test
	public void testAddBullet() {
		List<AbstractBullet> bulletList = generateBulletList(10000);
		// Add bullets to the manager
		for (int i = 0; i < bulletList.size(); ++i) {
			AbstractBullet bullet = bulletList.get(i);
			bulletManager.addBullet(bullet);
			assertTrue(bulletManager.getBullets().contains(bullet));
			assertEquals(i + 1, bulletCollector.getBullets().get(Constants.EVENT_NEW_ENTITY).size());
		}
		assertTrue(bulletManager.getBullets().containsAll(bulletList));
		assertTrue(bulletCollector.getBullets().get(Constants.EVENT_NEW_ENTITY)
				.containsAll(bulletList));
	}

	/**
	 * Add player and enemy bullets to the manager, and then check so the bullet
	 * manager's method getBulletsFrom() works correctly.
	 */
	@Test
	public void testGetBulletsFrom() {
		List<AbstractBullet> playerBullets = generateBulletList(100, PlayerID.PLAYER1);
		List<AbstractBullet> enemyBullets = generateBulletList(100, PlayerID.ENEMY);
		for (int i = 0; i < 100; ++i) {
			bulletManager.addBullet(playerBullets.get(i));
			bulletManager.addBullet(enemyBullets.get(i));
		}
		assertTrue(bulletManager.getBulletsFrom(PlayerID.PLAYER1).containsAll(
				playerBullets));
		assertEquals(playerBullets.size(),
				bulletManager.getBulletsFrom(PlayerID.PLAYER1).size());
		assertTrue(bulletManager.getBulletsFrom(PlayerID.ENEMY).containsAll(
				enemyBullets));
		assertEquals(enemyBullets.size(),
				bulletManager.getBulletsFrom(PlayerID.ENEMY).size());
	}

	/**
	 * Add bullets to the bullet manager and try to clear the bullets that are
	 * outside the screen.
	 */
	@Test
	public void testClearBulletsOffScreen() {
		// Init stuff
		List<AbstractBullet> insideBullets = generateBulletList(100, 200, 200);
		List<AbstractBullet> outsideBullets = generateBulletList(100, -200,
				-200);
		List<AbstractBullet> allBullets = new ArrayList<AbstractBullet>();
		allBullets.addAll(insideBullets);
		allBullets.addAll(outsideBullets);
		BulletCollector bulletCollector = new BulletCollector();
		bulletManager.addPropertyChangeListener(bulletCollector);

		for (AbstractBullet bullet : allBullets) {
			bulletManager.addBullet(bullet);
		}

		// Make sure that the bullets are in the manager
		assertTrue(bulletManager.getBullets().containsAll(allBullets));

		bulletManager.clearBulletsOffScreen();

		assertTrue(bulletManager.getBullets().containsAll(insideBullets));
		assertEquals(outsideBullets,
				bulletCollector.getBullets()
						.get(Constants.EVENT_REMOVED_ENTITY));
		for (AbstractBullet outsideBullet : outsideBullets) {
			assertFalse(bulletManager.getBullets().contains(outsideBullet));
		}
	}

	/**
	 * Create bullets, add them to the manager, clear them and check so they
	 * were removed.
	 */
	@Test
	public void testClearAllBullets() {
		List<AbstractBullet> bullets = generateBulletList(100);
		for (AbstractBullet bullet : bullets) {
			bulletManager.addBullet(bullet);
		}
		bulletManager.clearAllBullets();
		assertEquals(0, bulletManager.getBullets().size());
		assertEquals(bullets,
				bulletCollector.getBullets()
						.get(Constants.EVENT_REMOVED_ENTITY));
	}

	/**
	 * Add bullets from the player and an enemy to the bullet manager. Then
	 * delete the enemy's bullets and check so everything is correct. Then
	 * delete the player's bullets and check so no bullets are left.
	 */
	@Test
	public void testClearAllBulletsFrom() {
		List<AbstractBullet> playerBullets = generateBulletList(100,
				PlayerID.PLAYER1);
		List<AbstractBullet> enemyBullets = generateBulletList(100,
				PlayerID.ENEMY);
		List<AbstractBullet> allBullets = new ArrayList<AbstractBullet>();
		allBullets.addAll(playerBullets);
		allBullets.addAll(enemyBullets);

		for (AbstractBullet bullet : allBullets) {
			bulletManager.addBullet(bullet);
		}

		assertEquals(allBullets, bulletManager.getBullets());
		bulletManager.clearAllBulletsFrom(PlayerID.ENEMY);
		assertEquals(playerBullets, bulletManager.getBullets());
		assertEquals(enemyBullets,
				bulletCollector.getBullets()
						.get(Constants.EVENT_REMOVED_ENTITY));

		bulletManager.clearAllBulletsFrom(PlayerID.PLAYER1);
		assertEquals(0, bulletManager.getBullets().size());
		assertEquals(allBullets,
				bulletCollector.getBullets()
						.get(Constants.EVENT_REMOVED_ENTITY));
	}

	/**
	 * Drop some bombs and check so the the feature behaves correctly.
	 */
	@Test
	public void testDropBomb() {
		assertFalse(bulletManager.isBombDropped());
		assertFalse(bulletManager.isBombDropped());
		assertFalse(bulletManager.isBombDropped());
		bulletManager.dropBomb();
		assertTrue(bulletManager.isBombDropped());
		assertFalse(bulletManager.isBombDropped());
		assertFalse(bulletManager.isBombDropped());
		bulletManager.dropBomb();
		assertTrue(bulletManager.isBombDropped());
		assertFalse(bulletManager.isBombDropped());
		assertFalse(bulletManager.isBombDropped());
		bulletManager.dropBomb();
		assertTrue(bulletManager.isBombDropped());
		assertFalse(bulletManager.isBombDropped());
		assertFalse(bulletManager.isBombDropped());
	}

	/**
	 * Create listeners, fire a bullet and check if it's the collectors. Then
	 * remove the listeners, fire a bullet and check so it hasn't been added to
	 * the collectors.
	 */
	@Test
	public void testAddAndRemoveListener() {
		// Add collectors and add them as listeners
		List<BulletCollector> collectors = new ArrayList<BulletCollector>();
		for (int i = 0; i < 100; i++) {
			BulletCollector collector = new BulletCollector();
			collectors.add(collector);
			bulletManager.addPropertyChangeListener(collector);
		}
		// Fire a bullet
		bulletManager.addBullet(new BasicBullet(0, 0, 0, 0, PlayerID.PLAYER1,
				new Rotation(0)));
		// Check so it's in the collectors
		for (BulletCollector collector : collectors) {
			assertEquals(1,
					collector.getBullets().get(Constants.EVENT_NEW_ENTITY)
							.size());
		}
		// Remove the collectors as listeners
		for (BulletCollector collector : collectors) {
			bulletManager.removeListener(collector);
		}
		// Fire a bullet
		bulletManager.addBullet(new BasicBullet(0, 0, 0, 0, PlayerID.PLAYER1,
				new Rotation(0)));
		// Check so it hasn't been added to the collectors
		for (BulletCollector collector : collectors) {
			assertEquals(1,
					collector.getBullets().get(Constants.EVENT_NEW_ENTITY)
							.size());
		}
	}

	/**
	 * Add this as a listener to the bullet manager and collects its bullets.
	 * 
	 * @author Daniel Jonsson
	 * 
	 */
	public class BulletCollector implements PropertyChangeListener {

		private Map<String, List<IBullet>> map;

		public BulletCollector() {
			map = new HashMap<String, List<IBullet>>();
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if (!map.containsKey(event.getPropertyName())) {
				map.put(event.getPropertyName(), new ArrayList<IBullet>());
			}
			map.get(event.getPropertyName()).add((IBullet) event.getNewValue());
		}

		public Map<String, List<IBullet>> getBullets() {
			return map;
		}

	}
}
