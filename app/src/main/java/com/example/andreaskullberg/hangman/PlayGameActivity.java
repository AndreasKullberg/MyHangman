package com.example.andreaskullberg.hangman;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.xml.transform.Result;

public class PlayGameActivity extends AppCompatActivity {
    private TextView hiddenWord;
    private TextView guessLeftNr;
    private TextView wrongLetters;
    private EditText guessLetter;
    private ImageView pictures;
    private Hangman hangman = new Hangman();
    private String picture = "https://raw.githubusercontent.com/AndreasKullberg/HangmanPic/master/Hangmanpic/hang";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        guessLeftNr = findViewById(R.id.guessLeftNr);
        hiddenWord = findViewById(R.id.word);
        wrongLetters = findViewById(R.id.wrongGuess);
        guessLetter = findViewById(R.id.guessLetter);
        pictures = findViewById(R.id.imageView2);

        String words[] = getResources().getStringArray(R.array.words);
        hangman.newWord(words);
        hangman.initiateCharWord();
        String guessLeft = Integer.toString(hangman.getGuessLeft());
        guessLeftNr.setText(guessLeft);
        hiddenWord.setText(hangman.getHiddenWord());
        //setHasOptionMenu(true) ta med meny i fragments
        //recreate();

        Glide
                .with(this)
                .load(picture + hangman.getGuessLeft() + ".gif")
                .apply(new RequestOptions().override(210, 210).fitCenter())
                .into(pictures);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void guessOnClick(View view){
        String tempGuess = guessLetter.getText().toString();
        char guess = '-';
        if (!tempGuess.equals("")) {
            guess = tempGuess.charAt(0);
        }

        if(tempGuess.equals("")  || !Character.isAlphabetic(guess)) {
            Toast toast = Toast.makeText(this,getString(R.string.skrivBokstav), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,600);
            toast.show();
        }
        else if(hangman.hasUsedLetter(guess)){
            Toast toast = Toast.makeText(this,getString(R.string.bokstav_Använd), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,600);
            toast.show();
        }
        else {
            hangman.guess(guess);
            hiddenWord.setText(hangman.getHiddenWord());
            wrongLetters.setText(hangman.getWrongLettersUsed());
            String guessLeft = Integer.toString(hangman.getGuessLeft());
            guessLeftNr.setText(guessLeft);
        }
        Glide
                .with(this)
                .load(picture + hangman.getGuessLeft() + ".gif")
                .apply(new RequestOptions().override(210, 210).fitCenter())
                .into(pictures);
        if(hangman.hasLost() || hangman.hasWon()){
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("GUESSLEFT", hangman.getGuessLeft());
            intent.putExtra("CORRECTWORD", hangman.getWord());
            startActivity(intent);
        }
    }
}
