package ultraextreme.model;

import ultraextreme.model.entity.Entity;

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

	public void update() {
	}
}