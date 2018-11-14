package com.example.andreaskullberg.hangman;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Fragment {
    private TextView winOrLose;
    private TextView showWord;
    private TextView guessLeftNr;
    private Button backToMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_result, container, false);
        winOrLose = view.findViewById(R.id.winOrLose);
        showWord = view.findViewById(R.id.showWord);
        guessLeftNr = view.findViewById(R.id.guessLeftNr);
        backToMenu = view.findViewById(R.id.backToMenu);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.resultat);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getArguments();
        String word = bundle.getString("WORD");
        int guessLeft = bundle.getInt("GUESSLEFT");

        if(guessLeft == 0){
            winOrLose.setText(getString(R.string.duFörlorade));
        }
        showWord.setText(word);
        String stringGuess = Integer.toString(guessLeft);
        guessLeftNr.setText(stringGuess);

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment menuActivity = new MenuActivity();
                getFragmentManager().beginTransaction().replace(R.id.framelayout,menuActivity)
                        .commit();
            }
        });

        return view;
    }
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.playAction).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

}
