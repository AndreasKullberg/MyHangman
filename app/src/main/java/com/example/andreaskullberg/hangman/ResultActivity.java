package com.example.andreaskullberg.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView winOrLose;
    private TextView showWord;
    private TextView guessLeftNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        winOrLose = findViewById(R.id.winOrLose);
        showWord = findViewById(R.id.showWord);
        guessLeftNr = findViewById(R.id.guessLeftNr);
        Intent intent = getIntent();
        String word = intent.getStringExtra("CORRECTWORD");
        int guessLeft = intent.getIntExtra("GUESSLEFT", 0);
        if(guessLeft == 0){
            winOrLose.setText(getString(R.string.duFörlorade));
        }
        showWord.setText(word);
        String stringGuess = Integer.toString(guessLeft);
        guessLeftNr.setText(stringGuess);
    }

    public void backToMenuOnClick(View view){
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
    }
}
