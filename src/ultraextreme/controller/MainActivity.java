package ultraextreme.controller;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import ultraextreme.model.util.Constants;
import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;
import ultraextreme.view.SpriteFactory;
import android.content.Context;
import android.graphics.Typeface;
import android.hardware.SensorManager;

/**
 * This is the main class of the game.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public class MainActivity extends SimpleBaseGameActivity implements
		IControllerListener {

	private GameController gameController;
	private MainMenuController mainMenuController;
	private SpriteFactory spriteFactory;
	// private GameModel gameModel;
	private Font defaultFont;
	private Camera camera;
	private Scene currentScene;
	private AbstractController currentController;

	// TODO PMD: The field name indicates a constant but its modifiers do not
	// These two should either be final, or not be in capital letters.
	// Capital letters indicates a constant, but constants MUST be final.
	private static int CAMERA_WIDTH;
	private static int CAMERA_HEIGHT;

	private float scaling;

	@Override
	public EngineOptions onCreateEngineOptions() {
		initializeResources();
		// TODO FindBugs: This instance method writes to a static field.
		// This is tricky to get correct if multiple instances are being
		// manipulated,
		// and generally bad practice.
		CAMERA_WIDTH = getResources().getDisplayMetrics().widthPixels;
		CAMERA_HEIGHT = getResources().getDisplayMetrics().heightPixels;
		scaling = (float) (getResources().getDisplayMetrics().heightPixels / Constants
				.getLevelDimension().getY());
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	private void initializeResources() {
		Resources res = Resources.getInstance();
		res.setResource(ResourceName.START_GAME, getString(R.string.start_game));
		res.setResource(ResourceName.LIVES, getString(R.string.lives));
		res.setResource(ResourceName.SCORE, getString(R.string.score));
	}

	@Override
	protected void onCreateResources() {
		spriteFactory = new SpriteFactory(this);
		defaultFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32f,
				Color.WHITE_ARGB_PACKED_INT);
		defaultFont.load();
	}

	@Override
	protected Scene onCreateScene() {
		gameController = new GameController(
				this.getVertexBufferObjectManager(),
				(SensorManager) this.getSystemService(Context.SENSOR_SERVICE),
				spriteFactory, this, scaling, camera, defaultFont);
		mainMenuController = new MainMenuController(camera, defaultFont,
				this.getVertexBufferObjectManager());

		gameController.addListener(this);
		mainMenuController.addListener(this);

		currentController = mainMenuController;
		updateScene();
		return currentScene;
	}

	@Override
	public void controllerListenerUpdate(final ControllerEvent event) {
		switch (event.getEventType()) {
		case SWITCH_TO_GAME:
			switchControllerTo(gameController);
			break;

		default:
			break;
		}
	}

	private void updateScene() {
		currentScene = currentController.getScene();
		getEngine().setScene(currentScene);
	}

	private void switchControllerTo(AbstractController newController) {
		currentController.deactivateController();
		currentController = newController;
		currentController.activateController();
		updateScene();
	}
}