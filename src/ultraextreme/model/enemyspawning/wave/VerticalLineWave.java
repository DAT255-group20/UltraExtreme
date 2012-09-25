package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

public class VerticalLineWave extends Wave {

	private float timer;

	private int enemyCounter;

	private int numberOfEnemies;

	private Rotation rotation;

	private Position spawningPositon;

	public VerticalLineWave(int numberOfEnemies, double rotation,
			int x, int y, BulletManager bulletManager) {
		super(bulletManager);
		timer = 2;
		enemyCounter = 0;
		this.numberOfEnemies = numberOfEnemies;
		this.rotation = new Rotation(rotation);
		this.spawningPositon = new Position(x, y);
	}

	@Override
	public void update(float timeElapsed) {
		timer += timeElapsed;
		if (timer > 2) {
			fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX(),
					spawningPositon.getY(), rotation, this.bulletManager));
			timer = 0;
			enemyCounter++;
			if (enemyCounter >= numberOfEnemies) {
				this.fireWaveEnded();
			}
		}
	}
}
