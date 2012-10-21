/* ============================================================
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,
 * Viktor Anderling
 *
 * This file is part of UltraExtreme.
 *
 * UltraExtreme is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UltraExtreme is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.
 * ============================================================ */

package ultraextreme.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.enemy.EnemyManager;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.enemyspawning.EnemySpawner;
import ultraextreme.model.enemyspawning.wavelist.RandomWaveList;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.entity.WeaponPickup;
import ultraextreme.model.item.BasicWeapon;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.PickupManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Difficulty;
import ultraextreme.model.util.PlayerID;

/**
 * The main class for a running game.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public class GameModel implements IUltraExtremeModel {

	private final Player player;

	private final BulletManager bulletManager;

	private final EnemyManager enemyManager;

	private EnemySpawner enemySpawner;

	private PickupManager pickupManager;

	private PropertyChangeSupport pcs;

	public GameModel() {
		bulletManager = new BulletManager();
		enemyManager = new EnemyManager();
		pickupManager = new PickupManager();
		WeaponFactory.initialize(bulletManager);
		enemySpawner = new EnemySpawner(new RandomWaveList(1000,
				Difficulty.NORMAL));
		enemySpawner.addPropertyChangeListener(enemyManager);
		player = new Player(PlayerID.PLAYER1, bulletManager);
		pcs = new PropertyChangeSupport(this);

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
		for (AbstractBullet bullet : bulletManager.getBullets()) {
			bullet.doMovement(timeElapsed);
		}
		for (AbstractEnemy enemy : enemyManager.getEnemies()) {
			enemy.update(timeElapsed);
		}
		for (WeaponPickup pickup : pickupManager.getPickups()) {
			pickup.doMovement(timeElapsed);
		}

		enemySpawner.update(timeElapsed);

		checkCollisions();

		spawnPickups();
		enemyManager.clearDeadEnemies();
		bulletManager.clearBulletsOffScreen();
		pickupManager.clearPickupsOffScreen();
	}

	/**
	 * spawns a pickup for every enemy marked as "should spawn a pickup"
	 */
	private void spawnPickups() {
		for (IEnemy enemy : enemyManager.getEnemies()) {
			if (enemy.shouldSpawnPickup()) {
				pickupManager.addPickup(new WeaponPickup(enemy.getShip()
						.getPositionClone(), enemy.getWeapon().getName()));
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
					pcs.firePropertyChange(Constants.EVENT_ENEMY_DAMAGED, null,
							e.getShip());
					b.markForRemoval();
				}
			}
		}

		// Check enemy bullets against player
		if (!player.isInvincible()) {
			for (IBullet b : enemyBullets) {
				if (b.collidesWith(player.getShip())) {
					player.getShip().receiveDamage(b.getDamage());
					pcs.firePropertyChange(Constants.EVENT_ENTITY_INVINCIBLE,
							null, player);
					b.markForRemoval();
					break;
				}
			}
		}

		// Check Items against player
		for (int i = 0; i < pickupManager.getPickups().size(); i++) {
			WeaponPickup wp = pickupManager.getPickups().get(i);
			if (wp.collidesWith(player.getShip())) {
				player.giveWeapon(WeaponFactory.getNewWeapon(wp.getObjectName()));
				pickupManager.removePickup(i);
				i--;
			}
		}
	}

	@Override
	public IPlayer getPlayer() {
		return player;
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

	public PickupManager getPickupManager() {
		return pickupManager;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);

	}

	@Override
	public boolean isGameOver() {
		return player.getLives() < 1;
	}

	/**
	 * Resets the game
	 */
	public void reset() {
		bulletManager.clearAllBullets();
		enemyManager.clearAllEnemies();
		pickupManager.clearAllPickups();
		enemySpawner = new EnemySpawner(new RandomWaveList(1000,
				Difficulty.NORMAL));
		enemySpawner.addPropertyChangeListener(enemyManager);
		player.reset();
		player.giveWeapon(new BasicWeapon(bulletManager));

		// TODO(matachi) Reset enemyspawner too?
	}
}