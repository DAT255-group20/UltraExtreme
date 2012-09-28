package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * This is a enemy wave where the enemies spawn in a vertical line. You set a
 * number of enemies and where they should spawn, then is a new enemy spawned
 * every 2 second until the max number of enemies is reach.
 * 
 * @author Daniel Jonsson
 * 
 */
public class VerticalLineWave extends Wave {

	private float timer;

	private int enemyCounter;

	private int numberOfEnemies;

	private Rotation rotation;

	private Position spawningPositon;

	/**
	 * Create a new vertical enemy line.
	 * 
	 * @param numberOfEnemies
	 *            Number of enemies in the line.
	 * @param rotation
	 *            How much you want to rotate the line.
	 * @param x
	 *            X position where the enemies should spawn.
	 * @param y
	 *            Y position where the enemies should spawn.
	 * @param bulletManager
	 *            Reference to a bullet manager so the enemies can be created.
	 */
	public VerticalLineWave(int numberOfEnemies, double rotation, int x, int y,
			BulletManager bulletManager) {
		super(bulletManager);
		timer = 2;
		enemyCounter = 0;
		this.numberOfEnemies = numberOfEnemies;
		this.rotation = new Rotation(rotation);
		this.spawningPositon = new Position(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(float timeElapsed) {
		timer += timeElapsed;
		if (timer >= 2) {
			fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX(),
					spawningPositon.getY(), rotation, this.bulletManager));
			timer -= 2;
			enemyCounter++;
			if (enemyCounter >= numberOfEnemies) {
				this.fireWaveEnded();
			}
		}
	}
}
