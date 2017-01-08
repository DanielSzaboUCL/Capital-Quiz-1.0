package com.danielszabo.android.capitalquiz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;


public class Menu extends AppCompatActivity {


    Button btnPlay,btnScore, btnAbout, btnMulti;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide(); //hide options bar


        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnScore = (Button)findViewById(R.id.btnScore);
        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnMulti = (Button)findViewById(R.id.btnMulti);


        //database
        db = new DataBase(this);
        try{
           db.createDataBase();     }
        catch (IOException e){
            e.printStackTrace();
        }



        //BUTTONS
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
                finish();
            }
        });


       btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScoreBoard.class);
                startActivity(intent);
                finish();
            }
        });


        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),About.class);
                startActivity(intent);
                finish();
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registration_Multi.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
