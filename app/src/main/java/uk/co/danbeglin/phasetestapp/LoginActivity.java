package uk.co.danbeglin.phasetestapp;

import android.content.Intent;
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


    private String mUsername;
    private String mPassword;

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
