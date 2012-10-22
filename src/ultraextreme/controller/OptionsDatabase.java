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

import ultraextreme.model.util.Difficulty;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class wraps an SQLite database, so the settings are persistent between
 * sessions.
 * 
 * @author Daniel Jonsson
 * 
 */
public class OptionsDatabase {

	private Context context;

	private static final String DIFFICULTY = "difficulty";

	public OptionsDatabase(Context context) {
		this.context = context;
	}

	/**
	 * Return the value of the difficulty level setting.
	 * 
	 * @return The chosen difficulty level. If non is set NORMAL will be
	 *         returned.
	 */
	public Difficulty getDifficultyLevel() {
		OptionsDBOpenHelper optionsDBOpenHelper = new OptionsDBOpenHelper(
				context);

		// Reading from the database
		SQLiteDatabase readableDb = optionsDBOpenHelper.getReadableDatabase();
		String query = "SELECT * FROM " + OptionsDBOpenHelper.TABLE_NAME;
		Cursor cursor = readableDb.rawQuery(query, null);
		cursor.moveToFirst();

		int difficultyLevel = 0;

		// Traverse the rows in the database
		while (!cursor.isAfterLast()) {
			String key = cursor.getString(cursor
					.getColumnIndex(OptionsDBOpenHelper.KEY));
			String value = cursor.getString(cursor
					.getColumnIndex(OptionsDBOpenHelper.VALUE));
			if (key.equals(DIFFICULTY)) {
				try {
					difficultyLevel = Integer.parseInt(value);
				} catch (NumberFormatException e) {

				}
			}
			cursor.moveToNext();
		}
		optionsDBOpenHelper.close();

		// Translate the database's int value to a Difficulty value
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

	/**
	 * Store a difficulty setting in the database. If one is already stored, it
	 * will be replaced.
	 * 
	 * @param difficulty
	 *            What difficulty level that should be stored.
	 */
	public void setDifficultyLevel(Difficulty difficulty) {
		OptionsDBOpenHelper optionsDBOpenHelper = new OptionsDBOpenHelper(
				context);

		// Translate the difficulty to an int value, and store it in the
		// database
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

		// If more settings are added this needs to be replaces to something
		// that only removes the DIFFICULTY row
		clearDatabase();

		SQLiteDatabase database = optionsDBOpenHelper.getWritableDatabase();
		String sqlCommand = "INSERT INTO " + OptionsDBOpenHelper.TABLE_NAME
				+ " (" + OptionsDBOpenHelper.KEY + ", "
				+ OptionsDBOpenHelper.VALUE + ") VALUES ('" + DIFFICULTY
				+ "', '" + value + "')";
		database.execSQL(sqlCommand);
		optionsDBOpenHelper.close();
	}

	/**
	 * Delete all database rows.
	 */
	private void clearDatabase() {
		OptionsDBOpenHelper optionsDBOpenHelper = new OptionsDBOpenHelper(
				context);
		File db = new File(optionsDBOpenHelper.getWritableDatabase().getPath());
		optionsDBOpenHelper.close();
		db.delete();
	}

	private class OptionsDBOpenHelper extends SQLiteOpenHelper {

		private static final int DATABASE_VERSION = 2;
		public static final String TABLE_NAME = "optionsTable";
		public static final String KEY = "key";
		public static final String VALUE = "value";
		public static final String DATABASE_NAME = "optionsDatabase";

		private static final String OPTIONS_TABLE_CREATE = "CREATE TABLE "
				+ TABLE_NAME + " (" + KEY + " TEXT, " + VALUE + " TEXT);";

		public OptionsDBOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(OPTIONS_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Auto-generated method stub
		}
	}
}
