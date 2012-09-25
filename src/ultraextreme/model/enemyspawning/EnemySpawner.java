package ultraextreme.model.enemyspawning;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.enemyspawning.wave.Wave;
import ultraextreme.model.enemyspawning.wave.WaveListener;
import ultraextreme.model.enemyspawning.wavelist.WaveSpawningList;

/**
 * 
 * @author Daniel Jonsson
 *
 */
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

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * The wave lists that the enemy spawner is working with and grabbing waves
	 * from.
	 */
	private List<WaveSpawningList> waveLists;

	/**
	 * The waves of enemies that are currently spawning enemies.
	 */
	private List<Wave> activeWaves;

	/**
	 * Create an enemy spawner.
	 * 
	 * @param waveLists
	 *            Unspecified number of WaveSpawningLists. These will the
	 *            EnemySpawner keep track of and spawn the enemies from their
	 *            waves.
	 */
	public EnemySpawner(WaveSpawningList... waveLists) {
		this.waveLists = new ArrayList<WaveSpawningList>();
		for (WaveSpawningList waveList : waveLists) {
			if (waveList != null) {
				this.waveLists.add(waveList);
			}
		}
		if (this.waveLists.size() == 0) {
			throw new IllegalArgumentException(
					"You must specify one or more non-null waveLists");
		}
		activeWaves = new ArrayList<Wave>();
		wave = 0;
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
		updateWaves(timeElapsed);
		addNewWaves();
	}

	/**
	 * Check the wave lists that are being monitored and grab new waves if any
	 * should be spawned. Note that this method is recursive and that it calls
	 * itself.
	 */
	private void addNewWaves() {
		// TODO: This method could use some optimization.
		for (WaveSpawningList waveList : waveLists) {
			if (waveList.hasNext()) {
				if (waveList.getCurrentSpawningTime() < timer) {
					activeWaves.add(waveList.getCurrentWave());
					waveList.getCurrentWave().addListener(this);
					waveList.next();
					wave++;
					addNewWaves();
				}
			} else {
				waveLists.remove(waveList);
				break;
			}
		}
	}

	/**
	 * Run through the active waves and update them.
	 * 
	 * @param timeElapsed
	 *            Time that has elapsed since the last update.
	 */
	private void updateWaves(float timeElapsed) {
		for (Wave wave : activeWaves) {
			wave.update(timeElapsed);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void waveEnded(Wave wave) {
		for (Wave w : activeWaves) {
			if (w == wave) {
				activeWaves.remove(w);
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enemySpawned(IEnemy enemy) {
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