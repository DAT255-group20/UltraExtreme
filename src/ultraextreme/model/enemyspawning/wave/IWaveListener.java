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

import ultraextreme.model.enemy.AbstractEnemy;

/**
 * A class that wants to get updates from a wave should implement this
 * interface. Mainly made for the EnemySpawner class, which handles the active
 * waves.
 * 
 * @author Daniel Jonsson
 * 
 */
public interface IWaveListener {

	/**
	 * A wave that has ended (i.e. spawned all its enemies).
	 * 
	 * @param wave
	 *            Reference to the wave that has ended.
	 */
	void waveEnded(AbstractWave wave);

	/**
	 * A Wave has spawned an enemy.
	 * 
	 * @param enemy
	 *            The enemy that has spawned.
	 */
	void enemySpawned(AbstractEnemy enemy);

}
