package com.example.andreaskullberg.hangman;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MenuActivity extends Fragment {
    private Button play;
    private Button about;
    private Switch themeButton;
    private ModelViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu, container, false);
        model = ViewModelProviders.of(getActivity()).get(ModelViewModel.class);

        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.huvudmeny);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        play = view.findViewById(R.id.playGame);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment playGameActivity = new PlayGameActivity();
                getFragmentManager().beginTransaction().replace(R.id.framelayout,playGameActivity)
                        .addToBackStack(null)
                        .commit();
            }
        });
        about = view.findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment aboutActivity = new aboutActivity();
                getFragmentManager().beginTransaction().replace(R.id.framelayout,aboutActivity)
                        .addToBackStack(null)
                        .commit();
            }
        });

        themeButton = view.findViewById(R.id.themeButton);
        if(model.isTheme()){
            themeButton.setChecked(true);
            getActivity().setTheme(R.style.HalloweenTheme);
        }
        else {
            themeButton.setChecked(false);
            getActivity().setTheme(R.style.AppTheme);
        }
        themeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (themeButton.isChecked()) {
                   model.setTheme(true);
                    getActivity().recreate();
                }
                else {
                    model.setTheme(false);
                    getActivity().recreate();
                }

            }
        });

        return view;
    }


    public void onBackPressed() {

    }

}
