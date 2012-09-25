package ultraextreme.controller;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.hardware.SensorManager;

public class MainActivity extends SimpleBaseGameActivity implements
		IControllerListener {

	private GameController gameController;
	private MainMenuController mainMenuController;
	// private GameModel gameModel;
	private Font defaultFont;

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 800;

	@Override
	public EngineOptions onCreateEngineOptions() {
		Camera mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	}

	@Override
	protected void onCreateResources() {
		defaultFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
		defaultFont.load();
	}

	@Override
	protected Scene onCreateScene() {
		this.gameController = new GameController(
				this.getVertexBufferObjectManager(),
				(SensorManager) this.getSystemService(Context.SENSOR_SERVICE));
		this.mainMenuController = new MainMenuController(defaultFont, this.getVertexBufferObjectManager());
		return mainMenuController.getScene();
	}

	@Override
	public void controllerListenerUpdate(ControllerEvent event) {
		// TODO MainActivity.controllerListenerUpdate()
	}
}