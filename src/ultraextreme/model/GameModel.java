package ultraextreme.model;

import java.util.List;

import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.util.PlayerID;

/**
 * The main class for a running game.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class GameModel implements IUltraExtremeModel {

	private Player player;

	// private AbstractEntity entity;

	private BulletManager bulletManager;

	// private AbstractEnemy enemyController;

	public GameModel() {
		bulletManager = new BulletManager();
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
	@Override
	public void update(ModelInput input, float timeElapsed) {
		player.update(input, timeElapsed);
		for (AbstractBullet bullet : bulletManager.getNewBullets()) {
			bullet.doMovement(timeElapsed);
		}
	}

	@Override
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public List<AbstractBullet> getBullets() {
		return bulletManager.getNewBullets();
	}

	/**
	 * @return A model interface with only get methods.
	 */
	public IUltraExtremeModel getModelInterface() {
		return this;
	}

	@Override
	public BulletManager getBulletManager() {
		return bulletManager;
	}
}