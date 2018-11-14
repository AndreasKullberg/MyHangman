package com.example.andreaskullberg.hangman;

import android.arch.lifecycle.ViewModel;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman extends ViewModel {
    private String wrongLetters;
    private String word;
    private String hiddenWord;
    private char[] charWord;
    private int guessLeft;
    private String usedLetters;
    private static Random random = new Random();

    public Hangman() {
        this.wrongLetters = "";
        this.guessLeft = 10;
        this.usedLetters = "";
    }

    public int getGuessLeft() {
        return guessLeft;
    }

    public String getWord() {
        return word;
    }
    public String getWrongLettersUsed(){
        return wrongLetters;
    }
    public void initiateCharWord(){
        charWord = new char[word.length()];
        for (int i = 0; i < word.length() ; i++) {
            charWord[i] = '-';
        }
    }
    public void guess(char guess){
        int x = 0;
        usedLetters += guess;
        for (int i = 0; i < word.length(); i++) {
            if(guess == word.charAt(i)){
                charWord[i] = guess;
                x++;
            }
        }
        if (x == 0){
            guessLeft--;
            wrongLetters += guess;
        }
    }
    public String getHiddenWord(){
        hiddenWord = "";
        for (int i = 0; i < charWord.length; i++) {
            hiddenWord += charWord[i];
        }
        return hiddenWord;
    }
    public boolean hasWon(){
        if(hiddenWord.equals(word)){
            return true;
        }
        return false;
    }
    public boolean hasLost(){
        if (guessLeft == 0){
            return true;
        }
        return false;
    }
    public boolean hasUsedLetter(char guess){
        for (int i = 0; i < usedLetters.length(); i++) {
            if (guess == usedLetters.charAt(i)){
                return true;
            }
        }
        return false;
    }
    public void newWord(String[] words){
       int index = random.nextInt(words.length);
       word = words[index];
       initiateCharWord();
    }

    // the new boston



}
