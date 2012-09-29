package ultraextreme.controller;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import ultraextreme.model.GameModel;
import ultraextreme.view.GameScene;
import ultraextreme.view.SpriteFactory;
import android.hardware.SensorManager;
import android.view.MotionEvent;

/**
 * Controller for the game model.
 * 
 * @author Bjorn Persson Mattsson, Johan Gronvall
 * 
 */
public class GameController extends AbstractController implements
		IOnSceneTouchListener {

	private static final int INVALID_POINTER_ID = -1;
	// The 'active pointer' is the one currently moving the player.
	private int activePointerId = INVALID_POINTER_ID;

	private final GameScene scene;
	private final GameModel gameModel;
	private final GameLoop gameLoop;

	private float lastX = -1;
	private float lastY = -1;

	public GameController(
			final VertexBufferObjectManager vertexBufferObjectManager,
			final SensorManager sensorManager,
			final SpriteFactory spriteFactory,
			final SimpleBaseGameActivity activity) {
		super();
		gameModel = new GameModel();
		scene = new GameScene(gameModel, vertexBufferObjectManager,
				sensorManager, spriteFactory);
		scene.setOnSceneTouchListener(this);

		// Start the game loop and add it as a listener to the bullet manage
		gameLoop = new GameLoop(scene, gameModel, scene.getGameObjectSprites(),
				vertexBufferObjectManager, spriteFactory, activity
						.getResources().getDisplayMetrics().widthPixels,
				activity.getResources().getDisplayMetrics().heightPixels);
		gameModel.getBulletManager().addPropertyChangeListener(gameLoop);

		gameModel.getEnemyManager().addPropertyChangeListener(gameLoop);
		scene.registerUpdateHandler(gameLoop);
	}

	@Override
	public boolean onSceneTouchEvent(final Scene scene, TouchEvent tEvent) {
		final MotionEvent event = tEvent.getMotionEvent();
		// Multitouch handling code inspired by following android dev blog post
		// http://android-developers.blogspot.se/2010/06/making-sense-of-multitouch.html
		final int action = event.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			lastX = event.getX();
			lastY = event.getY();

			// Save the ID of this pointer
			activePointerId = event.getPointerId(0);
			gameLoop.setFiring(true);
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			// Find the index of the active pointer and fetch its position
			final int pointerIndex = event.findPointerIndex(activePointerId);
			final float x = event.getX(pointerIndex);
			final float y = event.getY(pointerIndex);

			final float dX = x - lastX;
			final float dY = y - lastY;

			gameLoop.addToMovement(dX, dY);

			lastX = x;
			lastY = y;
			break;
		}

		case MotionEvent.ACTION_UP: {
			activePointerId = INVALID_POINTER_ID;
			gameLoop.setFiring(false);
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			activePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			// Extract the index of the pointer that left the touch sensor
			final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			final int pointerId = event.getPointerId(pointerIndex);
			if (pointerId == activePointerId) {
				// This was our active pointer going up. Choose a new active
				// pointer and adjust accordingly.
				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
				lastX = event.getX(newPointerIndex);
				lastY = event.getY(newPointerIndex);
				activePointerId = event.getPointerId(newPointerIndex);
			}
			break;
		}

		case MotionEvent.ACTION_POINTER_DOWN: {
			gameLoop.fireSpecialAttack();
			break;
		}

		default:
			break;
		}

		return true;
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
}