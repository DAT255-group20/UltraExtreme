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
		RandomWaveList waveList1 = new RandomWaveList(100, Difficulty.NORMAL,
				new AbstractRandomGenerator() {
					private int counter;

					@Override
					public float nextFloat() {
						return ++counter;
					}
				});

		// 0
		assertEquals(waveList1.getCurrentSpawningTime(), 0f);
		assertTrue(waveList1.getCurrentWave() instanceof VWave);

		waveList1.next();

		// 5.5
		assertEquals(waveList1.getCurrentSpawningTime(), 5.5f);
		assertTrue(waveList1.getCurrentWave() instanceof RectangleWave);

		waveList1.next();

		// 5.5 + 7.5 = 13
		assertEquals(waveList1.getCurrentSpawningTime(), 13f);

		waveList1.next();

		// 5.5 + 7.5 + 9.5 = 22.5
		assertEquals(waveList1.getCurrentSpawningTime(), 22.5f);
		assertTrue(waveList1.getCurrentWave() instanceof VWave);

		waveList1.next();

		// 5.5 + 7.5 + 9.5 + 11.5 = 34
		assertEquals(waveList1.getCurrentSpawningTime(), 34f);
		assertTrue(waveList1.getCurrentWave() instanceof RectangleWave);
	}

	@Override
	public void testGetNumberOfWaves() {
		for (int waves = 1; waves < 100000; ++waves) {
			resetWaveList(waves);
			assertEquals(waves, waveList.getNumberOfWaves());
		}
	}

	@Override
	public void testNext() {

		// FIXME This test takes extremely long time to run
		// Run through a number of tests where the maximum number of waves are
		// different
		for (int waves = 1; waves < 1000; ++waves) {
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
