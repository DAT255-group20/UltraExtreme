package ultraextreme.controller;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.content.Context;
import android.hardware.SensorManager;

public class MainActivity extends SimpleBaseGameActivity implements
		IControllerListener {

	private GameController gameController;
	private MainMenuController mainMenuController;
	// private GameModel gameModel;

	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;

	@Override
	public EngineOptions onCreateEngineOptions() {
		Camera mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	}

	@Override
	protected void onCreateResources() {
		// TODO MainActivity.onCreateResources()
	}

	@Override
	protected Scene onCreateScene() {
		this.gameController = new GameController(
				this.getVertexBufferObjectManager(),
				(SensorManager) this.getSystemService(Context.SENSOR_SERVICE));
		return gameController.getScene();
	}

	@Override
	public void controllerListenerUpdate() {
		// TODO MainActivity.controllerListenerUpdate()
	}
}