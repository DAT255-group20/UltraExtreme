package ultraextreme.model.enemyspawning;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.enemyspawning.wave.Wave;
import ultraextreme.model.enemyspawning.wave.WaveListener;
import ultraextreme.model.enemyspawning.wavelist.PredefinedWaveList;
import ultraextreme.model.enemyspawning.wavelist.WaveSpawningList;
import ultraextreme.model.item.BulletManager;
import android.util.Log;

public class EnemySpawner implements WaveListener {

	public static final String NEW_ENEMY = "nE";

	/**
	 * A clock that keeps track of the time.
	 */
	private float timer;

	/**
	 * The number of the last started enemy wave.
	 */
	private int wave;

	/**
	 * A reference to the bullet manager.
	 */
	private BulletManager bulletManager;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private WaveSpawningList waveList;
	
	private List<Wave> activeWaves;

	/**
	 * Create an enemy spawner.
	 * 
	 * @param bulletManager
	 *            A reference to the bullet manager.
	 */
	public EnemySpawner(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
		wave = 0;
		waveList = new PredefinedWaveList(bulletManager);
		activeWaves = new ArrayList<Wave>();
	}

	/**
	 * Run an update on the enemy spawner. The PropertyChangeListeners
	 * registered to this class will get an enemy in an event if one is spawned.
	 * 
	 * @param timeElapsed
	 *            Time since this method was last called.
	 */
	public void update(float timeElapsed) {
		timer += timeElapsed;
		addNewWaves();
		updateWaves(timeElapsed);
	}
	
	private void addNewWaves() {
		if (waveList.hasNext()) {
			if (waveList.getCurrentSpawningTime() < timer) {
				activeWaves.add(waveList.getCurrentWave());
				waveList.getCurrentWave().addListener(this);
				waveList.next();
				wave++;
				addNewWaves();
			}
		}
	}
	
	private void updateWaves(float timeElapsed) {
		for (Wave wave : activeWaves) {
			wave.update(timeElapsed);
		}
	}

	@Override
	public void waveEnded(Wave wave) {
		for (Wave w : activeWaves) {
			if (w == wave) {
				activeWaves.remove(w);
				break;
			}
		}
	}

	@Override
	public void enemySpawned(IEnemy enemy) {
		pcs.firePropertyChange(EnemySpawner.NEW_ENEMY, null, enemy);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}
	
	public int getCurrentWave() {
		return wave;
	}
}