package ultraextreme.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Vector2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.GameModel;
import ultraextreme.model.ModelInput;
import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.EnemyShip;
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

	// TODO perhaps refactor this variable?
	private static final float blinkTime = 1;

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

	private List<Timer> timerList;

	public GameLoop(final GameScene gameScene, final GameModel gameModel,
			final List<GameObjectSprite> gameObjectSprites,
			final VertexBufferObjectManager vertexBufferObjectManager,
			double screenWidth, final double screenHeight) {

		final Dimension screenDimension = new Dimension(screenWidth,
				screenHeight);
		this.scalingQuotient = screenDimension.getQuotient(Constants
				.getLevelDimension());
		this.gameScene = gameScene;
		this.gameModel = gameModel;
		this.gameObjectSprites = gameObjectSprites;
		this.vertexBufferObjectManager = vertexBufferObjectManager;
		this.timerList = new LinkedList<Timer>();
		this.spriteFactory = SpriteFactory.getInstance();
	}

	@Override
	public void onUpdate(float time) {
		gameModel.update(new ModelInput(moveX / scalingQuotient.x, moveY
				/ scalingQuotient.y, firing, specialAttack), time);
		moveX = 0;
		moveY = 0;
		specialAttack = false;
		updateTimers(time);
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
		// TODO Refactor the "enemyHit" string.
		if (event.getPropertyName().equals(Constants.EVENT_ENEMY_DAMAGED)) {
			EnemyShip ship = (EnemyShip) event.getNewValue();
			Timer timer = new Timer(Constants.EVENT_ENEMY_DAMAGED, blinkTime, ship);
			timerList.add(timer);
			getSprite(ship).blink();
		} else if (event.getPropertyName().equals(Constants.EVENT_NEW_ENTITY)) {
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

		} else if (event.getPropertyName().equals(
				Constants.EVENT_REMOVED_ENTITY)) {
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
	 * Updates all the timers, performs actions and removes stopped timers.
	 * 
	 * @param timeElapsed
	 */
	private void updateTimers(float timeElapsed) {
		Iterator<Timer> i = timerList.iterator();
		while (i.hasNext()) {
			Timer timer = i.next();
			if (timer.isRunning()) {
				if (timer.update(timeElapsed)) {
					Object o = timer.getObject();
					timerAction(timer.getPropertyName(), o);
				}
			} else {
				i.remove();
			}
		}
	}

	// TODO Should this method be used at more places?
	/**
	 * Gets the sprite that corresponds to this entity.
	 * 
	 * @return The sprite if it exists, else null.
	 */
	private GameObjectSprite getSprite(IEntity entity) {
		Iterator<GameObjectSprite> i = gameObjectSprites.iterator();
		while (i.hasNext()) {
			GameObjectSprite sprite = ((GameObjectSprite) i.next());
			if (sprite.getEntity() == entity) {
				return sprite;
			}
		}
		return null;
	}

	/**
	 * Performs the corresponding action to the given object.
	 * 
	 * @param action
	 *            The action that is to be performed on the target object.
	 * @param o
	 *            The target Object.
	 */
	private void timerAction(String action, Object o) {
		if (action.equals(Constants.EVENT_ENEMY_DAMAGED)) {
			getSprite((IEntity) o).blink();
		}
		// TODO add more actions here!
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