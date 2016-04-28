package uk.co.danbeglin.phasetestapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.co.danbeglin.phasetestapp.database.ScoreDBHelper;
import uk.co.danbeglin.phasetestapp.database.ScoreDBSchema;

/**
 * Created by danny on 28/04/16.
 */
public class ScoreList {
    //sScoreList because it is a Static Var!
    private static ScoreList sScoreList;

    public static final String TAG = "DEBUG";

    private List<Score> mScores;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ScoreList get(Context context){
        if(sScoreList == null) {
            sScoreList = new ScoreList(context);
        }
        return sScoreList;
    }


    public ScoreList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ScoreDBHelper(mContext).getWritableDatabase();
        //mScores = new ArrayList<>();


        //GEN WITH FAKE SCORES
        /*
        for (int i = 0; i < 100; i++){
            Score score = new Score();
            Random randomGenerator = new Random();
            score.setUserName("User #" + i);
            score.setPassword("passwordtest" + i);
            score.setScore(randomGenerator.nextInt(100));
            //mScores.add(score);
        }
        */
    }

    public List<Score> getScores() {
        return new ArrayList<>();
    }

    public Score getScore(int id) {
        /*
        for (Score score : mScores) {
            if (score.getId() == id) {
                return score;
            }
        }
        */
        return null;
    }

    private static ContentValues getContentValues(Score score){
        ContentValues values = new ContentValues();

        values.put(ScoreDBSchema.ScoreTable.Coloums.uuid, Integer.valueOf(score.getId()));
        values.put(ScoreDBSchema.ScoreTable.Coloums.USER, score.getUserName());
        values.put(ScoreDBSchema.ScoreTable.Coloums.PASS, score.getPassword());
        values.put(ScoreDBSchema.ScoreTable.Coloums.SCORE, Integer.valueOf(score.getScore()));

        return values;
    }

    private Cursor queryScores(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ScoreDBSchema.ScoreTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null

        );

        return cursor;
    }

    public void add(Score score){
        ContentValues values = getContentValues(score);

        mDatabase.insert(ScoreDBSchema.ScoreTable.NAME, null, values);
        Log.d(TAG, "DATA ENTERED INTO TABLE!");


        //mScores.add(score);
        //Log.d(TAG, "Username added to DOMAIN LAYER" + score.getUserName());
        //Log.d(TAG, "Password added to DOMAIN LAYER"+ score.getPassword());
        //Log.d(TAG, "Score added to DOMAIN LAYER" + score.getScore());

    }
}
