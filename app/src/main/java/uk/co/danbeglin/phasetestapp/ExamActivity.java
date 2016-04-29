package uk.co.danbeglin.phasetestapp;
//The package for Java


//Imports all auto gen'd by IntelliJ

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Main class for Activity
//mainExamActivity Class, inherits from AppCompatActivity
//AppCompatActivity makes this compatable for older Android versions
public class ExamActivity extends AppCompatActivity {

    //TAG is used generally by everyone. Standard Android.
    //Allows me to tailor my debug prints.
    private static final String TAG = "DEBUG";

    //Keys for save instance state code.
    //Essentially allows us to flip orientation without losing data
    //NOT PERSISTANT
    private static final String KEYSTATE = "index";
    private static final String KEYSCORE = "index2";

    //This is persistant. This is the file name for the file we store.
    private static final String mFileName = "persistantData";

    //This is question we are on.
    private int mCurrentQuestion = 0;
    //This is the amount of correct answers user has.
    private int mScore = 0;
    //These store the inputs from earlier.
    //We pull them from persistantData files, which isnt Ideal.
    //But it makes overall stuff quicker and easier.
    private String mUsername;
    private String mPassword;

    //This is used to store the activity context during the db stuff
    private Context mDBContext;

    //These are our Buttons for each answer.
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;

    //Text views for all changing stuff. Question#, question and answers.
    private TextView mExamQuestionNumberTextView;
    private TextView mExamQuestionTextView;
    private TextView mExamAnswerATextView;
    private TextView mExamAnswerBTextView;
    private TextView mExamAnswerCTextView;
    private TextView mExamAnswerDTextView;

    //List of Questions Number Strings from strings.xml
    //Reused a class because I'm lazy.
    //Maybe I should make new classes: ExamText and have ExamQuestions inherit from it?
    //If I have time, Ill do just that.
    //If you see this, guess who ran out of time?
    private ExamAnswer[] mExamQuestionNumber = new ExamAnswer[] {

            //Now I admit, this here is lazy coding. I should
            //have just made a new class ExamQuestionNumber.
            //When I have more time, Ill go fix that, however it works.
            new ExamAnswer(R.string.question_number_1),
            new ExamAnswer(R.string.question_number_2),
            new ExamAnswer(R.string.question_number_3),
            new ExamAnswer(R.string.question_number_4),
            new ExamAnswer(R.string.question_number_5),
            new ExamAnswer(R.string.question_number_6),
            new ExamAnswer(R.string.question_number_7),
            new ExamAnswer(R.string.question_number_8),
            new ExamAnswer(R.string.question_number_9),
            new ExamAnswer(R.string.question_number_10),
            new ExamAnswer(R.string.question_number_11),
            new ExamAnswer(R.string.question_number_12),
            new ExamAnswer(R.string.question_number_13),
            new ExamAnswer(R.string.question_number_14),
            new ExamAnswer(R.string.question_number_15),
            new ExamAnswer(R.string.question_number_16),
            new ExamAnswer(R.string.question_number_17),
            new ExamAnswer(R.string.question_number_18),
            new ExamAnswer(R.string.question_number_19),
            new ExamAnswer(R.string.question_number_20),
            new ExamAnswer(R.string.question_number_21),
            new ExamAnswer(R.string.question_number_22),
            new ExamAnswer(R.string.question_number_23),
            new ExamAnswer(R.string.question_number_24),
            new ExamAnswer(R.string.question_number_25),


    };

    //Same as above, but these Objects take a Char for correct answer.
    private ExamQuestion[] mExamQuestions = new ExamQuestion[] {
            new ExamQuestion(R.string.exam_question_1, 'B'),
            new ExamQuestion(R.string.exam_question_2, 'A'),
            new ExamQuestion(R.string.exam_question_3, 'B'),
            new ExamQuestion(R.string.exam_question_4, 'C'),
            new ExamQuestion(R.string.exam_question_5, 'D'),
            new ExamQuestion(R.string.exam_question_6, 'B'),
            new ExamQuestion(R.string.exam_question_7, 'A'),
            new ExamQuestion(R.string.exam_question_8, 'D'),
            new ExamQuestion(R.string.exam_question_9, 'A'),
            new ExamQuestion(R.string.exam_question_10, 'B'),
            new ExamQuestion(R.string.exam_question_11, 'C'),

            new ExamQuestion(R.string.exam_question_12, 'A'),
            new ExamQuestion(R.string.exam_question_13, 'A'),
            new ExamQuestion(R.string.exam_question_14, 'B'),
            new ExamQuestion(R.string.exam_question_15, 'D'),
            new ExamQuestion(R.string.exam_question_16, 'D'),
            new ExamQuestion(R.string.exam_question_17, 'B'),
            new ExamQuestion(R.string.exam_question_18, 'B'),
            new ExamQuestion(R.string.exam_question_19, 'B'),
            new ExamQuestion(R.string.exam_question_20, 'B'),
            new ExamQuestion(R.string.exam_question_21, 'C'),
            new ExamQuestion(R.string.exam_question_22, 'C'),
            new ExamQuestion(R.string.exam_question_23, 'A'),
            new ExamQuestion(R.string.exam_question_24, 'A'),
            new ExamQuestion(R.string.exam_question_25, 'A'),
    };

    //Exam answers. Maybe there was a better way to do this, but I dont know it.
    private ExamAnswer[] mExamAnswersA = new ExamAnswer[] {
            new ExamAnswer(R.string.exam_answer_1a),
            new ExamAnswer(R.string.exam_answer_2a),
            new ExamAnswer(R.string.exam_answer_3a),
            new ExamAnswer(R.string.exam_answer_4a),
            new ExamAnswer(R.string.exam_answer_5a),
            new ExamAnswer(R.string.exam_answer_6a),
            new ExamAnswer(R.string.exam_answer_7a),
            new ExamAnswer(R.string.exam_answer_8a),
            new ExamAnswer(R.string.exam_answer_9a),
            new ExamAnswer(R.string.exam_answer_10a),
            new ExamAnswer(R.string.exam_answer_11a),
            new ExamAnswer(R.string.exam_answer_12a),
            new ExamAnswer(R.string.exam_answer_13a),
            new ExamAnswer(R.string.exam_answer_14a),
            new ExamAnswer(R.string.exam_answer_15a),
            new ExamAnswer(R.string.exam_answer_16a),
            new ExamAnswer(R.string.exam_answer_17a),
            new ExamAnswer(R.string.exam_answer_18a),
            new ExamAnswer(R.string.exam_answer_19a),
            new ExamAnswer(R.string.exam_answer_20a),
            new ExamAnswer(R.string.exam_answer_21a),
            new ExamAnswer(R.string.exam_answer_22a),
            new ExamAnswer(R.string.exam_answer_23a),
            new ExamAnswer(R.string.exam_answer_24a),
            new ExamAnswer(R.string.exam_answer_25a),

    };

    private ExamAnswer[] mExamAnswersB = new ExamAnswer[]{
            new ExamAnswer(R.string.exam_answer_1b),
            new ExamAnswer(R.string.exam_answer_2b),
            new ExamAnswer(R.string.exam_answer_3b),
            new ExamAnswer(R.string.exam_answer_4b),
            new ExamAnswer(R.string.exam_answer_5b),
            new ExamAnswer(R.string.exam_answer_6b),
            new ExamAnswer(R.string.exam_answer_7b),
            new ExamAnswer(R.string.exam_answer_8b),
            new ExamAnswer(R.string.exam_answer_9b),
            new ExamAnswer(R.string.exam_answer_10b),
            new ExamAnswer(R.string.exam_answer_11b),
            new ExamAnswer(R.string.exam_answer_12b),
            new ExamAnswer(R.string.exam_answer_13b),
            new ExamAnswer(R.string.exam_answer_14b),
            new ExamAnswer(R.string.exam_answer_15b),
            new ExamAnswer(R.string.exam_answer_16b),
            new ExamAnswer(R.string.exam_answer_17b),
            new ExamAnswer(R.string.exam_answer_18b),
            new ExamAnswer(R.string.exam_answer_19b),
            new ExamAnswer(R.string.exam_answer_20b),
            new ExamAnswer(R.string.exam_answer_21b),
            new ExamAnswer(R.string.exam_answer_22b),
            new ExamAnswer(R.string.exam_answer_23b),
            new ExamAnswer(R.string.exam_answer_24b),
            new ExamAnswer(R.string.exam_answer_25b),
    };

    private ExamAnswer[] mExamAnswersC = new ExamAnswer[]{
            new ExamAnswer(R.string.exam_answer_1c),
            new ExamAnswer(R.string.exam_answer_2c),
            new ExamAnswer(R.string.exam_answer_3c),
            new ExamAnswer(R.string.exam_answer_4c),
            new ExamAnswer(R.string.exam_answer_5c),
            new ExamAnswer(R.string.exam_answer_6c),
            new ExamAnswer(R.string.exam_answer_7c),
            new ExamAnswer(R.string.exam_answer_8c),
            new ExamAnswer(R.string.exam_answer_9c),
            new ExamAnswer(R.string.exam_answer_10c),
            new ExamAnswer(R.string.exam_answer_11c),
            new ExamAnswer(R.string.exam_answer_12c),
            new ExamAnswer(R.string.exam_answer_13c),
            new ExamAnswer(R.string.exam_answer_14c),
            new ExamAnswer(R.string.exam_answer_15c),
            new ExamAnswer(R.string.exam_answer_16c),
            new ExamAnswer(R.string.exam_answer_17c),
            new ExamAnswer(R.string.exam_answer_18c),
            new ExamAnswer(R.string.exam_answer_19c),
            new ExamAnswer(R.string.exam_answer_20c),
            new ExamAnswer(R.string.exam_answer_21c),
            new ExamAnswer(R.string.exam_answer_22c),
            new ExamAnswer(R.string.exam_answer_23c),
            new ExamAnswer(R.string.exam_answer_24c),
            new ExamAnswer(R.string.exam_answer_25c),
    };

    private ExamAnswer[] mExamAnswersD = new ExamAnswer[]{
            new ExamAnswer(R.string.exam_answer_1d),
            new ExamAnswer(R.string.exam_answer_2d),
            new ExamAnswer(R.string.exam_answer_3d),
            new ExamAnswer(R.string.exam_answer_4d),
            new ExamAnswer(R.string.exam_answer_5d),
            new ExamAnswer(R.string.exam_answer_6d),
            new ExamAnswer(R.string.exam_answer_7d),
            new ExamAnswer(R.string.exam_answer_8d),
            new ExamAnswer(R.string.exam_answer_9d),
            new ExamAnswer(R.string.exam_answer_10d),
            new ExamAnswer(R.string.exam_answer_11d),
            new ExamAnswer(R.string.exam_answer_12d),
            new ExamAnswer(R.string.exam_answer_13d),
            new ExamAnswer(R.string.exam_answer_14d),
            new ExamAnswer(R.string.exam_answer_15d),
            new ExamAnswer(R.string.exam_answer_16d),
            new ExamAnswer(R.string.exam_answer_17d),
            new ExamAnswer(R.string.exam_answer_18d),
            new ExamAnswer(R.string.exam_answer_19d),
            new ExamAnswer(R.string.exam_answer_20d),
            new ExamAnswer(R.string.exam_answer_21d),
            new ExamAnswer(R.string.exam_answer_22d),
            new ExamAnswer(R.string.exam_answer_23d),
            new ExamAnswer(R.string.exam_answer_24d),
            new ExamAnswer(R.string.exam_answer_25d),
    };



    //These methods below just change the question texts for each list.
    //Question Number, Question and 4 answers!
    private void updateQuestionNumber() {
        int questionNum = mExamQuestionNumber[mCurrentQuestion].getTextResId();
        mExamQuestionNumberTextView.setText(questionNum);
    }

    private void updateExamQuestion() {
        int exam_question = mExamQuestions[mCurrentQuestion].getTextResId();
        mExamQuestionTextView.setText(exam_question);
    }

    private void updateAnswerA() {
        int exam_ansA = mExamAnswersA[mCurrentQuestion].getTextResId();
        mExamAnswerATextView.setText(exam_ansA);
    }

    private void updateAnswerB() {
        int ansB = mExamAnswersB[mCurrentQuestion].getTextResId();
        mExamAnswerBTextView.setText(ansB);
    }

    private void updateAnswerC() {
        int ansC = mExamAnswersC[mCurrentQuestion].getTextResId();
        mExamAnswerCTextView.setText(ansC);
    }

    private void updateAnswerD() {
        int ansD = mExamAnswersD[mCurrentQuestion].getTextResId();
        mExamAnswerDTextView.setText(ansD);
    }

    private void checkAnswer(char ans) {
        //is the ans Char passed, the same as whats in the Question?
        if(ans == mExamQuestions[mCurrentQuestion].getCorrectAnswer()) {
            //increment score!
            mScore = mScore + 1;
            //My own debug.
            Log.d(TAG, Integer.toString(mScore));
        }
    }

    private void checkEnd(){
        //if we are at the end of the exam
        if(mCurrentQuestion == mExamQuestionNumber.length - 1) {

            //Build up a string of all our data.
            String finalScore = "Username : " + mUsername + " Password: " + mPassword + " Score: " + String.valueOf(mScore);
            Log.d(TAG, finalScore);

            //put score into Score Object!
            Score thisScore = new Score();
            thisScore.setScore(mScore);
            thisScore.setUserName(mUsername);
            thisScore.setPassword(mPassword);

            //This is used for the Database!

            mDBContext = this.getApplicationContext();
            ScoreList mScoreList = new ScoreList(mDBContext);
            //Add score to domain layer!
            mScoreList.add(thisScore);


            //Save data!
            SharedPreferences sharedPref = getSharedPreferences(mFileName, MODE_PRIVATE);
            //set up editor
            SharedPreferences.Editor editor = sharedPref.edit();
            //set up data to save (reset score, save usernames.)
            String mResetScore = mUsername + "," + mPassword + "," +  mCurrentQuestion + "," + mScore ;
            Log.d(TAG, "SAVED SCORE");
            //Save string to file.
            editor.putString(mFileName, mResetScore);
            editor.commit();

            //UURL to store data on My Database (Friend is hosting it for me on his Server on port :1337)
            String mUrl = "http://jrbradley.co.uk:1337/leaderboard/post/" + mUsername + "/" + mPassword + "/" + "1" + "/" + mScore;

            //I need to use this InternetThread object, because calling HTTP
            //In the main thread is apparently illegal.

            //This feature isnt working. I can't work out why right now.
            //In theory, it should work!
            InternetThread thread = new InternetThread();
            thread.doInBackground(mUrl);

            /*
            sharedPref = getSharedPreferences(mFileName, MODE_PRIVATE);
            String mSavedData = sharedPref.getString(mFileName, null);
            if(mSavedData != null) {

                String mBreak = "[,]";
                String[] mDataList = mSavedData.split(mBreak);
                mUsername = mDataList[0];
                mPassword = mDataList[1];
                mCurrentQuestion = Integer.parseInt(mDataList[2]);
                mScore = Integer.parseInt(mDataList[3]);
            }
            */


            //Call a new Activity! Switch to Result Page!
            Intent i = new Intent(ExamActivity.this, ResultActivity.class);
            startActivity(i);
        } else {
            //if we are not at the end of the exam, increment question.
            mCurrentQuestion = (mCurrentQuestion + 1);
        }

    }

    //This is my OnCreate function. It takes in the Bundle from
    //The last save instance (essetially it runs each time I load this activitiy.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Sets up our Text views.
        mExamQuestionNumberTextView = (TextView) findViewById(R.id.question_number);
        mExamQuestionTextView = (TextView) findViewById(R.id.exam_question);
        mExamAnswerATextView = (TextView) findViewById(R.id.exam_answer_a);
        mExamAnswerBTextView = (TextView) findViewById(R.id.exam_answer_b);
        mExamAnswerCTextView = (TextView) findViewById(R.id.exam_answer_c);
        mExamAnswerDTextView = (TextView) findViewById(R.id.exam_answer_d);

        /*
        This is the code which I used to set up the activity if it was destroyed wrongly in the last session
        Ideally, if the session was cancelled (onDestroy called), without the exam finishing, it should flag and
        re-create the previous exam stat using this data.

        I couldn't get the flags to work properly, and this is also not the ideal way to deal with data
        in android. I have included the codeblock to show you the idea, and hopefully you will work
        out what I was trying to do and consider how close I was.
         */

        /*
        SharedPreferences sharedPrefAgain = getSharedPreferences(mFileName, MODE_PRIVATE);
        String mSavedDataAgain = sharedPrefAgain.getString(mFileName, null);

        if(mSavedDataAgain != null) {
            String mBreak = "[,]";
            String[] mDataList = mSavedDataAgain.split(mBreak);
            mScore = Integer.parseInt(mDataList[3]);
            mCurrentQuestion = Integer.parseInt(mDataList[2]);
            Log.d(TAG, String.valueOf(mScore));
        }
        */

        //Sets up our Button Text
        mAButton = (Button) findViewById(R.id.exam_button_a);
        mAButton.setOnClickListener(new View.OnClickListener() {

            //This is an anonymous class, since why waste head space?
            @Override
            public void onClick(View v) {
                //check if this is the right answer
                checkAnswer('A');

                checkEnd();
                // Show toast then update screen
                Toast.makeText(ExamActivity.this, R.string.a_toast, Toast.LENGTH_SHORT).show();
                updateExamQuestion();
                updateQuestionNumber();
                updateAnswerA();
                updateAnswerB();
                updateAnswerC();
                updateAnswerD();
            }
        });

        //Same as A!
        mBButton = (Button) findViewById(R.id.exam_button_b);
        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer('B');
                checkEnd();
                Toast.makeText(ExamActivity.this, R.string.b_toast, Toast.LENGTH_SHORT).show();
                updateExamQuestion();
                updateQuestionNumber();
                updateAnswerA();
                updateAnswerB();
                updateAnswerC();
                updateAnswerD();
            }
        });

        //Same as A & B!
        mCButton = (Button) findViewById(R.id.exam_button_c);
        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer('C');
                checkEnd();
                Toast.makeText(ExamActivity.this, R.string.c_toast, Toast.LENGTH_SHORT).show();
                updateExamQuestion();
                updateQuestionNumber();
                updateAnswerA();
                updateAnswerB();
                updateAnswerC();
                updateAnswerD();

            }
        });

        //Same as A, B & D!
        mDButton = (Button) findViewById(R.id.exam_button_d);
        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer('D');
                checkEnd();
                Toast.makeText(ExamActivity.this, R.string.d_toast, Toast.LENGTH_SHORT).show();
                updateExamQuestion();
                updateQuestionNumber();
                updateQuestionNumber();
                updateAnswerA();
                updateAnswerB();
                updateAnswerC();
                updateAnswerD();


            }
        });


        //PERSISTANT DATA, 25% of the Marks Shouldnt be laughed at!
        //I admit 3 hours before submission, I couldnt get all the persistant data stuff working, but this does!
        //I have a database too!

        //open SharePref, and set it up
        SharedPreferences sharedPrefAgain = getSharedPreferences(mFileName, MODE_PRIVATE);
        //Set up a string, from the current sharedPref!
        String mSavedDataAgain = sharedPrefAgain.getString(mFileName, null);

        Log.d(TAG, "READING STUFF FROM FILES");
        //If it exists
        if(mSavedDataAgain != null) {


            Log.d(TAG, "SAVED DATA : " + mSavedDataAgain);
            //Break the CSV into String array
            String mBreak = "[,]";
            String[] mDataList = mSavedDataAgain.split(mBreak);

            mUsername = mDataList[0];
            mPassword = mDataList[1];
            mCurrentQuestion = Integer.parseInt(mDataList[2]);

            String testa = String.valueOf("THE SCORE AT THE START OF THE EXAM IS: " + mScore);
            Log.d(TAG, testa);

            mScore = Integer.parseInt(mDataList[3]);

        }



        if(savedInstanceState != null){
            mCurrentQuestion = savedInstanceState.getInt(KEYSTATE, 0);
            mScore = savedInstanceState.getInt(KEYSCORE, 0);
        }

        updateExamQuestion();
        updateQuestionNumber();
        updateAnswerA();
        updateAnswerB();
        updateAnswerC();
        updateAnswerD();


        //NEED TO REMOVE
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "The invigilator has been signaled.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override

    //Protects Values during Rotation etc.
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEYSTATE, mCurrentQuestion);
        savedInstanceState.putInt(KEYSCORE, mScore);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exam, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        //IS THIS THE ISSUE?
        /*
        SharedPreferences sharedPref = getSharedPreferences(mFileName, MODE_PRIVATE);
        String mCurrentQuestionString = String.valueOf(mCurrentQuestion);
        String mScoreString = String.valueOf(mScore);
        String mSaveData = mUsername + "," + mPassword + "," + mCurrentQuestionString + "," + mScoreString;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(mFileName, mSaveData);
        Log.d(TAG, "ON DESTROY: " + mSaveData);
        editor.commit();
        */

    }

}
