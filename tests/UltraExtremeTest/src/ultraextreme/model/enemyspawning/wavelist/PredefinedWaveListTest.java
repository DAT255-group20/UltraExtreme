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

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class PredefinedWaveListTest extends TestCase implements
		AbstractWaveListTest {

	private PredefinedWaveList waveList;

	@Override
	public void setUp() {
		resetWaveList();
	}

	/**
	 * Reset the instance variable waveList.
	 */
	private void resetWaveList() {
		waveList = new PredefinedWaveList(new BulletManager());
	}

	@Override
	@Test
	public void testGetNumberOfWaves() {
		resetWaveList();
		assertEquals(5, waveList.getNumberOfWaves());
	}

	@Override
	@Test
	public void testNext() {
		// Call next() a number of times on the wave list
		for (int i = 1; i < waveList.getNumberOfWaves(); ++i) {
			assertEquals(i, waveList.getCurrentWaveNumber());
			assertTrue(waveList.hasNext());
			waveList.next();
		}
		// Now there shouldn't be anything left in the list
		assertEquals(waveList.getNumberOfWaves(),
				waveList.getCurrentWaveNumber());
		assertFalse(waveList.hasNext());
	}

	public void testGenerateWaves() {
		fail("Need some implementation!!!1 maybe");
	}

}
