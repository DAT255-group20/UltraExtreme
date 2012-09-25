package ultraextreme.model.enemyspawning.wave;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public abstract class Wave {

	private List<WaveListener> listeners = new ArrayList<WaveListener>();

	protected BulletManager bulletManager;

	public Wave(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}

	public abstract void update(float timeElapsed);

	protected void fireWaveEnded() {
		for (WaveListener listener : listeners) {
			listener.waveEnded(this);
		}
	}

	protected void fireNewEnemySpawned(IEnemy enemy) {
		for (WaveListener listener : listeners) {
			listener.enemySpawned(enemy);
		}
	}

	public void addListener(WaveListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(WaveListener listener) {
		this.listeners.remove(listener);
	}
}
