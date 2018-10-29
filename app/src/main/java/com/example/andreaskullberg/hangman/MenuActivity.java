package com.example.andreaskullberg.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private TextView textView;
    private Button play;
    private Button about;
    //TypedArray pictures = res.obtainTypedArray varje item blir ett index i vanlig ordning
    // include för att hämta actionbar till xmlactivity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        play = findViewById(R.id.playGame);
        about = findViewById(R.id.about);
        ///Log skapar utskrifter

    }


    public void onBackPressed() {
        //super.onBackPressed(); //Så att programmet inte kan "destroy" när användaren trycker bakåtknappen

    }

    public void onClick(View view){

            switch (view.getId()) {
                case R.id.playGame:
                    Intent play = new Intent(this, PlayGameActivity.class);
                    startActivity(play);
                    break;
                case R.id.about:
                    Intent about = new Intent(this, aboutActivity.class);
                    startActivity(about);
                    break;
            }
    }
}
