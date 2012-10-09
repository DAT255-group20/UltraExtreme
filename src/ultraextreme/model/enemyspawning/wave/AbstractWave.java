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

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.item.BulletManager;

/**
 * This is an abstract wave that handles stuff that all child waves will need,
 * such as listener management.
 * 
 * @author Daniel Jonsson
 * 
 */
public abstract class AbstractWave {

	/**
	 * The classes that listen to the wave.
	 */
	private List<WaveListener> listeners = new ArrayList<WaveListener>();

	/**
	 * Reference to a bullet manager. This is kept so enemies can be created.
	 */
	protected BulletManager bulletManager;

	/**
	 * Create a new enemy wave.
	 * 
	 * @param bulletManager
	 *            Reference to a bullet manager.
	 */
	public AbstractWave(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}

	/**
	 * Update the wave's logic.
	 * 
	 * @param timeElapsed
	 *            Time since the last update.
	 */
	public abstract void update(float timeElapsed);

	/**
	 * Tell the listeners that this wave has ended.
	 */
	protected void fireWaveEnded() {
		for (WaveListener listener : listeners) {
			listener.waveEnded(this);
		}
	}

	/**
	 * Tell the listeners that a new enemy has spawned.
	 * 
	 * @param enemy
	 *            Reference to the enemy that has spawned.
	 */
	protected void fireNewEnemySpawned(AbstractEnemy enemy) {
		for (WaveListener listener : listeners) {
			listener.enemySpawned(enemy);
		}
	}

	/**
	 * Add a WaveListener that wants to know when the wave has ended and when
	 * enemies spawn.
	 * 
	 * @param listener
	 *            The listener to be added.
	 */
	public void addListener(WaveListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * Remove a listener from the wave.
	 * 
	 * @param listener
	 *            The listener to be removed.
	 */
	public void removeListener(WaveListener listener) {
		this.listeners.remove(listener);
	}
}
