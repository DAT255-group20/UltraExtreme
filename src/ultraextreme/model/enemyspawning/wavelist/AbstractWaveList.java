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

/**
 * This abstract WaveSpawningList implements some stuff that most lists will
 * need/use. It has the current wave counter and the number of maximum waves.
 * 
 * @author Daniel Jonsson
 * 
 */
public abstract class AbstractWaveList implements IWaveSpawningList {

	/**
	 * The number of waves that this wave list has spawned.
	 */
	private int currentWaveNumber;

	/**
	 * Total number of waves that this list contains.
	 */
	private final int numberOfWaves;

	/**
	 * @param numberOfWaves
	 *            The number of available waves.
	 */
	public AbstractWaveList(final int numberOfWaves) {
		this.numberOfWaves = numberOfWaves;
		this.currentWaveNumber = 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void next() {
		currentWaveNumber++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCurrentWaveNumber() {
		return currentWaveNumber;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNumberOfWaves() {
		return numberOfWaves;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasNext() {
		return currentWaveNumber < numberOfWaves;
	}
}
