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
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.item.BulletManagerListener;
import ultraextreme.model.util.Position;
import ultraextreme.view.BulletSprite;
import ultraextreme.view.EnemySprite;
import ultraextreme.view.GameScene;
import ultraextreme.view.SpriteContainer;
import android.util.Log;

/**
 * 
 * @author Daniel Jonsson, Bjorn Persson Mattsson
 * 
 */
public class GameLoop implements IUpdateHandler, PropertyChangeListener,
		BulletManagerListener {

	private GameScene gameScene;
	private GameModel gameModel;
	private List<BulletSprite> bulletSprites;
	private List<EnemySprite> enemySprites;
	private VertexBufferObjectManager vertexBufferObjectManager;

	private boolean firing;
	private double moveX;
	private double moveY;
	private boolean specialAttack;

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
		gameModel.update(new ModelInput(moveX, moveY, firing, specialAttack), time);
		moveX = 0;
		moveY = 0;
		specialAttack = false;

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
	
	/**
	 * Orders the player to fire a special attack.
	 */
	public void fireSpecialAttack()
	{
		specialAttack = true;
	}

	@Override
	public void bulletAdded(IBullet bullet) {
		BulletSprite b = new BulletSprite(bullet, vertexBufferObjectManager);
		bulletSprites.add(b);
		gameScene.attachChild(b);
	}

	@Override
	public void bulletRemoved(IBullet removedBullet) {
		// Find the BulletSprite that has a reference to this bullet and remove
		// it from the bullet sprite list and from the render scene.
		// Note: It's generally not a very good idea to remove elements when
		// iterating through them, but this breaks the loop when one element is
		// removed.
		for (BulletSprite b : bulletSprites) {
			if (b.getIBullet() == removedBullet) {
				gameScene.detachChild(b);
				bulletSprites.remove(b);
				break;
			}
		}
	}
}