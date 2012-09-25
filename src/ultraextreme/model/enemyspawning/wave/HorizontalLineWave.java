package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

public class HorizontalLineWave extends Wave {

	private float timer;

	private int lineCounter;

	private int maxLines;

	private int enemyInLines;

	private Rotation rotation;

	private Position spawningPosition;

	public HorizontalLineWave(int enemyInLines, int maxLines, double rotation,
			int x, int y, BulletManager bulletManager) {
		super(bulletManager);
		timer = 2;
		lineCounter = 0;
		this.enemyInLines = enemyInLines;
		this.maxLines = maxLines;
		this.rotation = new Rotation(rotation);
		this.spawningPosition = new Position(x, y);
	}

	@Override
	public void update(float timeElapsed) {
		timer += timeElapsed;
		if (timer > 2) {
			spawnLine();
			timer = 0;
			lineCounter++;
			if (lineCounter > maxLines) {
				this.fireWaveEnded();
			}
		}
	}

	private void spawnLine() {
		for (int i = 0; i < enemyInLines; i++) {
			fireNewEnemySpawned(new BasicEnemy(spawningPosition.getX() + i
					* 75, spawningPosition.getY(), rotation,
					this.bulletManager));
		}
	}
}
