package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * This is a enemy wave where the enemies spawn in a horizontal line. You set a
 * number of enemies that should spawn in a horizontal line, how many lines
 * there should be and where they should spawn. When run this class will spawn a
 * new horizontal line of enemies every 2 seconds until the specified number of
 * lines is met.
 * 
 * @author Daniel Jonsson
 * 
 */
public class HorizontalLineWave extends AbstractWave {

	private float timer;

	private int lineCounter;

	private final int maxLines;

	private final int enemiesInLines;

	private final Rotation rotation;

	private final Position spawningPosition;

	/**
	 * Create a new vertical enemy line.
	 * 
	 * @param enemiesInLines
	 *            How many enemies a horizontal line should consist of.
	 * @param maxLines
	 *            Number of horizontal lines.
	 * @param rotation
	 *            How much you want to rotate the line.
	 * @param x
	 *            X position where the enemies should spawn.
	 * @param y
	 *            Y position where the enemies should spawn.
	 * @param bulletManager
	 *            Reference to a bullet manager so the enemies can be created.
	 */
	public HorizontalLineWave(final int enemiesInLines, final int maxLines,
			final double rotation, final int x, final int y, final BulletManager bulletManager) {
		super(bulletManager);
		timer = 2;
		lineCounter = 0;
		this.enemiesInLines = enemiesInLines;
		this.maxLines = maxLines;
		this.rotation = new Rotation(rotation);
		this.spawningPosition = new Position(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(final float timeElapsed) {
		timer += timeElapsed;
		if (timer >= 2) {
			spawnLine();
			timer -= 2;
			lineCounter++;
			if (lineCounter >= maxLines) {
				this.fireWaveEnded();
			}
		}
	}

	/**
	 * Spawn a new horizontal line.
	 */
	private void spawnLine() {
		for (int i = 0; i < enemiesInLines; i++) {
			fireNewEnemySpawned(new BasicEnemy(
					spawningPosition.getX() + i * 75, spawningPosition.getY(),
					rotation, this.bulletManager));
		}
	}
}
