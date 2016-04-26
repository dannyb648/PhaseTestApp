package uk.co.danbeglin.phasetestapp;

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

public class ExamActivity extends AppCompatActivity {
    //mainExamActivity Class, inherits from AppCompatActivity
    //AppCompatActivity makes this compatable for older Android versions

    private static final String TAG = "DEBUG";

    private static final String KEYSTATE = "index";
    private static final String KEYSCORE = "index2";

    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;

    private TextView mExamQuestionNumberTextView;
    private TextView mExamQuestionTextView;
    private TextView mExamAnswerATextView;
    private TextView mExamAnswerBTextView;
    private TextView mExamAnswerCTextView;
    private TextView mExamAnswerDTextView;

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
    };

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
    };

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
    };



    private int mCurrentQuestion = 0;

    private int mScore = 0;

    //Vars for my Buttons, "mXButton" is convention, and useful.

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
        if(ans == mExamQuestions[mCurrentQuestion].getCorrectAnswer()) {
            mScore = mScore + 1;
            Log.d(TAG, Integer.toString(mScore));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mExamQuestionNumberTextView = (TextView) findViewById(R.id.question_number);

        mExamQuestionTextView = (TextView) findViewById(R.id.exam_question);
        //int question = mExamQuestions[mCurrentQuestion].getTextResId();
        //mExamQuestionTextView.setText(question);

        mExamAnswerATextView = (TextView) findViewById(R.id.exam_answer_a);
        //int ansA = mExamAnswersA[mCurrentQuestion].getTextResId();
        //mExamAnswerATextView.setText(ansA);

        mExamAnswerBTextView = (TextView) findViewById(R.id.exam_answer_b);
        //int ansB = mExamAnswersB[mCurrentQuestion].getTextResId();
        //mExamAnswerBTextView.setText(ansB);

        mExamAnswerCTextView = (TextView) findViewById(R.id.exam_answer_c);
        //int ansC = mExamAnswersC[mCurrentQuestion].getTextResId();
        //mExamAnswerCTextView.setText(ansC);

        mExamAnswerDTextView = (TextView) findViewById(R.id.exam_answer_d);
        //int ansD = mExamAnswersD[mCurrentQuestion].getTextResId();
        //mExamAnswerDTextView.setText(ansD);




        mAButton = (Button) findViewById(R.id.exam_button_a);
        mAButton.setOnClickListener(new View.OnClickListener() {
            //This is an anonymous class, since why waste head space?
            @Override
            public void onClick(View v) {

                checkAnswer('A');
                //IF STATEMENT HERE: if mCurrentQuestion => 11, ENDTEST()
                mCurrentQuestion = (mCurrentQuestion + 1);
                Toast.makeText(ExamActivity.this, R.string.a_toast, Toast.LENGTH_SHORT).show();
                updateExamQuestion();
                updateQuestionNumber();
                updateAnswerA();
                updateAnswerB();
                updateAnswerC();
                updateAnswerD();
            }
        });

        mBButton = (Button) findViewById(R.id.exam_button_b);
        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer('B');
                mCurrentQuestion = (mCurrentQuestion + 1);
                Toast.makeText(ExamActivity.this, R.string.b_toast, Toast.LENGTH_SHORT).show();
                updateExamQuestion();
                updateQuestionNumber();
                updateAnswerA();
                updateAnswerB();
                updateAnswerC();
                updateAnswerD();
            }
        });

        mCButton = (Button) findViewById(R.id.exam_button_c);
        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer('C');
                mCurrentQuestion = (mCurrentQuestion + 1);
                Toast.makeText(ExamActivity.this, R.string.c_toast, Toast.LENGTH_SHORT).show();
                updateExamQuestion();
                updateQuestionNumber();
                updateAnswerA();
                updateAnswerB();
                updateAnswerC();
                updateAnswerD();

            }
        });

        mDButton = (Button) findViewById(R.id.exam_button_d);
        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer('D');
                mCurrentQuestion = (mCurrentQuestion + 1);
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
                Snackbar.make(view, "The invigilator has beem signaled.", Snackbar.LENGTH_LONG)
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
}
