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
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import ultraextreme.controller.ControllerEvent.ControllerEventType;
import ultraextreme.model.GameModel;
import ultraextreme.model.IPlayer;
import ultraextreme.model.IPlayerListener;
import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.view.GameScene;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Controller for the game model.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * @author Viktor Anderling
 * 
 */
public class GameController extends AbstractController implements
		IOnSceneTouchListener, IPlayerListener {

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
			final SimpleBaseGameActivity activity, float scaling,
			Camera camera, Font font) {
		super();
		gameModel = new GameModel();
		scene = new GameScene(gameModel, vertexBufferObjectManager,
				sensorManager,
				activity.getResources().getDisplayMetrics().widthPixels,
				activity.getResources().getDisplayMetrics().heightPixels,
				camera, font);
		scene.setOnSceneTouchListener(this);

		// Start the game loop and add it as a listener to the bullet manage
		gameLoop = new GameLoop(scene, gameModel, scene.getGameObjectSprites(),
				vertexBufferObjectManager, activity.getResources()
						.getDisplayMetrics().widthPixels, activity
						.getResources().getDisplayMetrics().heightPixels);
		gameModel.getBulletManager().addPropertyChangeListener(gameLoop);
		gameModel.getPickupManager().addPropertyChangeListener(gameLoop);
		gameModel.getEnemyManager().addPropertyChangeListener(gameLoop);
		gameModel.addPropertyChangeListener(gameLoop);
		gameModel.registerPlayerListener(this);
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

	@Override
	public void activateController() {
		resetGameModel();
		scene.setHUDVisible(true);
	}

	@Override
	public void deactivateController() {
		scene.setHUDVisible(false);
	}

	@Override
	public void playerUpdate(IPlayer player) {
		if (gameModel.isGameOver()) {
			fireEvent(new ControllerEvent(this,
					ControllerEventType.SWITCH_TO_GAMEOVER));
		}
	}

	private void resetGameModel() {
		Log.d("DEBUG", "Resetting the game model");
		gameLoop.reset();
		gameModel.reset();

		/*
		 * gameModel.getBulletManager().addPropertyChangeListener(gameLoop);
		 * gameModel.getPickupManager().addPropertyChangeListener(gameLoop);
		 * gameModel.getEnemyManager().addPropertyChangeListener(gameLoop);
		 * gameModel.addPropertyChangeListener(gameLoop);
		 * gameModel.registerPlayerListener(this);
		 */
	}

	/**
	 * @return An interface of the game model.
	 */
	public IUltraExtremeModel getGameModel() {
		return gameModel;
	}

	@Override
	public void backButtonPressed() {
		fireEvent(new ControllerEvent(this, ControllerEventType.SWITCH_TO_MENU));
	}
}