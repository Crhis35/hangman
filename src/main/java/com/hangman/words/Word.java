package com.hangman.words;

public class Word {

    private String word;
    private char[] lettersInWord;
    private String definition;

    public Word(String word) {
        this.word = word;
    }

    public void splitWordToLetters() {
        /*
         * Splits the word into letters for comparison against letter clicked on
         * keyboard
         */

        this.lettersInWord = this.word.toCharArray();
    }

    public char[] getLettersInWord() {
        return this.lettersInWord;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setLettersInWord(char[] lettersInWord) {
        this.lettersInWord = lettersInWord;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }
}
