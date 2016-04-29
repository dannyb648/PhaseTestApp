package uk.co.danbeglin.phasetestapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    private TextView mResultDisplay;


    private static final String mFileName = "persistantData";
    private static final String TAG = "DEBUG";

    private Button mLogOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mResultDisplay = (TextView) findViewById(R.id.resultTextView);

        //GET DATA



        /*
        The code below is for the Onlnie database pulling.
        Due to constraints on ASyncTask, I cannot get them to work.
        I'm sure it is a trivial issue, so I have included the lines to highlight my potential feature.
         */

        /*
        InternetThread thread = new InternetThread();
        thread.doInBackground("http://jrbradley.co.uk:1337/leaderboard/get/highscores/1");
        */

        mLogOut = (Button)findViewById(R.id.LogOutButton);
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //These lines should not be in





                Intent i = new Intent(ResultActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });




        SharedPreferences sharedPrefAgain = getSharedPreferences(mFileName, MODE_PRIVATE);
        String mSavedDataAgain = sharedPrefAgain.getString(mFileName, null);

        if(mSavedDataAgain != null) {


            String mBreak = "[,]";
            String[] mDataList = mSavedDataAgain.split(mBreak);


            int mScore = Integer.parseInt(mDataList[3]);
            Log.d(TAG, String.valueOf(mScore));

            int finalScoreInt = mScore * 4;
            String finalScoreIntString = Integer.toString(finalScoreInt);

            mResultDisplay.setText(finalScoreIntString + "%");

        }




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "An invigilator has been signaled", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

