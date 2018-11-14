package com.example.andreaskullberg.hangman;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.xml.transform.Result;

public class PlayGameActivity extends Fragment {
    private TextView hiddenWord;
    private TextView guessLeftNr;
    private TextView wrongLetters;
    private EditText guessLetter;
    private Button guessButton;
    private ImageView pictures;
    private String picture = "https://raw.githubusercontent.com/AndreasKullberg/HangmanPic/master/Hangmanpic/hang";
    private ModelViewModel model;
    private Hangman hangman;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_play_game, container, false);
        guessLeftNr = view.findViewById(R.id.guessLeftNr);
        hiddenWord = view.findViewById(R.id.word);
        wrongLetters = view.findViewById(R.id.wrongGuess);
        guessLetter = view.findViewById(R.id.guessLetter);
        pictures = view.findViewById(R.id.imageView2);
        guessButton = view.findViewById(R.id.guessButton);

        model = ViewModelProviders.of(getActivity()).get(ModelViewModel.class);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.hangmanGame);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!model.isInGame()){
            hangman = new Hangman();
            String words[] = getResources().getStringArray(R.array.words);
            hangman.newWord(words);
            model.setHangman(hangman);
            model.setInGame(true);
        }
        else {
            hangman = model.getHangman();
        }

        String guessLeft = Integer.toString(hangman.getGuessLeft());
        guessLeftNr.setText(guessLeft);
        hiddenWord.setText(hangman.getHiddenWord());
        wrongLetters.setText(hangman.getWrongLettersUsed());

        Glide
                .with(this)
                .load(picture + hangman.getGuessLeft() + ".gif")
                .apply(new RequestOptions().override(300, 300))
                .into(pictures);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String tempGuess = guessLetter.getText().toString();
                char guess = '-';
                if (!tempGuess.equals("")) {
                    guess = tempGuess.charAt(0);
                }

                if(tempGuess.equals("")  || !Character.isAlphabetic(guess)) {
            Toast toast = Toast.makeText(getActivity(),getString(R.string.skrivBokstav),Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,600);
            toast.show();
                }
                else if(hangman.hasUsedLetter(guess)){
            Toast toast = Toast.makeText(getActivity(),getString(R.string.bokstav_Använd), Toast.LENGTH_LONG);
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
                        .with(view)
                        .load(picture + hangman.getGuessLeft() + ".gif")
                        .apply(new RequestOptions().override(300, 300))
                        .into(pictures);

                if(hangman.hasLost() || hangman.hasWon()){
                    model.setInGame(false);
                    Fragment resultActivity = new ResultActivity();
                    Bundle bundle = new Bundle();
                    bundle.putString("WORD", hangman.getWord());
                    bundle.putInt("GUESSLEFT", hangman.getGuessLeft());
                    resultActivity.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.framelayout,resultActivity)
                            .commit();
                }

            }
        });
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.playAction).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

}
