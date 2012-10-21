/* ============================================================
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,
 * Viktor Anderling
 *
 * This file is part of UltraExtreme.
 *
 * UltraExtreme is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UltraExtreme is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.
 * ============================================================ */

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
	private GameOverController gameOverController;
	private HighscoreController highscoreController;
	private Font defaultFont;
	private Camera camera;
	private Scene currentScene;
	private AbstractController currentController;
	private HighscoreDBOpenHelper highscoreDBOpenHelper;

	@Override
	public void controllerListenerUpdate(final ControllerEvent event) {
		switch (event.getEventType()) {
		case SWITCH_TO_GAME:
			switchControllerTo(gameController);
			break;

		case SWITCH_TO_MENU:
			switchControllerTo(mainMenuController);
			break;

		case SWITCH_TO_GAMEOVER:
			switchControllerTo(gameOverController);
			break;

		case SWITCH_TO_HIGHSCORE:
			switchControllerTo(highscoreController);
			break;

		case EXIT_GAME:
			finish();
			break;

		default:
			break;
		}
	}

	private void initializeResources() {
		Resources res = Resources.getInstance();
		res.setResource(ResourceName.MENU_START_GAME_TEXT,
				getString(R.string.menu_start_game));
		res.setResource(ResourceName.MENU_HIGHSCORE_TEXT,
				getString(R.string.menu_highscore_text));
		res.setResource(ResourceName.CLEAR_HIGHSCORE,
				getString(R.string.clear_highscore));
		res.setResource(ResourceName.LIVES, getString(R.string.lives));
		res.setResource(ResourceName.SCORE, getString(R.string.score));
		res.setResource(ResourceName.GOTO_MENU, getString(R.string.goto_menu));
		res.setResource(ResourceName.DEFAULT_HIGHSCORE_NAME,
				getString(R.string.default_highscore_name));
		res.setResource(ResourceName.HIGHSCORE_INPUT_TEXT,
				getString(R.string.highscore_input_text));
		res.setResource(ResourceName.HIGHSCORE_INPUT_TITLE,
				getString(R.string.highscore_input_title));
	}

	@Override
	public EngineOptions onCreateEngineOptions() {
		initializeResources();
		int cameraWidth = getResources().getDisplayMetrics().widthPixels;
		int cameraHeight = getResources().getDisplayMetrics().heightPixels;
		camera = new Camera(0, 0, cameraWidth, cameraHeight);
		return new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR,
				new RatioResolutionPolicy(cameraWidth, cameraHeight), camera);
	}

	@Override
	protected void onCreateResources() {
		SpriteFactory.initialize(this);
		defaultFont = FontFactory.create(this.getFontManager(),
				this.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32f,
				Color.WHITE_ARGB_PACKED_INT);
		defaultFont.load();
	}

	@Override
	protected Scene onCreateScene() {
		highscoreDBOpenHelper = new HighscoreDBOpenHelper(this);
		gameController = new GameController(
				this.getVertexBufferObjectManager(),
				(SensorManager) this.getSystemService(Context.SENSOR_SERVICE),
				this, camera, defaultFont);
		mainMenuController = new MainMenuController(camera,
				this.getVertexBufferObjectManager());
		gameOverController = new GameOverController(
				gameController.getGameModel(), camera, defaultFont,
				this.getVertexBufferObjectManager(), highscoreDBOpenHelper,
				this);
		highscoreController = new HighscoreController(camera, defaultFont,
				this.getVertexBufferObjectManager(), highscoreDBOpenHelper);

		gameController.addListener(this);
		mainMenuController.addListener(this);
		gameOverController.addListener(this);
		highscoreController.addListener(this);

		currentController = mainMenuController;
		updateScene();
		return currentScene;
	}

	private void switchControllerTo(AbstractController newController) {
		currentController.deactivateController();
		currentController = newController;
		currentController.activateController();
		updateScene();
	}

	private void updateScene() {
		currentScene = currentController.getScene();
		getEngine().setScene(currentScene);
	}

	@Override
	public void onBackPressed() {
		currentController.backButtonPressed();
	}
}