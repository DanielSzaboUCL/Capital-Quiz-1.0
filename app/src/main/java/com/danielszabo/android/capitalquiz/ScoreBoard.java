package com.danielszabo.android.capitalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class ScoreBoard extends AppCompatActivity {
    ListView lstView;

    Button BackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getSupportActionBar().hide();


        //list view
        lstView = (ListView)findViewById(R.id.lstRanking);
        DataBase db = new DataBase(this);
        List<Ranking> lstRanking = db.getRanking();
        if(lstRanking.size() > 0)
        {
            ScoreBoardView adapter = new ScoreBoardView(this,lstRanking);
            lstView.setAdapter(adapter);
        }

        //back button
        BackButton = (Button) findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
                finish();
            }
    });
    }

    @Override  //back button leads to Menu
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);
        finish();
    }
}
