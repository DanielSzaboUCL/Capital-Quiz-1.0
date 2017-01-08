package com.danielszabo.android.capitalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Done extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore, AtextView;
    String congratText;
    static int score;



    //store user's name, score as private static variable
    private static String UserName;

    public static String getName() {
        return UserName;
    }

    public static void setName(String name) {
        UserName = name;
    }


    public static int getScore() {
        return score;
    }

    public static void setScore(int number) {
        score = number;
    }


    public void insertDatabase(){

        //store user's name and score in database
        DataBase db = new DataBase(this);
        db.insertNameScore(getScore(), getName());

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        getSupportActionBar().hide();  //hide top settings bar


        AtextView = (TextView) findViewById(R.id.text);
        txtResultScore = (TextView) findViewById(R.id.txtTotalScore);


        //get extra from QuizActivity
        Bundle extra = getIntent().getExtras();
        int score = extra.getInt("SCORE");

        setScore(score);


        //user feedback based on score
        if (score >= 15)  congratText = "Wow, that is impressive ";
        if ( 15 > score && score >= 10 )  congratText = "Good job ";
        if ( 10 > score && score >= 5 )  congratText = "Not bad ";
        if ( 5 > score )  congratText = "Better luck next time ";

        txtResultScore.setText(String.format("Your score is : %d", score));
        AtextView.setText(congratText + getName() + "!");



        //back button to Menu
        btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
                insertDatabase();
                finish();
            }
        });
    }

    @Override //back button leads back to Menu
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);
        insertDatabase();
        finish();
    }
}
