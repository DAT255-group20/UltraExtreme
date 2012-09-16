package ultraextreme.model;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.Entity;
import ultraextreme.model.util.PlayerID;

/**
 * The main class for a running game.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class GameModel implements IUltraExtremeModel {

	private Player player;
	private Entity entity;
	private BulletProductionQueue bulletProdQueue;
	private Enemy enemyController;
	private List<AbstractBullet> bullets;
	
	public GameModel() {
		bulletProdQueue = new BulletProductionQueue();
		player = new Player(PlayerID.PLAYER1, bulletProdQueue);
		bullets = new ArrayList<AbstractBullet>();
	}
	
	/**
	 * Run an update on the model.
	 * @param input Input variables to the model.
	 * @param timeElapsed Time in seconds since last update.
	 */
	public void update(ModelInput input, float timeElapsed) {
		player.update(input, timeElapsed);
		bullets.addAll(bulletProdQueue.getNewBullets());
		bulletProdQueue.clear();
		for (AbstractBullet bullet : bullets) {
			bullet.doMovement(timeElapsed);
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public List<AbstractBullet> getBullets() {
		return bullets;
	}
}