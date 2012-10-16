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
import android.util.Log;

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
}