package ultraextreme.controller;

import java.io.File;

import ultraextreme.model.util.Difficulty;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OptionsDatabase {

	private Context context;

	public OptionsDatabase(Context context) {
		this.context = context;
	}

	public Difficulty getDifficultyLevel() {
		OptionsDBOpenHelper optionsDBOpenHelper = new OptionsDBOpenHelper(
				context);

		// Reading from the database
		SQLiteDatabase readableDb = optionsDBOpenHelper.getReadableDatabase();
		String query = "SELECT * FROM " + OptionsDBOpenHelper.TABLE_NAME;
		Cursor cursor = readableDb.rawQuery(query, null);
		cursor.moveToFirst();
		
		int difficultyLevel = 0;
		
		while (!cursor.isAfterLast()) {

			String key = cursor.getString(cursor
					.getColumnIndex(OptionsDBOpenHelper.KEY));
			String value = cursor.getString(cursor
					.getColumnIndex(OptionsDBOpenHelper.VALUE));
			try {
				difficultyLevel = Integer.parseInt(value);
			} catch (NumberFormatException e) {

			}
			Log.e("hi", "kalleanka" + key + value);
			cursor.moveToNext();
		}
		optionsDBOpenHelper.close();
		
		switch (difficultyLevel) {
		case 0:
			return Difficulty.NORMAL;
		case 1:
			return Difficulty.HARD;
		case 2:
			return Difficulty.EXTREME;
		case 3:
			return Difficulty.ULTRAEXTREME;
		default:
			return Difficulty.NORMAL;
		}
	}

	public void setDifficultyLevel(Difficulty difficulty) {
		OptionsDBOpenHelper optionsDBOpenHelper = new OptionsDBOpenHelper(
				context);

		String key = "difficulty";
		int value;
		switch (difficulty) {
		case NORMAL:
			value = 0;
			break;
		case HARD:
			value = 1;
			break;
		case EXTREME:
			value = 2;
			break;
		case ULTRAEXTREME:
			value = 3;
			break;
		default:
			value = 0;
			break;
		}
		
		clearDatabase();

		SQLiteDatabase database = optionsDBOpenHelper.getWritableDatabase();
		String sqlCommand = "INSERT INTO " + OptionsDBOpenHelper.TABLE_NAME
				+ " (" + OptionsDBOpenHelper.KEY + ", "
				+ OptionsDBOpenHelper.VALUE + ") VALUES ('" + key + "', '"
				+ value + "')";
		Log.e("DEBUG", "Saving highscore: " + sqlCommand);
		database.execSQL(sqlCommand);
		optionsDBOpenHelper.close();
	}

	private void clearDatabase() {
		OptionsDBOpenHelper optionsDBOpenHelper = new OptionsDBOpenHelper(
				context);
		File db = new File(optionsDBOpenHelper.getWritableDatabase().getPath());
		optionsDBOpenHelper.close();
		db.delete();
	}

	public class OptionsDBOpenHelper extends SQLiteOpenHelper {

		private static final int DATABASE_VERSION = 2;
		public static final String TABLE_NAME = "optionsTable";
		public static final String KEY = "key";
		public static final String VALUE = "value";
		public static final String DATABASE_NAME = "optionsDatabase";

		private static final String HIGHSCORE_TABLE_CREATE = "CREATE TABLE "
				+ TABLE_NAME + " (" + KEY + " TEXT, " + VALUE + " TEXT);";

		public OptionsDBOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(HIGHSCORE_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Auto-generated method stub

		}
	}
}
