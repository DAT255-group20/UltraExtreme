package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * This is an enemy wave where the formation is like a V.
 * 
 * @author Daniel Jonsson
 * 
 */
public class VWave extends Wave {

	private float timer;

	private int counter;

	private Rotation rotation;

	private Position spawningPositon;

	/**
	 * Create a enemy wave flying like a V.
	 * 
	 * @param rotation
	 *            How much you want to rotate the formation.
	 * @param x
	 *            X position where the enemies should spawn.
	 * @param y
	 *            Y position where the enemies should spawn.
	 * @param bulletManager
	 *            Reference to a bullet manager so the enemies can be created.
	 */
	public VWave(double rotation, int x, int y, BulletManager bulletManager) {
		super(bulletManager);
		timer = 2;
		counter = 0;
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
			if (counter == 0) {
				fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX(),
					spawningPositon.getY(), this.rotation, this.bulletManager));
			} else {
				fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX() - counter
						* 60, spawningPositon.getY(), rotation, this.bulletManager));
				fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX() + counter
						* 60, spawningPositon.getY(), rotation, this.bulletManager));
			}
			timer -= 2;
			counter++;
			if (counter > 3) {
				this.fireWaveEnded();
			}
		}
	}
}
