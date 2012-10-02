package ultraextreme.model.enemy;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Daniel Jonsson
 * @author Bjorn Persson Mattsson
 *
 */
public class EnemySpawner {

	public static final String NEW_ENEMY = "add";

	/**
	 * A clock that keeps track of the time.
	 */
	private float timer;

	/**
	 * When the next wave will spawn.
	 */
	private float nextWaveTime;

	/**
	 * The number of the last started enemy wave.
	 */
	private int wave; // TODO PMD: This field is not used

	/**
	 * The number of ships in the current wave.
	 */
	private int waveSize;

	/**
	 * Keep track of how many enemies that have spawned in the current wave.
	 */
	private int waveSpawnCounter;

	/**
	 * When the next enemy wave will spawn.
	 */
	private float nextEnemySpawnTime;

	/**
	 * Used so the next enemy knows where to spawn.
	 */
	private Position nextPosition;

	/**
	 * A reference to the bullet manager.
	 */
	private final BulletManager bulletManager;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Create an enemy spawner.
	 * 
	 * @param bulletManager
	 *            A reference to the bullet manager.
	 */
	public EnemySpawner(final BulletManager bulletManager) {
		this.bulletManager = bulletManager;
		nextWaveTime = 0;
		wave = 0;
	}

	/**
	 * Run an update on the enemy spawner. The PropertyChangeListeners
	 * registered to this class will get an enemyShip in an event if an enemy is
	 * spawned.
	 * 
	 * @param timeElapsed
	 *            Time since this method was last called.
	 */
	public void update(final float timeElapsed) {
		timer += timeElapsed;
		if (timer > nextEnemySpawnTime) {
			if (waveSpawnCounter < waveSize) {
				waveSpawnCounter++;
				nextEnemySpawnTime = timer + 1;

				final BasicEnemy newEnemy = new BasicEnemy(nextPosition.getX(),
						nextPosition.getY(), bulletManager);
				pcs.firePropertyChange(EnemySpawner.NEW_ENEMY, null, newEnemy);

				nextPosition.setX(nextPosition.getX() + 70);
			} else if (timer > nextWaveTime) {
				wave++;
				final Random random = new Random();
				waveSize = (int) (random.nextFloat() * 5 + 2);
				waveSpawnCounter = 0;
				nextWaveTime = timer + waveSize + 2;
				nextPosition = new Position(10, 10);
				nextEnemySpawnTime = timer + 2;
			}
		}
		if (timer > nextWaveTime) {
			// TODO PMD: Avoid empty 'if' statements
		}
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(
			final PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}
}