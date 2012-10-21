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
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemyManagerTest extends TestCase {

	/**
	 * Add this as a listener to the enemy manager and collects its enemies.
	 * 
	 * @author Daniel Jonsson
	 * 
	 */
	public class EnemyCollector implements PropertyChangeListener {

		private Map<String, List<AbstractEnemy>> map;

		public EnemyCollector() {
			map = new HashMap<String, List<AbstractEnemy>>();
		}

		public Map<String, List<AbstractEnemy>> getEnemies() {
			return map;
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if (!map.containsKey(event.getPropertyName())) {
				map.put(event.getPropertyName(), new ArrayList<AbstractEnemy>());
			}
			map.get(event.getPropertyName()).add(
					(AbstractEnemy) event.getNewValue());
		}

	}

	private EnemyManager enemyManager;
	private EnemyCollector collector;

	private BulletManager manager;

	@Override
	public void setUp() {
		enemyManager = new EnemyManager();
		collector = new EnemyCollector();
		enemyManager.addPropertyChangeListener(collector);
		manager = new BulletManager();
		WeaponFactory.initialize(manager);
	}

	/**
	 * Add a single enemy to the manager.
	 */
	@Test
	public void testAddEnemy() {
		AbstractEnemy enemy = new BasicEnemy(new Position());
		enemyManager.addEnemy(enemy);
		assertEquals(enemy, enemyManager.getEnemies().get(0));
		assertEquals(enemy,
				collector.getEnemies().get(Constants.EVENT_NEW_ENTITY).get(0));

		// Test to add a lot of enemies to the manager.
		List<IEnemy> addedEnemies = new ArrayList<IEnemy>();

		// Add a lot of enemies to the enemy manager and to a local list.
		for (int i = 0; i < 10000; i++) {
			AbstractEnemy e = new BasicEnemy(new Position());
			enemyManager.addEnemy(e);
			addedEnemies.add(e);
		}
		// Check so the enemy manager fired events for all added ships.
		assertTrue("Enemy manager fired events for all added enemies",
				collector.getEnemies().get(Constants.EVENT_NEW_ENTITY)
						.containsAll(addedEnemies));
		// Check so the enemy manager contains the enemies.
		assertTrue("Enemy manager has all enemies", enemyManager.getEnemies()
				.containsAll(addedEnemies));
	}

	public void testClearAllEnemies() {
		for(int i = 0; i < 10; i++)  {
			enemyManager.addEnemy(new BasicEnemy(new Position(0, 1)));
		}
		enemyManager.clearAllEnemies();
		
		assertTrue((collector.getEnemies().get(Constants.EVENT_REMOVED_ENTITY).size() == 10));
		}

	/**
	 * Test to clear a dead enemy from the manager with the method
	 * clearDeadEnemies().
	 */
	@Test
	public void testClearDeadEnemies() {
		AbstractEnemy enemy = new BasicEnemy(new Position());
		enemyManager.addEnemy(enemy);

		// kill the ship
		enemy.getShip().receiveDamage(10000);

		enemyManager.clearDeadEnemies();

		assertTrue("Enemy was killed",
				collector.getEnemies().get(Constants.EVENT_ENEMY_KILLED)
						.contains(enemy));
		assertTrue("Enemy was removed",
				collector.getEnemies().get(Constants.EVENT_REMOVED_ENTITY)
						.contains(enemy));
		assertEquals("No enemies in manager", 0, enemyManager.getEnemies()
				.size());
	}

	/**
	 * Test to clear an enemy that is outside the map.
	 */
	@Test
	public void testClearDeadEnemies2() {
		AbstractEnemy enemy = new BasicEnemy(new Position(-500, -500));
		enemyManager.addEnemy(enemy);

		enemyManager.clearDeadEnemies();

		assertNull("Enemy wasn't killed",
				collector.getEnemies().get(Constants.EVENT_ENEMY_KILLED));
		assertTrue("Enemy was removed",
				collector.getEnemies().get(Constants.EVENT_REMOVED_ENTITY)
						.contains(enemy));
		assertEquals("No enemies in manager", 0, enemyManager.getEnemies()
				.size());
	}

	/**
	 * Test to add an enemy through the manager's propertyChange method.
	 */
	@Test
	public void testPropertyChange() {
		IEnemy enemy = new BasicEnemy(new Position());
		PropertyChangeSupport pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(enemyManager);
		pcs.firePropertyChange(EnemySpawner.NEW_ENEMY, null, enemy);
		assertEquals(enemy, enemyManager.getEnemies().get(0));
		assertTrue("Enemy was added",
				collector.getEnemies().get(Constants.EVENT_NEW_ENTITY)
						.contains(enemy));
	}
}
