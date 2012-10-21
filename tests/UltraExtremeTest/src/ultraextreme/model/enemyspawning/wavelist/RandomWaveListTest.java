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

package ultraextreme.model.enemyspawning.wavelist;

import ultraextreme.model.enemyspawning.wave.RectangleWave;
import ultraextreme.model.enemyspawning.wave.VWave;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.Difficulty;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class RandomWaveListTest extends AbstractWaveListTest {

	private RandomWaveList waveList;

	private BulletManager bulletManager;

	/**
	 * Reset the instance variable waveList.
	 * 
	 * @param numberOfWaves
	 */
	private void resetWaveList(int numberOfWaves) {
		waveList = new RandomWaveList(numberOfWaves, Difficulty.NORMAL);
	}

	@Override
	public void setUp() {
		bulletManager = new BulletManager();
		WeaponFactory.initialize(bulletManager);
	}

	/**
	 * This test will create a new RandomWaveList, generate some waves and check
	 * if they got the correct spawn time and are instance of the right wave.
	 */
	public void testGeneratingNewWaves() {
		waveList = new RandomWaveList(100, Difficulty.NORMAL,
				new AbstractRandomGenerator() {
					private int counter = 0;
					// 0.0f - make it pick 1st wave
					// 0.25f make it pick 2nd wave
					// 0.5f make it pick 3rd wave
					// 0.75f make it pick 4th wave
					private float numbers[] = { 0.0f, 1, 0.25f, 1, 0.5f, 1, 1,
							1, 0.75f, 1, 0.0f, 1, 1 };

					@Override
					public float nextFloat() {
						return numbers[counter++];
					}
				});

		// First wave
		assertEquals(0f, waveList.getCurrentSpawningTime());
		assertTrue(waveList.getCurrentWave() instanceof VWave);

		waveList.next();

		// Second wave
		assertEquals(12f, waveList.getCurrentSpawningTime());
		assertTrue(waveList.getCurrentWave() instanceof RectangleWave);

		waveList.next();

		// Third wave
		assertEquals(24f, waveList.getCurrentSpawningTime(), 0.3f);
		assertTrue(waveList.getCurrentWave() instanceof RectangleWave);

		waveList.next();

		// Fourth wave
		assertEquals(35.5f, waveList.getCurrentSpawningTime(), 0.3f);
		assertTrue(waveList.getCurrentWave() instanceof RectangleWave);

		waveList.next();

		// First wave again
		assertEquals(47.3f, waveList.getCurrentSpawningTime(), 0.3f);
		assertTrue(waveList.getCurrentWave() instanceof VWave);
	}

	@Override
	public void testGetNumberOfWaves() {
		for (int waves = 1; waves < 1000; waves += 10) {
			resetWaveList(waves);
			assertEquals(waves, waveList.getNumberOfWaves());
		}
	}

	@Override
	public void testNext() {
		// Test with different maximum number of waves
		for (int waves = 1; waves < 1000; waves += 200) {
			resetWaveList(waves);
			// Call next() a number of times on the wave list
			for (int i = 1; i < waves; ++i) {
				assertEquals(i, waveList.getCurrentWaveNumber());
				assertTrue(waveList.hasNext());
				waveList.next();
			}
			// Now there shouldn't be anything left in the list
			assertEquals(waves, waveList.getCurrentWaveNumber());
			assertFalse(waveList.hasNext());
		}
	}
}
