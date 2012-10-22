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

import ultraextreme.model.enemyspawning.wave.AbstractWave;

/**
 * A WaveSpawningList has a number of enemy waves (the Wave class), that a
 * EnemySpawner uses to grab waves from.
 * 
 * @author Daniel Jonsson
 * 
 */
public interface IWaveSpawningList {

	/**
	 * Get the current wave.
	 * 
	 * @return The current wave.
	 */
	AbstractWave getCurrentWave();

	/**
	 * Get when the current wave is supposed to start spawning.
	 * 
	 * @return When the wave is supposed to start spawn.
	 */
	float getCurrentSpawningTime();

	/**
	 * Move the list to the next wave entry. Observe that it's not possible to
	 * go back again, so make sure the last wave has spawned.
	 */
	void next();

	/**
	 * Get which wave that was the latest to spawn.
	 * 
	 * @return Number of the latest wave.
	 */
	int getCurrentWaveNumber();

	/**
	 * If there exists a next wave.
	 * 
	 * @return If there exists a next wave.
	 */
	boolean hasNext();

}
