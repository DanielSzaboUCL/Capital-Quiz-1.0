package com.danielszabo.android.capitalquiz;

        //storing questions and answers
public class Question {

    private int mTextResId;
    private String mAnswerA;

    public Question(int textResId, String answerA) {
        mTextResId = textResId;
        mAnswerA = answerA;
    }

    public int getTextResId() {
        return mTextResId;
    }


    public String isAnswerA() {
        return mAnswerA;
    }

}
