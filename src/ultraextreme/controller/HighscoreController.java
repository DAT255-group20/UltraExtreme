package ultraextreme.controller;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.controller.ControllerEvent.ControllerEventType;
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
		// Auto-generated method stub
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

			// Testing the database
			SQLiteDatabase readableDb = dbOpenHelper.getReadableDatabase();
			String query = "SELECT * FROM " + HighscoreDBOpenHelper.TABLE_NAME;
			Cursor cursor = readableDb.rawQuery(query, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Log.d("DEBUG", "Cursor pointing on: " + cursor.getString(0)
						+ ", " + cursor.getString(1));
				cursor.moveToNext();
			}
			// End of database test

			fireEvent(new ControllerEvent(this,
					ControllerEventType.SWITCH_TO_MENU));
			break;

		default:
			break;
		}
		return true;
	}
}