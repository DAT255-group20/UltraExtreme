package ultraextreme.controller;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.GameModel;
import ultraextreme.view.GameScene;
import android.hardware.SensorManager;

/**
 * Controller for the game model.
 * 
 * @author Bjorn Persson Mattsson, Johan Gronvall
 * 
 */
public class GameController extends AbstractController implements
		IOnSceneTouchListener {

	private GameScene scene;
	private GameModel gameModel;
	private GameLoop gameLoop;

	double lastX = -1;
	double lastY = -1;

	public GameController(VertexBufferObjectManager vertexBufferObjectManager,
			SensorManager sensorManager) {
		gameModel = new GameModel();
		scene = new GameScene(gameModel, vertexBufferObjectManager,
				sensorManager);
		scene.setOnSceneTouchListener(this);

		// Start the game loop and add it as a listener to the bullet manage
		gameLoop = new GameLoop(scene, gameModel, scene.getBulletSprites(),
				scene.getEnemySprites(), vertexBufferObjectManager);
		gameModel.getBulletManager().addListener(gameLoop);
		gameModel.getEnemyManager().addPropertyChangeListener(gameLoop);
		scene.registerUpdateHandler(gameLoop);
	}

	@Override
	public boolean onSceneTouchEvent(Scene scene, TouchEvent event) {
		// Get the movement since last event
		double currentX = event.getX();
		double currentY = event.getY();
		double dX = 0;
		double dY = 0;
		if (lastX != -1 && lastY != -1) {
			dX = currentX - lastX;
			dY = currentY - lastY;
		}
		lastX = currentX;
		lastY = currentY;

		switch (event.getAction()) {
		case TouchEvent.ACTION_DOWN:

			gameLoop.setFiring(true);
			break;

		case TouchEvent.ACTION_MOVE:

			gameLoop.addToMovement(dX, dY);
			break;

		case TouchEvent.ACTION_UP:

			gameLoop.setFiring(false);
			break;

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