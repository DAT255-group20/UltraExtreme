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

package ultraextreme.model.enemyspawning;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.enemyspawning.wave.AbstractEnemyProvider;
import ultraextreme.model.enemyspawning.wave.AbstractWave;
import ultraextreme.model.enemyspawning.wave.RectangleWave;
import ultraextreme.model.enemyspawning.wave.VWave;
import ultraextreme.model.enemyspawning.wavelist.IWaveSpawningList;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemySpawnerTest extends TestCase {

	private BulletManager bulletManager;

	@Override
	public void setUp() {
		bulletManager = new BulletManager();
		WeaponFactory.initialize(bulletManager);
	}

	/**
	 * Create a EnemySpawner, give it a custom IWaveSpawningList and see if t
	 * behaves correctly. Which means that the EnemySpawner gets waves from the
	 * wave list and throws enemies to the listeners.
	 */
	public void testUpdateMethod() {
		IWaveSpawningList list = new IWaveSpawningList() {

			// Array with 2 different waves
			private AbstractWave[] waves = {
					new VWave(0, 0, 0),
					new RectangleWave(0, 0, 0, 0, 0,
							new AbstractEnemyProvider() {
								@Override
								public AbstractEnemy getEnemy(
										Position spawningPosition,
										Rotation rotation) {
									return new BasicEnemy(new Position(
											spawningPosition));
								}
							}) };

			// Current wave
			private int currentWave = 1;

			@Override
			public void next() {
				++currentWave;
			}

			@Override
			public boolean hasNext() {
				// FIXME not needed
				return true;
			}

			@Override
			public int getNumberOfWaves() {
				// FIXME not needed
				return 100;
			}

			@Override
			public int getCurrentWaveNumber() {
				return currentWave;
			}

			@Override
			public AbstractWave getCurrentWave() {
				return waves[currentWave % 1];
			}

			@Override
			public float getCurrentSpawningTime() {
				return currentWave;
			}
		};

		EnemySpawner enemySpawner = new EnemySpawner(list);
		EnemyCollector enemyCollector = new EnemyCollector();
		enemySpawner.addPropertyChangeListener(enemyCollector);

		assertEquals(0, enemySpawner.getCurrentWave());

		enemySpawner.update(1); // A second has passed

		assertEquals("First wave has spawned", 1, enemySpawner.getCurrentWave());
		assertEquals("First wave spawned an enemy", 1, enemyCollector
				.getSpawnedEnemies().size());

		enemySpawner.update(1); // An additional second has passed

		assertEquals("An additional wave has spawned", 2,
				enemySpawner.getCurrentWave());
		assertEquals("5 enemies has spawned in total", 5, enemyCollector
				.getSpawnedEnemies().size());

		enemySpawner.update(1); // A third second has passed

		assertEquals("A third wave has spawned", 3,
				enemySpawner.getCurrentWave());
		assertEquals("17 enemies has spawned in total", 17, enemyCollector
				.getSpawnedEnemies().size());
	}

	/**
	 * Used to collect the spawned enemies.
	 * 
	 * @author Daniel Jonsson
	 * 
	 */
	public class EnemyCollector implements PropertyChangeListener {

		private List<IEnemy> enemies;

		public EnemyCollector() {
			enemies = new ArrayList<IEnemy>();
		}

		public List<IEnemy> getSpawnedEnemies() {
			return enemies;
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if (event.getPropertyName().equals(EnemySpawner.NEW_ENEMY)) {
				enemies.add((IEnemy) event.getNewValue());
			}
		}

	}
}
