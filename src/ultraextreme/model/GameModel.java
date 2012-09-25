package ultraextreme.model;

import java.util.List;

import ultraextreme.model.enemy.EnemyManager;
import ultraextreme.model.enemy.EnemySpawner;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.WeaponPickup;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.PickupManager;
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

	private EnemySpawner enemySpawner;
	
	private PickupManager pickUpManager;
	
	public GameModel() {
		bulletManager = new BulletManager();
		enemyManager = new EnemyManager();
		enemySpawner = new EnemySpawner(bulletManager);
		enemySpawner.addPropertyChangeListener(enemyManager);
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

		enemySpawner.update(timeElapsed);

		bulletManager.clearBulletsOffScreen();
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