package com.example.andreaskullberg.hangman;

import android.arch.lifecycle.ViewModel;

public class ModelViewModel extends ViewModel {
    private boolean theme;
    private Hangman hangman;
    private boolean inGame;

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Hangman getHangman() {
        return hangman;
    }

    public void setHangman(Hangman hangman) {
        this.hangman = hangman;
    }

    public boolean isTheme() {
        return theme;
    }

    public void setTheme(boolean theme) {
        this.theme = theme;
    }
}
