package uk.co.danbeglin.phasetestapp;

/**
 * Created by danny on 26/04/16.
 */
public class ExamAnswer {
    private int mTextResId;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public ExamAnswer(int textResId){
        mTextResId = textResId;
    }
}
