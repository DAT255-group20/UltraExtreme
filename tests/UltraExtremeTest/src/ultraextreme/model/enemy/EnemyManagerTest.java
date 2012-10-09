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

package ultraextreme.model.enemy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.enemyspawning.EnemySpawner;
import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Constants;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemyManagerTest extends TestCase {

	private EnemyManager enemyManager;

	@Override
	public void setUp() {
		enemyManager = new EnemyManager();
	}

	/**
	 * Add a single enemy to the manager.
	 */
	@Test
	public void testAddEnemy() {
		EnemyCollector collector = new EnemyCollector();
		AbstractEnemy enemy = new BasicEnemy(0, 0, new BulletManager());
		enemyManager.addPropertyChangeListener(collector);
		enemyManager.addEnemy(enemy);
		assertEquals(enemy, enemyManager.getEnemies().get(0));
		assertEquals(enemy.getShip(),
				collector.getEnemyShips().get(Constants.EVENT_NEW_ENTITY));
	}

	/**
	 * Test to add a lot of enemies to the manager.
	 */
	@Test
	public void testAddEnemy2() {
		EnemyCollector collector = new EnemyCollector();
		List<IEnemy> addedEnemies = new ArrayList<IEnemy>();
		List<EnemyShip> addedShips = new ArrayList<EnemyShip>();
		BulletManager bulletManager = new BulletManager();
		enemyManager.addPropertyChangeListener(collector);
		// Add a lot of enemies to the enemy manager and to a local list.
		for (int i = 0; i < 10000; i++) {
			AbstractEnemy enemy = new BasicEnemy(0, 0, bulletManager);
			enemyManager.addEnemy(enemy);
			addedEnemies.add(enemy);
			addedShips.add(enemy.getShip());
		}
		// Check so the enemy manager fired events for all added ships.
		assertTrue(addedShips.containsAll(collector.getEnemyShips().values()));
		// Check so the enemy manager contains the enemies.
		assertTrue(addedEnemies.containsAll(enemyManager.getEnemies()));
	}

	@Test
	public void testClearDeadEnemies() {
		EnemyCollector collector = new EnemyCollector();
		AbstractEnemy enemy = new BasicEnemy(0, 0, new BulletManager());
		enemyManager.addPropertyChangeListener(collector);
		enemyManager.addEnemy(enemy);

		// kill the ship
		enemy.getShip().receiveDamage(10000);

		enemyManager.clearDeadEnemies();

		assertEquals(collector.getEnemyShips().size(), 2);
		assertEquals(enemy,
				collector.getEnemyShips().get(Constants.EVENT_REMOVED_ENTITY));
		collector.getEnemyShips().clear();

		// Now check if an enemy gets removed when it is outside the map
		enemy = new BasicEnemy(0, 0, new BulletManager());
		enemyManager.addEnemy(enemy);

		enemy.getShip().move(-500, -500);

		enemyManager.clearDeadEnemies();

		assertEquals(collector.getEnemyShips().size(), 2);
		assertEquals(enemy,
				collector.getEnemyShips().get(Constants.EVENT_REMOVED_ENTITY));
	}

	/**
	 * Test to add an enemy through the manager's propertyChange method.
	 */
	@Test
	public void testPropertyChange() {
		IEnemy enemy = new BasicEnemy(0, 0, new BulletManager());
		PropertyChangeSupport pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(enemyManager);
		pcs.firePropertyChange(EnemySpawner.NEW_ENEMY, null, enemy);
		assertEquals(enemy, enemyManager.getEnemies().get(0));
	}

	/**
	 * Add this as a listener to the enemy manager and collects its enemies.
	 * 
	 * @author Daniel Jonsson
	 * 
	 */
	public class EnemyCollector implements PropertyChangeListener {

		private Map<String, EnemyShip> map;

		public EnemyCollector() {
			map = new HashMap<String, EnemyShip>();
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			map.put(event.getPropertyName(), (EnemyShip) event.getNewValue());
		}

		public Map<String, EnemyShip> getEnemyShips() {
			return map;
		}

	}

}
