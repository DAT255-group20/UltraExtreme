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

package ultraextreme.model.enemyspawning.wave;

import junit.framework.TestCase;
import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class HorizontalLineWaveTest extends TestCase {

	private BulletManager bulletManager;

	private EnemyCollector enemyCollector;

	private AbstractWave wave;

	@Override
	public void setUp() {
		bulletManager = new BulletManager();
		enemyCollector = new EnemyCollector();
	}

	private void initWave(int numOfEnemies, int numOfLines, double rotation,
			int x, int y) {
		wave = new HorizontalLineWave(numOfEnemies, numOfLines, rotation, x, y,
				bulletManager);
		wave.addListener(enemyCollector);
	}

	/**
	 * Create a new wave containing 1 enemy and see if it spawns and if the wave
	 * ends.
	 */
	public void testUpdate1() {
		initWave(1, 1, 0, 0, 0);
		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 0);
		wave.update(0);
		assertTrue(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);
	}

	/**
	 * Create a new wave containing 3 rows with 5 enemies in each, then see if
	 * they spawn correctly.
	 */
	public void testUpdate2() {
		initWave(5, 3, 0, 0, 0);
		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 0);

		wave.update(0.01f);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 5);

		wave.update(1.98f);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 5);

		wave.update(0.03f);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 10);

		wave.update(2);

		assertTrue(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 15);
	}

	/**
	 * Create a new wave and see if the enemy spawned has the correct
	 * properties.
	 */
	public void testSpawnedEnemyPropteries() {
		initWave(1, 1, 0, 0, 0);
		wave.update(0);
		EnemyShip enemyShip = enemyCollector.getSpawnedEnemies().get(0)
				.getShip();
		assertEquals(enemyShip.getRotation().getAngle(), 0.0);
		assertEquals(enemyShip.getPositionClone().getX(), 0.0);
		assertEquals(enemyShip.getPositionClone().getY(), 0.0);

		initWave(1, 1, 2, 100, 200);
		wave.update(0);
		enemyShip = enemyCollector.getSpawnedEnemies().get(1).getShip();
		assertEquals(enemyShip.getRotation().getAngle(), 2.0);
		assertEquals(enemyShip.getPositionClone().getX(), 100.0);
		assertEquals(enemyShip.getPositionClone().getY(), 200.0);
	}
}
