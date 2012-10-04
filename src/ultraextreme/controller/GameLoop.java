package ultraextreme.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.vecmath.Vector2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.GameModel;
import ultraextreme.model.ModelInput;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.IEntity;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.view.GameObjectSprite;
import ultraextreme.view.GameScene;
import ultraextreme.view.SpriteFactory;
import android.util.Log;

/**
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public class GameLoop implements IUpdateHandler, PropertyChangeListener {

	final private GameScene gameScene;
	final private GameModel gameModel;
	final private List<GameObjectSprite> gameObjectSprites;
	final private VertexBufferObjectManager vertexBufferObjectManager;
	final private SpriteFactory spriteFactory;
	private Vector2d scalingQuotient;

	private boolean firing;
	private double moveX;
	private double moveY;
	private boolean specialAttack;

	public GameLoop(final GameScene gameScene, final GameModel gameModel,
			final List<GameObjectSprite> gameObjectSprites,
			final VertexBufferObjectManager vertexBufferObjectManager,
			SpriteFactory spriteFactory, double screenWidth,
			final double screenHeight) {

		final Dimension screenDimension = new Dimension(screenWidth,
				screenHeight);
		this.scalingQuotient = screenDimension.getQuotient(
				Constants.getLevelDimension());
		this.gameScene = gameScene;
		this.gameModel = gameModel;
		this.gameObjectSprites = gameObjectSprites;
		this.vertexBufferObjectManager = vertexBufferObjectManager;
		this.spriteFactory = spriteFactory;
	}

	@Override
	public void onUpdate(float time) {
		gameModel.update(new ModelInput(moveX / scalingQuotient.x, moveY
				/ scalingQuotient.y, firing, specialAttack), time);
		moveX = 0;
		moveY = 0;
		specialAttack = false;

		for (GameObjectSprite sprite : gameObjectSprites) {
			sprite.update();
		}
	}

	@Override
	public void reset() {
		// Auto-generated method stub

	}

	/**
	 * If a new sprite is created, adds it to the scene and to the list if a
	 * sprite is removed, removes it from the scene and the list
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("add")) {
			IEntity entity;

			if (event.getNewValue() instanceof IEnemy) {
				entity = ((IEnemy) event.getNewValue()).getShip();
			} else { // if item or bullet
				entity = (IEntity) event.getNewValue();

				final GameObjectSprite newSprite = spriteFactory.getNewSprite(
						entity, vertexBufferObjectManager);
				gameScene.attachChild(newSprite);
				gameObjectSprites.add(newSprite);
			}

		} else if (event.getPropertyName().equals("remove")) {
			IEntity entity;
			if (event.getNewValue() instanceof IEnemy) {
				entity = ((IEnemy) event.getNewValue()).getShip();
			} else { // if item or bullet
				entity = (IEntity) event.getNewValue();
			}
			// Find the GameObjectSprite that has a reference to this entity and
			// remove
			// it from the GameObjectSprite list and from the render scene.
			// Note: It's generally not a very good idea to remove elements when
			// iterating through them, but this breaks the loop when one element
			// is
			// removed.
			for (GameObjectSprite sprite : gameObjectSprites) {
				if (sprite.getEntity() == entity) {
					gameScene.detachChild(sprite);
					gameObjectSprites.remove(sprite);
					break;
				}
			}
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
	public void fireSpecialAttack() {
		specialAttack = true;
		Log.d("DEBUG", "specialAttack " + specialAttack);
	}
}