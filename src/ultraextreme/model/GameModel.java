package ultraextreme.model;

import java.util.List;

import ultraextreme.model.enemy.EnemyManager;
import ultraextreme.model.enemy.EnemySpawner;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.IBullet;
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
		for (IBullet bullet : bulletManager.getBullets()) {
			bullet.doMovement(timeElapsed);
		}
		for (IEnemy enemy : enemyManager.getEnemies()) {
			enemy.update(timeElapsed);
		}

		enemySpawner.update(timeElapsed);
		
		checkCollisions();

		bulletManager.clearBulletsOffScreen();
	}

	private void checkCollisions() {
		// TODO Auto-generated method stub
		List<IBullet> playerBullets = bulletManager.getBulletsFrom(PlayerID.PLAYER1);
		for (IBullet b : playerBullets)
		{
			for (IEnemy e : enemyManager.getEnemies())
			{
				if (b.collidesWith(e.getShip()))
				{
					e.getShip().damage(1);
					b.markForRemoval();
				}
			}
		}
	}

	@Override
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public List<IBullet> getBullets() {
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