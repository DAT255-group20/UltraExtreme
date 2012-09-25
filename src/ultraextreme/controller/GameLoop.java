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
import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.util.Position;
import ultraextreme.view.GameObjectSprite;
import ultraextreme.view.GameScene;
import ultraextreme.view.SpriteContainer;
import ultraextreme.view.SpriteFactory;
import android.util.Log;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class GameLoop implements IUpdateHandler, PropertyChangeListener {

	private GameScene gameScene;
	private GameModel gameModel;
	private List<GameObjectSprite> gameObjectSprites;
	private VertexBufferObjectManager vertexBufferObjectManager;
	private SpriteFactory spriteFactory;
	
	private boolean firing;
	private double moveX;
	private double moveY;

	public GameLoop(GameScene gameScene, GameModel gameModel,
			List<GameObjectSprite> gameObjectSprites,
			VertexBufferObjectManager vertexBufferObjectManager) {
		this.gameScene = gameScene;
		this.gameModel = gameModel;
		this.gameObjectSprites = gameObjectSprites;
		this.vertexBufferObjectManager = vertexBufferObjectManager;
		spriteFactory = new SpriteFactory();
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
		for (GameObjectSprite sprite: gameObjectSprites) {
			sprite.update();
		}
	}

	@Override
	public void reset() {
		// Auto-generated method stub

	}

//	@Override
//	public void propertyChange(PropertyChangeEvent event) {
//		if (event.getPropertyName().equals("newBullet")) {
//			BulletSprite b = new BulletSprite(
//					(AbstractBullet) event.getNewValue(),
//					vertexBufferObjectManager);
//			bulletSprites.add(b);
//			gameScene.attachChild(b);
//			Log.d("Bullet list length View", "" + bulletSprites.size());
//		} else if (event.getPropertyName().equals(EnemyManager.NEW_ENEMY)) {
//			EnemySprite e = new EnemySprite(
//					((IEnemy) event.getNewValue()).getShip(),
//					vertexBufferObjectManager);
//			enemySprites.add(e);
//			gameScene.attachChild(e);
//		}
//	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if(event.getPropertyName() == "new") {
			AbstractEntity entity = (AbstractEntity)event.getNewValue();
			spriteFactory.getNewSprite(entity, vertexBufferObjectManager,entity.getName());
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