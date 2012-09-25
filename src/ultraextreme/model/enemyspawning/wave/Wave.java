package ultraextreme.model.enemyspawning.wave;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class Wave {
	
	private List<WaveListener> listeners = new ArrayList<WaveListener>();
	
	private float timer;
	
	private int waveCounter;
	
	private BulletManager bulletManager;
	
	public Wave(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
		timer = 0;
		waveCounter = 0;
	}

	public void update(float timeDiff) {
		timer += timeDiff;
		if (timer > 1) {
			fireNewEnemySpawned(new BasicEnemy(10, 10, bulletManager));
			timer = 0;
			waveCounter++;
			if (waveCounter > 5) {
				fireWaveEnded();
			}
		}
	}
	
	private void fireWaveEnded() {
		for (WaveListener listener : listeners) {
			listener.waveEnded(this);
		}
	}
	
	private void fireNewEnemySpawned(IEnemy enemy) {
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
