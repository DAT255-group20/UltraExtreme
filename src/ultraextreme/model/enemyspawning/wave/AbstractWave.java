package ultraextreme.model.enemyspawning.wave;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.IEnemy;
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
	protected void fireNewEnemySpawned(IEnemy enemy) {
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
