package uk.co.danbeglin.phasetestapp;

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

public class ResultActivity extends AppCompatActivity {

    private TextView mResultDisplay;
    private static final String mFileName = "persistantData";
    private static final String TAG = "DEBUG";

    private Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mResultDisplay = (TextView) findViewById(R.id.resultTextView);





        SharedPreferences sharedPrefAgain = getSharedPreferences(mFileName, MODE_PRIVATE);
        String mSavedDataAgain = sharedPrefAgain.getString(mFileName, null);

        if(mSavedDataAgain != null) {


            String mBreak = "[,]";
            String[] mDataList = mSavedDataAgain.split(mBreak);


            int mScore = Integer.parseInt(mDataList[3]);
            Log.d(TAG, String.valueOf(mScore));

            int finalScoreInt = mScore;
            String finalScoreIntString = Integer.toString(finalScoreInt);

            mResultDisplay.setText(finalScoreIntString + "%");

        }




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

