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

import junit.framework.TestCase;
import ultraextreme.model.enemyspawning.wavelist.AbstractRandomGenerator;
import ultraextreme.model.enemyspawning.wavelist.RandomWaveList;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;

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
	 * Create a EnemySpawner, give it a RandomWaveList with a custom
	 * RandomGenerator and see if the EnemySpawner behaves correctly.
	 */
	public void testUpdateMethod() {
		RandomWaveList waveList = new RandomWaveList(1,
				new AbstractRandomGenerator() {
					private int counter = 0;

					@Override
					public float nextFloat() {
						return ++counter;
					}
				});
		EnemySpawner enemySpawner = new EnemySpawner(waveList);
		EnemyCollector enemyCollector = new EnemyCollector();
		enemySpawner.addPropertyChangeListener(enemyCollector);

		assertEquals(enemySpawner.getCurrentWave(), 0);

		enemySpawner.update(0); // A VWave has spawned

		assertEquals(enemySpawner.getCurrentWave(), 1);

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);

		enemySpawner.update(1.98f); // Timer in VWave is ~1.98

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);

		enemySpawner.update(0.03f); // Timer in VWave is ~2.01 and 2 enemies
									// spawn

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 3);

		enemySpawner.update(2f); // 2 New enemies spawn

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 5);

		enemySpawner.update(2f); // 2 New enemies spawn

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 7);

		enemySpawner.update(2f); // No new enemies spawn

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 7);
	}
	
	public void testGetCurrentWave()
	{
		fail("Not yet tested");
	}
	
	public void testWaveEnded()
	{
		// TODO Is this even testable?
		fail("Not yet tested");
	}
	
	public void testEnemySpawned()
	{
		// TODO Is this even testable?
		fail("Not yet tested");
	}
}
