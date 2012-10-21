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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.controller.ControllerEvent.ControllerEventType;
import ultraextreme.view.Highscore;
import ultraextreme.view.HighscoreScene;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HighscoreController extends AbstractController implements
		IOnMenuItemClickListener {
	private final HighscoreScene scene;
	private HighscoreDBOpenHelper dbOpenHelper;

	public HighscoreController(Camera camera, Font font,
			VertexBufferObjectManager vbo, HighscoreDBOpenHelper dbOpenHelper) {
		super();
		this.scene = new HighscoreScene(camera, font, vbo);
		scene.setOnMenuItemClickListener(this);
		this.dbOpenHelper = dbOpenHelper;
	}

	@Override
	public void activateController() {
		loadFromDatabase();
	}

	@Override
	public void deactivateController() {
		// Auto-generated method stub
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	private void loadFromDatabase() {

		List<Highscore> highscores = new ArrayList<Highscore>();

		// Reading from the database
		SQLiteDatabase readableDb = dbOpenHelper.getReadableDatabase();
		String query = "SELECT * FROM " + HighscoreDBOpenHelper.TABLE_NAME;
		Cursor cursor = readableDb.rawQuery(query, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {

			String highscoreName = cursor.getString(cursor
					.getColumnIndex(HighscoreDBOpenHelper.NAME));
			String scoreString = cursor.getString(cursor
					.getColumnIndex(HighscoreDBOpenHelper.HIGHSCORE));
			try {
				int highscoreValue = Integer.parseInt(scoreString);

				highscores.add(new Highscore(highscoreName, highscoreValue));

			} catch (NumberFormatException e) {

			}
			cursor.moveToNext();
		}
		dbOpenHelper.close();

		scene.displayHighscore(highscores);
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene menuScene,
			final IMenuItem menuItem, float menuItemLocalX, float menuItemLocalY) {
		switch (menuItem.getID()) {
		case HighscoreScene.GOTO_MENU:

			fireEvent(new ControllerEvent(this,
					ControllerEventType.SWITCH_TO_MENU));
			break;

		case HighscoreScene.CLEAR_LIST:

			// Delete the database file
			File db = new File(dbOpenHelper.getWritableDatabase().getPath());
			dbOpenHelper.close();
			db.delete();
			loadFromDatabase();

			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void backButtonPressed() {
		fireEvent(new ControllerEvent(this, ControllerEventType.SWITCH_TO_MENU));
	}
}