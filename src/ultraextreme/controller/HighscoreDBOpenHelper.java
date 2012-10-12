package ultraextreme.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HighscoreDBOpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "highscoreTable";
	public static final String NAME = "name";
	public static final String HIGHSCORE = "highscore";
	private static final String DATABASE_NAME = "highscoreDatabase";
	
    private static final String HIGHSCORE_TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                NAME + " TEXT, " +
                HIGHSCORE + " TEXT);";

    public HighscoreDBOpenHelper(Context context) {
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