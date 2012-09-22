package ultraextreme.model;

import java.util.List;

import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.enemy.EnemyManager;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.PlayerID;

/**
 * The main class for a running game.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class GameModel implements IUltraExtremeModel {

	private Player player;

	private BulletManager bulletManager;

	private EnemyManager enemyManager;

	// FIXME: Only for testing and shouldn't be here.
	private float enemySpawnTimer = 0;

	public GameModel() {
		bulletManager = new BulletManager();
		enemyManager = new EnemyManager();
		player = new Player(PlayerID.PLAYER1, bulletManager);
	}

	/**
	 * Run an update on the model.
	 * 
	 * @param input
	 *            Input variables to the model.
	 * @param timeElapsed
	 *            Time in seconds since last update.
	 */
	public void update(ModelInput input, float timeElapsed) {
		player.update(input, timeElapsed);
		for (AbstractBullet bullet : bulletManager.getBullets()) {
			bullet.doMovement(timeElapsed);
		}
		for (IEnemy enemy : enemyManager.getEnemies()) {
			enemy.update(timeElapsed);
		}

		// FIXME: Only for testing
		enemySpawnTimer += timeElapsed;
		if (enemySpawnTimer > 1) {
			enemyManager.addEnemy(new BasicEnemy(100 * enemyManager
					.getEnemies().size(), 100 * enemyManager.getEnemies()
					.size(), bulletManager));
			enemySpawnTimer = 0;
		}
	}

	@Override
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public List<AbstractBullet> getBullets() {
		return bulletManager.getBullets();
	}

	@Override
	public List<IEnemy> getEnemies() {
		return enemyManager.getEnemies();
	}

	/**
	 * @return A model interface with only get methods.
	 */
	public IUltraExtremeModel getModelInterface() {
		return this;
	}

	public BulletManager getBulletManager() {
		return bulletManager;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}
}