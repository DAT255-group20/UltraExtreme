package ultraextreme.model;

import java.util.List;

import ultraextreme.model.enemy.EnemyManager;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.enemyspawning.EnemySpawner;
import ultraextreme.model.enemyspawning.wavelist.RandomWaveList;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.entity.WeaponPickup;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.PickupManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.PlayerID;

/**
 * The main class for a running game.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * 
 */
public class GameModel implements IUltraExtremeModel {

	final private Player player;

	final private BulletManager bulletManager;

	final private EnemyManager enemyManager;

	final private EnemySpawner enemySpawner;

	private PickupManager pickupManager;

	private WeaponFactory weaponFactory;

	public GameModel() {
		bulletManager = new BulletManager();
		enemyManager = new EnemyManager();
		pickupManager = new PickupManager();
		weaponFactory = new WeaponFactory(bulletManager);
		enemySpawner = new EnemySpawner(new RandomWaveList(1000, bulletManager));
		enemySpawner.addPropertyChangeListener(enemyManager);
		player = new Player(PlayerID.PLAYER1, bulletManager);

		// Player listens when enemies are killed
		enemyManager.addPropertyChangeListener(player);
	}

	/**
	 * Run an update on the model.
	 * 
	 * @param input
	 *            Input variables to the model.
	 * @param timeElapsed
	 *            Time in seconds since last update.
	 */
	public void update(final ModelInput input, final float timeElapsed) {
		player.update(input, timeElapsed);
		for (IBullet bullet : bulletManager.getBullets()) {
			bullet.doMovement(timeElapsed);
		}
		for (IEnemy enemy : enemyManager.getEnemies()) {
			enemy.update(timeElapsed);
		}

		// TODO Decide if we want pickups to move around. If so, the following
		// commented code is necessary.
		// for(WeaponPickup pickup : pickupManager.getPickups()) {
		// pickup.doMovement(timeElapsed); ?
		// }

		enemySpawner.update(timeElapsed);

		checkCollisions();

		spawnPickups();
		enemyManager.clearDeadEnemies();
		bulletManager.clearBulletsOffScreen();
	}

	/**
	 * spawns a pickup for every enemy marked as "should spawn a pickup"
	 */
	private void spawnPickups() {
		for (IEnemy enemy : enemyManager.getEnemies()) {
			if (enemy.shouldSpawnPickup()) {
				pickupManager.addPickup(new WeaponPickup(enemy.getShip()
						.getPosition(), enemy.getWeapon().getName()));
			}
		}
	}

	private void checkCollisions() {
		final List<IBullet> playerBullets = bulletManager
				.getBulletsFrom(PlayerID.PLAYER1);
		final List<IBullet> enemyBullets = bulletManager
				.getBulletsFrom(PlayerID.ENEMY);

		// Check player bullets against enemies
		for (IBullet b : playerBullets) {
			for (IEnemy e : enemyManager.getEnemies()) {
				if (b.collidesWith(e.getShip())) {
					e.getShip().receiveDamage(b.getDamage());
					b.markForRemoval();
				}
			}
		}

		// Check enemy bullets against player
		for (IBullet b : enemyBullets) {
			if (b.collidesWith(player.getShip())) {
				player.getShip().receiveDamage(b.getDamage());
				b.markForRemoval();
			}
		}

		// Check Items against player
		for (int i = 0; i < pickupManager.getPickups().size(); i++) {
			WeaponPickup wp = pickupManager.getPickups().get(i);
			if (wp.collidesWith(player.getShip())) {
				player.giveWeapon(weaponFactory.getNewWeapon(wp.getObjectName()));
				pickupManager.removePickUp(i);
				i--;
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

	@Override
	public void registerPlayerListener(IPlayerListener listener) {
		player.registerListener(listener);
	}

	@Override
	public void unregisterPlayerListener(IPlayerListener listener) {
		player.unregisterListener(listener);
	}
}