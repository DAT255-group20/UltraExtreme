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
import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class RectangleWaveTest extends TestCase {

	private static final String WAVE_NOT_ENDED = "Wave hasn't ended";
	private BulletManager bulletManager;

	// EnemyCollector will listen to the wave and collect the enemies it spawns
	private EnemyCollector enemyCollector;

	private AbstractWave wave;

	private void initWave(int numOfEnemies, int numOfLines, double rotation,
			int x, int y) {
		wave = new RectangleWave(numOfEnemies, numOfLines, rotation, x, y,
				new AbstractEnemyProvider() {
					// Let the RectangleWave create BasicEnemies
					@Override
					public AbstractEnemy getEnemy(Position spawningPosition,
							Rotation rotation) {
						return new BasicEnemy(spawningPosition, rotation,
								ObjectName.BASIC_WEAPON);
					}

				});
		wave.addListener(enemyCollector);
	}

	@Override
	public void setUp() {
		bulletManager = new BulletManager();
		WeaponFactory.initialize(bulletManager);
		enemyCollector = new EnemyCollector();
	}

	/**
	 * Create a new wave and see if the enemy spawned has the correct
	 * properties.
	 */
	public void testSpawnedEnemyProperties() {
		double angle = 0;
		int x = 100;
		int y = 200;
		initWave(1, 1, angle, x, y);
		wave.update(0);
		EnemyShip enemyShip = enemyCollector.getSpawnedEnemies().get(0)
				.getShip();
		assertEquals(angle, enemyShip.getRotation().getAngle());
		assertEquals((double) x, enemyShip.getPositionClone().getX());
		assertEquals((double) y, enemyShip.getPositionClone().getY());

		angle = 2;
		x = 100;
		y = 200;
		initWave(1, 1, angle, x, y);
		wave.update(0);
		enemyShip = enemyCollector.getSpawnedEnemies().get(1).getShip();
		assertEquals(angle, enemyShip.getRotation().getAngle());
		assertEquals((double) x, enemyShip.getPositionClone().getX());
		assertEquals((double) y, enemyShip.getPositionClone().getY());
	}

	/**
	 * Create a new wave containing 1 enemy and see if it spawns and if the wave
	 * ends.
	 */
	public void testUpdate1() {
		initWave(1, 1, 0, 0, 0);
		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals("No spawned enemies", 0, enemyCollector
				.getSpawnedEnemies().size());
		wave.update(0); // Update the wave and let it spawn it first enemy
		assertTrue("The wave has ended", enemyCollector.hasWaveEnded());
		assertEquals("One spawned enemy", 1, enemyCollector.getSpawnedEnemies()
				.size());
	}

	/**
	 * Create a new wave containing 3 rows with 5 enemies in each, then see if
	 * they spawn correctly.
	 */
	public void testUpdate2() {
		int enemiesInRow = 5; // Number of enemies in a row

		initWave(enemiesInRow, 3, 0, 0, 0);
		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals("No enemies has spawned yet", 0, enemyCollector
				.getSpawnedEnemies().size());

		wave.update(0.01f); // Run an update

		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals(Integer.toString(enemiesInRow) + " enemies has spawned",
				enemiesInRow, enemyCollector.getSpawnedEnemies().size());

		wave.update(1.98f);

		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals("No new enemies has spawned", enemiesInRow, enemyCollector
				.getSpawnedEnemies().size());

		wave.update(0.03f);

		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals(Integer.toString(enemiesInRow)
				+ " new enemies has spawned", enemiesInRow * 2, enemyCollector
				.getSpawnedEnemies().size());

		wave.update(2);

		assertTrue("Wave has ended", enemyCollector.hasWaveEnded());
		assertEquals("All enemies has spawned", enemiesInRow * 3,
				enemyCollector.getSpawnedEnemies().size());
	}
}
