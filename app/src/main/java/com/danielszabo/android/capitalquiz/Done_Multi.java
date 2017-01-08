package com.danielszabo.android.capitalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Done_Multi extends AppCompatActivity {

    Button btnTryAgain;
    TextView name1, name2, score1, score2, winner;


        //private static variable to store number of players who completed quiz
        private static int PlayersCompletedQuiz;
        public static void setPlayersCompletedQuiz(int num) {
            PlayersCompletedQuiz = num;
        }

        public static int getPlayersCompletedQuiz() {
            return PlayersCompletedQuiz;
        }

        public static void addPlayersCompletedQuiz( ) {
            PlayersCompletedQuiz++;
        }


        //private static variables to store names and scores
        private static int score_pl1, score_pl2;
        private static String UserName1, UserName2;

        public static String getName1() {
            return UserName1;
        }
        public static void setName1(String name) {
            UserName1 = name;
        }

        public static void setScore1 (int score) {
            score_pl1 = score;
        }
        public static int getScore1 () {
            return score_pl1;
        }



        public static String getName2() {
            return UserName2;
        }

        public static void setName2(String name) {
            UserName2 = name;
        }

        public static void setScore2 (int score) {
            score_pl2 = score;
        }

        public static int getScore2 () {
            return score_pl2;
        }


    public void insertDatabase(){

        //store user's name and score in database
        DataBase db = new DataBase(this);
        db.insertNameScore(getScore1(), getName1());
        db.insertNameScore(getScore2(), getName2());

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_multi);
        getSupportActionBar().hide();  //hide options bar on top

        //restart quiz for second player
        if (PlayersCompletedQuiz < 2) {
            Intent intent = new Intent(getApplicationContext(), QuizActivity_Multi.class);
            startActivity(intent);
            finish();
        }


        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        winner = (TextView) findViewById(R.id.winner);

        //displaying names and scores
        name1.setText(getName1());
        name2.setText(getName2());
        score1.setText(getScore1() ==1? getScore1()+ " point" : getScore1() + " points");
        score2.setText(getScore2() ==1? getScore2()+ " point" : getScore2() + " points");

        //write out winner's name
        if (score_pl1 < score_pl2) {
            winner.setText("The winner is " + getName2()+ "!");
        }
        else if (score_pl2 < score_pl1) {
            winner.setText("The winner is " + getName1() + "!");
        }
        else if  (score_pl2 == score_pl1) {
            winner.setText("It is a draw!");
        }



        //Try again button leads to Menu
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

    @Override //back button leads to Menu
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);
        insertDatabase();
        finish();
    }
}
