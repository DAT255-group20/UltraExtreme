package ultraextreme.model;

import java.util.List;

import ultraextreme.model.Enemy.AbstractEnemy;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.util.PlayerID;

/**
 * The main class for a running game.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class GameModel implements IUltraExtremeModel  {

	private Player player;
	
	//private AbstractEntity entity;
	
	// FIXME: BulletProductionQueue should be renamed to BulletManager
	private BulletProductionQueue bulletManager;
	
	//private AbstractEnemy enemyController;

	public GameModel() {
		bulletManager = new BulletProductionQueue();
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
	public BulletProductionQueue getBulletManager() {
		return bulletManager;
	}
}