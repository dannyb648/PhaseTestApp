package uk.co.danbeglin.phasetestapp;

/**
 * Created by danny on 26/04/16.
 */

public class ExamQuestion {
    //This class is for all exam questions on any exam.

    private int mTextResId;
    //Holds resource ID, which is an Int
    private char mCorrectAnswer;
    //Holds correct answer, which is a Char ("A" etc)


    //Get / Sets for Member Attributes, AutoGen'd
    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public char getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public void setCorrectAnswer(char correctAnswer) {
        mCorrectAnswer = correctAnswer;
    }

    public ExamQuestion(int textResId, char correctAnswer) {
        //Constructor
        mTextResId = textResId;
        mCorrectAnswer = correctAnswer;
    }
}
