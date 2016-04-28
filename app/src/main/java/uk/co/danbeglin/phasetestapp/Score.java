package uk.co.danbeglin.phasetestapp;

/**
 * Created by danny on 28/04/16.
 */
public class Score {

    private static int count = 0;
    private int mId;
    private int mScore;
    private String mUserName;
    private String mPassword;

    public static void updateCount() {
        count = count + 1;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public void Score(int score, String user, String pass) {
        mScore = score;
        mUserName = user;
        mPassword = pass;
        mId = count;
        updateCount();
    }
}

