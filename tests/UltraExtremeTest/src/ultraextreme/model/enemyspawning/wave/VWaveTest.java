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
import ultraextreme.model.item.WeaponFactory;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class VWaveTest extends TestCase {

	private static final String WAVE_NOT_ENDED = "Wave hasn't ended";
	// EnemyCollector will listen to the wave and collect the enemies it spawns
	private EnemyCollector enemyCollector;

	private AbstractWave wave;

	private BulletManager bulletManager;

	private void initWave(double rotation, int x, int y) {
		wave = new VWave(rotation, x, y);
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
		int x = 0;
		int y = 0;
		initWave(angle, x, y);
		wave.update(0);
		EnemyShip enemyShip = enemyCollector.getSpawnedEnemies().get(0)
				.getShip();
		assertEquals(angle, enemyShip.getRotation().getAngle());
		assertEquals((double) x, enemyShip.getPositionClone().getX());
		assertEquals((double) y, enemyShip.getPositionClone().getY());

		angle = 2;
		x = 100;
		y = 200;
		initWave(2, 100, 200);
		wave.update(0);
		enemyShip = enemyCollector.getSpawnedEnemies().get(1).getShip();
		assertEquals(angle, enemyShip.getRotation().getAngle());
		assertEquals((double) x, enemyShip.getPositionClone().getX());
		assertEquals((double) y, enemyShip.getPositionClone().getY());
	}

	/**
	 * Create a new VWave and see if it spawns its enemies and if the wave
	 * eventually ends.
	 */
	public void testUpdate1() {
		initWave(0, 0, 0);

		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals("0 enemies has spawned", 0, enemyCollector
				.getSpawnedEnemies().size());

		wave.update(0);

		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals("1 enemy has spawned", 1, enemyCollector
				.getSpawnedEnemies().size());

		wave.update(1.98f);

		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals("No new enemies", enemyCollector.getSpawnedEnemies()
				.size(), 1);

		wave.update(0.03f);

		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals("2 new enemies has spawned", enemyCollector
				.getSpawnedEnemies().size(), 3);

		wave.update(2);

		assertFalse(WAVE_NOT_ENDED, enemyCollector.hasWaveEnded());
		assertEquals("An additional 2 enemies has spawned", enemyCollector
				.getSpawnedEnemies().size(), 5);

		wave.update(2);

		assertTrue("Wave has ended", enemyCollector.hasWaveEnded());
		assertEquals("All 7 enemies in the VWave has spawned", enemyCollector
				.getSpawnedEnemies().size(), 7);
	}
}
