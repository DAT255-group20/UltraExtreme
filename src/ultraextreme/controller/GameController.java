package ultraextreme.controller;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.hardware.SensorManager;

import ultraextreme.model.GameModel;
import ultraextreme.view.GameScene;

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

	public GameController(VertexBufferObjectManager vertexBufferObjectManager, SensorManager sensorManager) {
		gameModel = new GameModel();
		scene = new GameScene(gameModel, vertexBufferObjectManager, sensorManager);
	}

	@Override
	public boolean onSceneTouchEvent(Scene arg0, TouchEvent arg1) {
		// TODO GameController.onSceneTouchEvent()
		return false;
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
}