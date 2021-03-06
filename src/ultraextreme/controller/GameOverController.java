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

import java.util.Calendar;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import ultraextreme.controller.ControllerEvent.ControllerEventType;
import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.view.GameOverScene;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class GameOverController extends AbstractController implements
		IOnMenuItemClickListener {

	private final GameOverScene scene;
	private HighscoreDBOpenHelper dbOpenHelper;
	private Long time;

	public GameOverController(IUltraExtremeModel gameModel,
			final Camera camera, final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager,
			HighscoreDBOpenHelper dbOpenHelper, BaseGameActivity activity) {
		super();
		scene = new GameOverScene(gameModel, camera, font,
				vertexBufferObjectManager, activity);
		scene.setOnMenuItemClickListener(this);
		this.dbOpenHelper = dbOpenHelper;
	}

	@Override
	public void activateController() {
		scene.updateScene();
		// Store the time when this view was switched to
		Calendar c = Calendar.getInstance();
		this.time = c.getTimeInMillis();
	}

	@Override
	public void deactivateController() {
		// Auto-generated method stub

	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene menuScene,
			final IMenuItem menuItem, float menuItemLocalX, float menuItemLocalY) {
		switch (menuItem.getID()) {
		case GameOverScene.GOTO_MENU:
			// It's only possible to switch from the game over view after 1.5
			// seconds
			Long time1 = Calendar.getInstance().getTimeInMillis();
			if (time1 < this.time + 1500) {
				break;
			}
			
			saveScore();

			fireEvent(new ControllerEvent(this,
					ControllerEventType.SWITCH_TO_MENU));
			break;

		default:
			break;
		}
		return true;
	}
	
	/**
	 * Save the score along with the tag in the database
	 */
	private void saveScore() {
		SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
		String nameTag = scene.getName();
		String score = Integer.toString(scene.getScore());
		String sqlCommand = "INSERT INTO "
				+ HighscoreDBOpenHelper.TABLE_NAME + " ("
				+ HighscoreDBOpenHelper.NAME + ", "
				+ HighscoreDBOpenHelper.HIGHSCORE + ") VALUES ('" + nameTag
				+ "', '" + score + "')";
		Log.d("DEBUG", "Saving highscore: " + sqlCommand);
		database.execSQL(sqlCommand);
		dbOpenHelper.close();
	}

	@Override
	public void backButtonPressed() {
		saveScore();
		fireEvent(new ControllerEvent(this, ControllerEventType.SWITCH_TO_MENU));
	}
}