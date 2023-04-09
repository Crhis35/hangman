package com.hangman.buttons;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.hangman.panels.MainGamePanel;
import com.hangman.words.Word;
import com.hangman.words.Words;

public class CategoryButton extends Button {

    private String category;
    private JPanel container;
    private CardLayout cardLayout;

    public CategoryButton(String text, JPanel container, CardLayout cardLayout) {
        super(text);
        this.container = container;
        this.cardLayout = cardLayout;
    }

    private void createListOfWords(Words words) {
        words.addWordsToListOfWords(this.getCategory());
    }

    public void swapCardOld(Words words, MainGamePanel mainGamePanel) {
        this.createListOfWords(words);
        Word word = words.selectRandomWord();
        word.splitWordToLetters();
        mainGamePanel.setRandomWord(word);
        mainGamePanel.setGuessedLetters(word);
        this.cardLayout.show(this.container, "3");

    }

    public void swapCard(Words words, MainGamePanel mainGamePanel) {
        Word word = words.selectRandomWord();
        word.splitWordToLetters();
        mainGamePanel.setRandomWord(word);
        mainGamePanel.setGuessedLetters(word);
        this.cardLayout.show(this.container, "3");
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}