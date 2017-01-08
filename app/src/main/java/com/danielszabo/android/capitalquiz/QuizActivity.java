package com.danielszabo.android.capitalquiz;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import android.os.Handler;


public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;

    private Button mNextButton;
    private Button mPreviousButton;
    private Button mCheatButton;
    private Button mFinishButton;
    private Button mHintButton;

    private Button q1Button;
    private Button q2Button;
    private Button q3Button;
    private Button q4Button;
    private Button q5Button;
    private Button q6Button;
    private Button q7Button;
    private Button q8Button;
    private Button q9Button;

    private TextView mQuestionTextView;
    private boolean mIsCheater;
    private boolean usedHint;

    private  SecureRandom ran5;
    private int rand5;
    private int mCurrentIndex;
    private int question_number;
    private static int score_running = 0;


    private TextView question_number_text; // shows current question number
    private TextView current_score_text;
    private  TextView answered_boolean;


    //array to keep track of answered questions
    private static boolean[] mAnsQues = new boolean[9];  {
        mAnsQues[0]=false;
        mAnsQues[1]=false;
        mAnsQues[2]=false;
        mAnsQues[3]=false;
        mAnsQues[4]=false;
        mAnsQues[5]=false;
        mAnsQues[6]=false;
        mAnsQues[7]=false;
        mAnsQues[8]=false;
    }


    //array to store questions and answers
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.q1,"Tirana"),
            new Question(R.string.q2,"Andorra la Vella"),
            new Question(R.string.q3,"Yerevan"),
            new Question(R.string.q4,"Vienna"),
            new Question(R.string.q5,"Baku"),
            new Question(R.string.q6,"Minsk"),
            new Question(R.string.q7,"Brussels"),
            new Question(R.string.q8,"Sarajevo"),
            new Question(R.string.q9,"Sofia"),
            new Question(R.string.q10,"Zagreb"),
            new Question(R.string.q11,"Nicosia"),
            new Question(R.string.q12,"Prague"),
            new Question(R.string.q13,"Copenhagen"),
            new Question(R.string.q14,"Tallinn"),
            new Question(R.string.q15,"Helsinki"),
            new Question(R.string.q16,"Paris"),
            new Question(R.string.q17,"Tbilisi"),
            new Question(R.string.q18,"Berlin"),
            new Question(R.string.q19,"Athens"),
            new Question(R.string.q20,"Budapest"),
            new Question(R.string.q21,"Reykjavik"),
            new Question(R.string.q22,"Dublin"),
            new Question(R.string.q23,"Rome"),
            new Question(R.string.q24,"Astana"),
            new Question(R.string.q25,"Pristina"),
            new Question(R.string.q26,"Riga"),
            new Question(R.string.q27,"Vaduz"),
            new Question(R.string.q28,"Vilnius"),
            new Question(R.string.q29,"Luxembourg"),
            new Question(R.string.q30,"Skopje"),
            new Question(R.string.q31,"Valletta"),
            new Question(R.string.q32,"Chisinau"),
            new Question(R.string.q33,"Monaco"),
            new Question(R.string.q34,"Podgorica"),
            new Question(R.string.q35,"Amsterdam"),
            new Question(R.string.q36,"Oslo"),
            new Question(R.string.q37,"Warsaw"),
            new Question(R.string.q38,"Lisbon"),
            new Question(R.string.q39,"Bucharest"),
            new Question(R.string.q40,"Moscow"),
            new Question(R.string.q41,"San Marino"),
            new Question(R.string.q42,"Belgrade"),
            new Question(R.string.q43,"Bratislava"),
            new Question(R.string.q44,"Ljubljana"),
            new Question(R.string.q45,"Madrid"),
            new Question(R.string.q46,"Stockholm"),
            new Question(R.string.q47,"Bern"),
            new Question(R.string.q48,"Ankara"),
            new Question(R.string.q49,"Kyiv"),
            new Question(R.string.q50,"London"),
    };

    //array to store possible answer choices
    private String[] mAnswerBank = new String[] {
            new String("Tirana"),
            new String("Andorra la Vella"),
            new String("Yerevan"),
            new String("Vienna"),
            new String("Baku"),
            new String("Minsk"),
            new String("Brussels"),
            new String("Sarajevo"),
            new String("Sofia"),
            new String("Zagreb"),
            new String("Nicosia"),
            new String("Prague"),
            new String("Copenhagen"),
            new String("Tallinn"),
            new String("Helsinki"),
            new String("Paris"),
            new String("Tbilisi"),
            new String("Berlin"),
            new String("Athens"),
            new String("Budapest"),
            new String("Reykjavik"),
            new String("Dublin"),
            new String("Rome"),
            new String("Astana"),
            new String("Pristina"),
            new String("Riga"),
            new String("Vaduz"),
            new String("Vilnius"),
            new String("Luxembourg"),
            new String("Skopje"),
            new String("Valletta"),
            new String("Chisinau"),
            new String("Monaco"),
            new String("Podgorica"),
            new String("Amsterdam"),
            new String("Oslo"),
            new String("Warsaw"),
            new String("Lisbon"),
            new String("Bucharest"),
            new String("Moscow"),
            new String("San Marino"),
            new String("Belgrade"),
            new String("Bratislava"),
            new String("Ljubljana"),
            new String("Madrid"),
            new String("Stockholm"),
            new String("Bern"),
            new String("Ankara"),
            new String("Kyiv"),
            new String("London"),

    };

        //method to get current score
        public static int getRunning_Score ()
        {
            return score_running;
        }


        //method for setting question number text of current question orange, others buttons' white
        private void CurrentQuestionColoured(){
            q1Button.setTextColor(getResources().getColor(R.color.black));
            q2Button.setTextColor(getResources().getColor(R.color.black));
            q3Button.setTextColor(getResources().getColor(R.color.black));
            q4Button.setTextColor(getResources().getColor(R.color.black));
            q5Button.setTextColor(getResources().getColor(R.color.black));
            q6Button.setTextColor(getResources().getColor(R.color.black));
            q7Button.setTextColor(getResources().getColor(R.color.black));
            q8Button.setTextColor(getResources().getColor(R.color.black));
            q9Button.setTextColor(getResources().getColor(R.color.black));


        switch (mCurrentIndex) {
            case 0: q1Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case 1: q2Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case 2: q3Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case 3: q4Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case 4: q5Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case 5: q6Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case 6: q7Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case 7: q8Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case 8: q9Button.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
    }}


        //method to mark questions that have already been answered
    private void buttonColours() {

        if (mAnsQues[0]) q1Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));
        if (mAnsQues[1]) q2Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));
        if (mAnsQues[2]) q3Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));
        if (mAnsQues[3]) q4Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));
        if (mAnsQues[4]) q5Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));
        if (mAnsQues[5]) q6Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));
        if (mAnsQues[6]) q7Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));
        if (mAnsQues[7]) q8Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));
        if (mAnsQues[8]) q9Button.setBackgroundColor(getResources().getColor(R.color.answeredQuestion));

    }

        //method to load new question
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question); //load new question text

        question_number_text.setText(
                getString(R.string.question_NUM,question_number));  //refresh question number text

        //get correct answer
        String Correct_Answer = mQuestionBank[mCurrentIndex].isAnswerA();
        String Correct_String = "" + Correct_Answer;


        ran5 = new SecureRandom();
        rand5 = ran5.nextInt(4);

        //refresh question number coloring
        CurrentQuestionColoured();

        //set hint variable to initial value
        usedHint = false;

        //randomly set possible answer options. Possible answer options are selected by formula below
        mAButton.setText(mAnswerBank[question_number*4+1]);
        mBButton.setText(mAnswerBank[question_number*4+2]);
        mCButton.setText(mAnswerBank[question_number*4+3]);
        mDButton.setText(mAnswerBank[question_number*4+4]);

        //if non of the random options is the correct one, replace one of the them with the correct one randomly
        if (!mAnswerBank[question_number*4+1].equals(Correct_String) && !mAnswerBank[question_number*4+2].equals(Correct_String) &&
        !mAnswerBank[question_number*4+3].equals(Correct_String) && !mAnswerBank[question_number*4+4].equals(Correct_String)) {

            switch (rand5) {
                case 1:
                    mAButton.setText(Correct_String);
                    break;
                case 2:
                    mBButton.setText(Correct_String);
                    break;
                case 3:
                    mCButton.setText(Correct_String);
                    break;
                case 0:
                    mDButton.setText(Correct_String);
                    break;
            }

            //disable options for already answered questions
            if (mAnsQues[mCurrentIndex]) {

                mAButton.setEnabled(false);
                mBButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);

                answered_boolean.setText(
                        getString(R.string.answered_boolean));
            }
            else if (!mAnsQues[mCurrentIndex]){
                mAButton.setEnabled(true);
                mBButton.setEnabled(true);
                mCButton.setEnabled(true);
                mDButton.setEnabled(true);

                answered_boolean.setText("");
            }
        }

        refreshAnswerButtons();

    }

    /*  sometimes when the user answered questions too quickly while the toast was still visible,
    the answer options remained disabled. This method reruns the button activation to solve this bug.
    The length of the short toast is 2 seconds.*/
    private void refreshAnswerButtons(){

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (mAnsQues[mCurrentIndex]) {

                        mAButton.setEnabled(false);
                        mBButton.setEnabled(false);
                        mCButton.setEnabled(false);
                        mDButton.setEnabled(false);

                    }
                    else {
                        mAButton.setEnabled(true);
                        mBButton.setEnabled(true);
                        mCButton.setEnabled(true);
                        mDButton.setEnabled(true);

                        answered_boolean.setText("");
                    }

                }
            }, (2000));
    }


    /*  sometimes when the user answered and clicked on Hint too quickly while the above button disabling-enabling was still running,
    the Hint did not work correctly. This method fixes this issue */
    private void hintRepeater(){

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    String Correct_Answer = mQuestionBank[mCurrentIndex].isAnswerA();
                    String Correct_String = "" + Correct_Answer;


                    String OptionA = "" + mAButton.getText();
                    String OptionB = "" + mBButton.getText();
                    String OptionC = "" + mCButton.getText();
                    String OptionD = "" + mDButton.getText();

                    //disable 2 buttons depending on column in which correct answer is
                    if (OptionA.equals(Correct_String)) { mBButton.setEnabled(false); mDButton.setEnabled(false); }
                    if (OptionC.equals(Correct_String)) { mBButton.setEnabled(false); mDButton.setEnabled(false); }
                    if (OptionB.equals(Correct_String)) { mAButton.setEnabled(false); mCButton.setEnabled(false); }
                    if (OptionD.equals(Correct_String)) { mAButton.setEnabled(false); mCButton.setEnabled(false); }

                }
            }, (2000));


    }


            //method to check answer
            private void checkAnswer(String userPressedA) {
                String answerIsA = mQuestionBank[mCurrentIndex].isAnswerA();

                int messageResId;

                if (mIsCheater) {
                    messageResId = R.string.judgment_toast;
                } else

                if (userPressedA.equals(answerIsA)) {
                    messageResId = R.string.correct_toast;

                    //add score if correct
                    if (usedHint) score_running = score_running + 1;
                    if (!usedHint) score_running = score_running + 2;

                    current_score_text.setText(
                            getString(R.string.current_score,getRunning_Score()));
                } else {
                    messageResId = R.string.incorrect_toast;
                    score_running--;
                    current_score_text.setText(
                            getString(R.string.current_score,getRunning_Score()));
                }

                //display correct/incorrect message
                Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                    .show();

                //set question answered
                mAnsQues[mCurrentIndex] = true;

                //disable buttons
                mAButton.setEnabled(false);
                mBButton.setEnabled(false);
                mCButton.setEnabled(false);
                mDButton.setEnabled(false);

                //colouring buttons
                buttonColours();
                allQuestionsAnswered();


                //go to next question after .75sec
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      mNextButton.performClick();
                    }
                }, usedHint? 2000 : 1000);  //longer delay when user used Hint, so that question refreshing works well

            }

            //method to automatically moves to Done activity if all questions are answered
        private void allQuestionsAnswered(){

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
            if (mAnsQues[0] && mAnsQues[1] &&  mAnsQues[2] &&  mAnsQues[3] &&  mAnsQues[4] &&  mAnsQues[5] &&  mAnsQues[6] && mAnsQues[7] &&
            mAnsQues[8] )
                    mFinishButton.performClick();
                }
            }, usedHint? 2000 : 1000); //longer delay when user used Hint, so that question refreshing works well

        }


        //method for cheating
    private void Cheating (){
        String Correct_Answer = mQuestionBank[mCurrentIndex].isAnswerA();
        String Correct_String = "" + Correct_Answer;
        String ShowCorrectAnswer = "The correct answer is " + Correct_String;

        Toast.makeText(getApplicationContext(), ShowCorrectAnswer, Toast.LENGTH_SHORT).show();
        mIsCheater = true;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();  //hide menu bar at the top
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // disable landscape mode


        Collections.shuffle(Arrays.asList(mQuestionBank));  //shuffle questions
        Collections.shuffle(Arrays.asList(mAnswerBank));  //shuffle answer options

            //set all questions unanswered
            mAnsQues[0]=false;
            mAnsQues[1]=false;
            mAnsQues[2]=false;
            mAnsQues[3]=false;
            mAnsQues[4]=false;
            mAnsQues[5]=false;
            mAnsQues[6]=false;
            mAnsQues[7]=false;
            mAnsQues[8]=false;

        //set initial values for score and question index
        score_running = 0;
        mCurrentIndex = 0;
        question_number = 1;

        question_number_text =
                (TextView) findViewById(R.id.question_number_text);
        question_number_text.setText(
                getString(R.string.question_NUM,question_number));

        current_score_text =
                (TextView) findViewById(R.id.current_score_text);
        current_score_text.setText(
                getString(R.string.current_score,getRunning_Score()));


        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        answered_boolean = (TextView) findViewById(R.id.answered_boolean);



        //Option buttons
        mAButton = (Button) findViewById(R.id.A_button);
        mAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonText = b.getText().toString();
                checkAnswer(buttonText);
            }
        });

        mBButton = (Button) findViewById(R.id.B_button);
        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button b = (Button)v;
                String buttonText = b.getText().toString();
                checkAnswer(buttonText);
            }
        });

        mCButton = (Button) findViewById(R.id.C_button);
        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonText = b.getText().toString();
                checkAnswer(buttonText);
            }
        });

        mDButton = (Button) findViewById(R.id.D_button);
        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String buttonText = b.getText().toString();
                checkAnswer(buttonText);
            }
        });



        //question and navigation buttons
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mCurrentIndex == 8) {
                    mCurrentIndex = 0;
                    question_number = 1;
                } else {
                    question_number = mCurrentIndex + 1;
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;}

                question_number = mCurrentIndex + 1;

                updateQuestion();


            }
        });

            q1Button = (Button) findViewById(R.id.q1_button);
            q1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 0;
                    mIsCheater = false;
                    question_number = 1;
                    updateQuestion();
                }
            });

            q2Button = (Button) findViewById(R.id.q2_button);
            q2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 1;
                    mIsCheater = false;
                    question_number = 2;
                    updateQuestion();
                }
            });

            q3Button = (Button) findViewById(R.id.q3_button);
            q3Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 2;
                    mIsCheater = false;
                    question_number = 3;
                    updateQuestion();
                }
            });

            q4Button = (Button) findViewById(R.id.q4_button);
            q4Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 3;
                    mIsCheater = false;
                    question_number = 4;
                    updateQuestion();
                }
            });

            q5Button = (Button) findViewById(R.id.q5_button);
            q5Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 4;
                    mIsCheater = false;
                    question_number = 5;
                    updateQuestion();
                }
            });

            q6Button = (Button) findViewById(R.id.q6_button);
            q6Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 5;
                    mIsCheater = false;
                    question_number = 6;
                    updateQuestion();
                }
            });

            q7Button = (Button) findViewById(R.id.q7_button);
            q7Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 6;
                    mIsCheater = false;
                    question_number = 7;
                    updateQuestion();
                }
            });

            q8Button = (Button) findViewById(R.id.q8_button);
            q8Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 7;
                    mIsCheater = false;
                    question_number = 8;
                    updateQuestion();
                }
            });

            q9Button = (Button) findViewById(R.id.q9_button);
            q9Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCurrentIndex = 8;
                    mIsCheater = false;
                    question_number = 9;
                    updateQuestion();
                }
            });


        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mCurrentIndex == 0) {
                            mCurrentIndex = 8;
                            question_number = 9;
                        } else {

                        mCurrentIndex = (mCurrentIndex - 1);
                        mIsCheater = false;

                        question_number = mCurrentIndex + 1;}

                        updateQuestion();
            }
        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cheating();

            }
        });

        mHintButton = (Button)findViewById(R.id.hint_button);
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Correct_Answer = mQuestionBank[mCurrentIndex].isAnswerA();
                String Correct_String = "" + Correct_Answer;

                usedHint = true;

                String OptionA = "" + mAButton.getText();
                String OptionB = "" + mBButton.getText();
                String OptionC = "" + mCButton.getText();
                String OptionD = "" + mDButton.getText();

                //disable 2 buttons depending on column in which correct answer is
                if (OptionA.equals(Correct_String)) { mBButton.setEnabled(false); mDButton.setEnabled(false); }
                if (OptionC.equals(Correct_String)) { mBButton.setEnabled(false); mDButton.setEnabled(false); }
                if (OptionB.equals(Correct_String)) { mAButton.setEnabled(false); mCButton.setEnabled(false); }
                if (OptionD.equals(Correct_String)) { mAButton.setEnabled(false); mCButton.setEnabled(false); }

                hintRepeater(); //method to make sure Hint works properly even when user clicks on button quickly after refreshing question

            }
        });


        mFinishButton = (Button)findViewById(R.id.finish_button);
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(QuizActivity.this, Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE",score_running);
                dataSend.putInt("TOTAL",18);
                dataSend.putInt("CORRECT",score_running);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();

            }
        });



        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
        CurrentQuestionColoured();

    }


        @Override
        public void onSaveInstanceState(Bundle savedInstanceState) {
            super.onSaveInstanceState(savedInstanceState);
            Log.i(TAG, "onSaveInstanceState");
            savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override  //back button leads to previous question
    public void onBackPressed() {
        mPreviousButton.performClick();
    }



}
