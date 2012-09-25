package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

public class VWave extends Wave {

	private float timer;

	private int counter;

	private Rotation rotation;

	private Position spawningPositon;

	public VWave(double rotation, int x, int y, BulletManager bulletManager) {
		super(bulletManager);
		timer = 0;
		counter = 0;
		this.rotation = new Rotation(rotation);
		this.spawningPositon = new Position(x, y);
		fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX(),
				spawningPositon.getY(), this.rotation, this.bulletManager));
	}

	@Override
	public void update(float timeElapsed) {
		timer += timeElapsed;
		if (timer > 2) {
			fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX() - counter
					* 60, spawningPositon.getY(), rotation, this.bulletManager));
			fireNewEnemySpawned(new BasicEnemy(spawningPositon.getX() + counter
					* 60, spawningPositon.getY(), rotation, this.bulletManager));
			timer = 0;
			counter++;
			if (counter > 3) {
				this.fireWaveEnded();
			}
		}
	}
}
