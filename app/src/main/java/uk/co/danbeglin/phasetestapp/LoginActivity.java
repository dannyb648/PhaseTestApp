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
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "DEBUG";


    private Button mLoginButton;

    private String mFileName = "persistantData";


    private String mUsername;
    private String mPassword;

    private String mLastLogin;

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final EditText mUsernameEditText = (EditText) findViewById(R.id.loginEditText);
        final EditText mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);


        mLoginButton = (Button)findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUsername = mUsernameEditText.getText().toString();
                mPassword = mPasswordEditText.getText().toString();


                Log.d(TAG, mUsername);
                Log.d(TAG, mPassword);


                //REMOVING THIS CODE FIXED THE BUG!
                //IT ALSO MADE A NEW BUG!

                /*
                It seems that, by reseting the [3] & [4] of the data, (for q# and score) we destroy persistance,
                as whatever is saved is overwrote here.
                 */

                SharedPreferences sharedPref = getSharedPreferences(mFileName, MODE_PRIVATE);
                String mSaveData = sharedPref.getString(mFileName, null);
                mSaveData = mUsername + "," + mPassword + ",0,0";
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(mFileName, mSaveData);
                editor.commit();
                //*/

                Intent i = new Intent(LoginActivity.this, ExamActivity.class);
                startActivity(i);

                //store Login and Password in Database
                //Start activity


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "The invigilator has been signaled", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
