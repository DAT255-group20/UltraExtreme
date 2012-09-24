package ultraextreme.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.GameModel;
import ultraextreme.model.ModelInput;
import ultraextreme.model.enemy.EnemyManager;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.util.Position;
import ultraextreme.view.BulletSprite;
import ultraextreme.view.EnemySprite;
import ultraextreme.view.GameScene;
import ultraextreme.view.SpriteContainer;
import android.util.Log;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class GameLoop implements IUpdateHandler, PropertyChangeListener {

	private GameScene gameScene;
	private GameModel gameModel;
	private List<BulletSprite> bulletSprites;
	private List<EnemySprite> enemySprites;
	private VertexBufferObjectManager vertexBufferObjectManager;

	private boolean firing;
	private double moveX;
	private double moveY;

	public GameLoop(GameScene gameScene, GameModel gameModel,
			List<BulletSprite> bulletSprites, List<EnemySprite> enemySprites,
			VertexBufferObjectManager vertexBufferObjectManager) {
		this.gameScene = gameScene;
		this.gameModel = gameModel;
		this.bulletSprites = bulletSprites;
		this.enemySprites = enemySprites;
		this.vertexBufferObjectManager = vertexBufferObjectManager;
	}

	@Override
	public void onUpdate(float time) {
		gameModel.update(new ModelInput(moveX, moveY, firing, false), time);
		moveX = 0;
		moveY = 0;

		Position p = gameModel.getPlayer().getShip().getPosition();
		// Log.d("DEBUG", "" + p.getX());
		SpriteContainer.playerShip.setX((float) (p.getX()));
		SpriteContainer.playerShip.setY((float) (p.getY()));
		for (BulletSprite b : bulletSprites) {
			b.update();
		}
		for (EnemySprite e : enemySprites) {
			e.update();
		}
	}

	@Override
	public void reset() {
		// Auto-generated method stub

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("newBullet")) {
			BulletSprite b = new BulletSprite(
					(AbstractBullet) event.getNewValue(),
					vertexBufferObjectManager);
			bulletSprites.add(b);
			gameScene.attachChild(b);
			Log.d("Bullet list length View", "" + bulletSprites.size());
		} else if (event.getPropertyName().equals(EnemyManager.NEW_ENEMY)) {
			EnemySprite e = new EnemySprite(
					((IEnemy) event.getNewValue()).getShip(),
					vertexBufferObjectManager);
			enemySprites.add(e);
			gameScene.attachChild(e);
		}
	}

	/**
	 * Sets if the player is firing or not.
	 * 
	 * @param b
	 *            true if the player is firing, otherwise false.
	 */
	public void setFiring(boolean b) {
		this.firing = b;
	}

	/**
	 * Adds the provided change to the movement.
	 * 
	 * @param dX
	 *            The change in the x axis.
	 * @param dY
	 *            The change in the y axis.
	 */
	public void addToMovement(double dX, double dY) {
		this.moveX += dX;
		this.moveY += dY;
	}
}