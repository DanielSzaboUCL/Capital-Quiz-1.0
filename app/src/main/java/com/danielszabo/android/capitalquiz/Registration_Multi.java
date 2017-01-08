package com.danielszabo.android.capitalquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registration_Multi extends AppCompatActivity {


    Button btnPlay;
    EditText mEditText1, mEditText2;
    String userName1, userName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_multi);
        getSupportActionBar().hide();  //hide options bar


        btnPlay = (Button)findViewById(R.id.play_button);
        mEditText1 = (EditText)findViewById(R.id.editText1);

        //open keyboard
        mEditText1.setFocusableInTouchMode(true);
        mEditText1.setFocusable(true);
        mEditText1.requestFocus();
        mEditText1.setSelection(mEditText1.length());



        mEditText2 = (EditText)findViewById(R.id.editText2);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);


        EditText editor = new EditText(this);
        editor.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);




        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //read inputs
                try {
                    userName1 = mEditText1.getText().toString();
                }
                catch (Exception e) {
                    int messageResId = R.string.name_error;
                    Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT).show();
                }

                try {
                    userName2 = mEditText2.getText().toString();
                }
                catch (Exception e) {
                    int messageResId = R.string.name_error;
                    Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT).show();
                }

                //close keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
                mEditText1.setInputType(InputType.TYPE_NULL);
                mEditText2.setInputType(InputType.TYPE_NULL);

                //store user names in Done_Multi
                Done_Multi.setName1(userName1);
                Done_Multi.setName2(userName2);

                //set number of players who completed quiz to zer ao
                Done_Multi.setPlayersCompletedQuiz(0);

                //start quiz
                Intent intent2 = new Intent(getApplicationContext(),QuizActivity_Multi.class);
                startActivity(intent2);
                finish();
            }
        });


    }

    @Override  //back leads to menu
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Menu.class);
        startActivity(intent);
        finish();
    }
}
