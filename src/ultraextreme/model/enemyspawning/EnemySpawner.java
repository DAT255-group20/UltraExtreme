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

package ultraextreme.model.enemyspawning;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.enemyspawning.wave.AbstractWave;
import ultraextreme.model.enemyspawning.wave.IWaveListener;
import ultraextreme.model.enemyspawning.wavelist.IWaveSpawningList;

/**
 * This class is responsible for managing the waves of enemies. It takes one or
 * more WaveSpawningLists in its constructor, which this class then grabs new
 * waves from. References to the enemies are then sent to all of the
 * EnemySpawner's listeners.
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemySpawner implements IWaveListener {

	public static final String NEW_ENEMY = "nE";

	/**
	 * A clock that keeps track of the time.
	 */
	private float timer;

	/**
	 * The number of the last started enemy wave.
	 */
	private int wave;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * The wave lists that the enemy spawner is working with and grabbing waves
	 * from.
	 */
	private final List<IWaveSpawningList> waveLists;

	/**
	 * The waves of enemies that are currently spawning enemies.
	 */
	private final List<AbstractWave> activeWaves;

	/**
	 * Keeps track of which waves that have ended since the last update.
	 */
	private final List<AbstractWave> finishedWaves;

	/**
	 * Create an enemy spawner.
	 * 
	 * @param waveLists
	 *            Unspecified number of WaveSpawningLists. These will the
	 *            EnemySpawner keep track of and spawn the enemies from their
	 *            waves.
	 */
	public EnemySpawner(final IWaveSpawningList... waveLists) {
		this.waveLists = new ArrayList<IWaveSpawningList>();
		for (IWaveSpawningList waveList : waveLists) {
			if (waveList != null) {
				this.waveLists.add(waveList);
			}
		}
		if (this.waveLists.size() == 0) {
			throw new IllegalArgumentException(
					"You must specify one or more non-null waveLists");
		}
		activeWaves = new ArrayList<AbstractWave>();
		finishedWaves = new ArrayList<AbstractWave>();
		wave = 0;
	}

	/**
	 * Run an update on the enemy spawner. The PropertyChangeListeners
	 * registered to this class will get an enemy in an event if one is spawned.
	 * 
	 * @param timeElapsed
	 *            Time since this method was last called.
	 */
	public void update(final float timeElapsed) {
		timer += timeElapsed;
		addNewWaves();
		updateWaves(timeElapsed);
	}

	/**
	 * Traverse the wave lists that are being monitored and grab new waves if
	 * any should be spawned.
	 */
	private void addNewWaves() {
		for (int i = 0; i < waveLists.size(); ++i) {
			final IWaveSpawningList waveList = waveLists.get(i);
			addWavesFromWaveList(waveList);
			if (!waveList.hasNext()) {
				waveLists.remove(waveList);
				--i;
			}
		}
	}

	/**
	 * Helper method to addNewWaves(). This asks a wave list for new waves until
	 * it doesn't have any more left. Note that this method is recursive and
	 * therefore calls itself.
	 * 
	 * This method is potentially dangerous if the wave list isn't properly
	 * coded. If the currentSpawningTime never increases, or increases too
	 * slowly, the game will crash because of a stack overflow.
	 */
	private void addWavesFromWaveList(IWaveSpawningList waveList) {
		if (waveList.hasNext() && waveList.getCurrentSpawningTime() <= timer) {
			activeWaves.add(waveList.getCurrentWave());
			waveList.getCurrentWave().addListener(this);
			waveList.next();
			++wave;
			addWavesFromWaveList(waveList);
		}
	}

	/**
	 * Run through the active waves and update them.
	 * 
	 * @param timeElapsed
	 *            Time that has elapsed since the last update.
	 */
	private void updateWaves(final float timeElapsed) {
		for (int i = 0; i < finishedWaves.size(); i++) {
			activeWaves.remove(finishedWaves.get(i));
			finishedWaves.remove(i);
			i--;
		}
		for (final Iterator<AbstractWave> i = activeWaves.iterator(); i
				.hasNext();) {
			final AbstractWave wave1 = i.next();
			wave1.update(timeElapsed);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void waveEnded(final AbstractWave wave) {
		// This method can't directly delete the wave from the activeWaves list
		// because that will result in an ConcurrentModificationException, since
		// higher up in the stack is this class' updateWaves method running.
		finishedWaves.add(wave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enemySpawned(final AbstractEnemy enemy) {
		pcs.firePropertyChange(EnemySpawner.NEW_ENEMY, null, enemy);
	}

	/**
	 * Add a listener that wants to get references to the spawned enemies.
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Get the current wave number.
	 * 
	 * @return The number of waves that has started.
	 */
	public int getCurrentWave() {
		return wave;
	}
}