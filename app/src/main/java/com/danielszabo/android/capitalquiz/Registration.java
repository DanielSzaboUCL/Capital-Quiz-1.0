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

import java.io.IOException;


public class Registration extends AppCompatActivity {


    Button btnPlay;
    EditText mEditText;
    String user_name_entered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide(); //hide options bar


        btnPlay = (Button)findViewById(R.id.play_button);
        mEditText = (EditText)findViewById(R.id.editText);

        //open keyboard automatically
        mEditText.setFocusableInTouchMode(true);
        mEditText.setFocusable(true);
        mEditText.requestFocus();
        mEditText.setSelection(mEditText.length());
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);


        //Upper case start of words (on phones that enable this)
        EditText editor = new EditText(this);
        editor.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);



        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    user_name_entered = mEditText.getText().toString();
                }
                catch (Exception e) {

                    int messageResId = R.string.name_error;
                    Toast.makeText(getApplicationContext(), messageResId, Toast.LENGTH_SHORT).show();

                }

                //close keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
                mEditText.setInputType(InputType.TYPE_NULL);

                //sore name in Done
                Done.setName(user_name_entered);

                //start QuizActivity
                Intent intent2 = new Intent(getApplicationContext(),QuizActivity.class);
                startActivity(intent2);
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
