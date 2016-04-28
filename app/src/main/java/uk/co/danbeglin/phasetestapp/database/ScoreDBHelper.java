package uk.co.danbeglin.phasetestapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import uk.co.danbeglin.phasetestapp.database.ScoreDBSchema.ScoreTable;

/**
 * Created by danny on 28/04/16.
 */
public class ScoreDBHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "scoreDB.db";

    public ScoreDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ScoreTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        ScoreTable.Coloums.uuid + "," +
                        ScoreTable.Coloums.USER + "," +
                        ScoreTable.Coloums.PASS + "," +
                        ScoreTable.Coloums.SCORE +
                        ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
