package com.hangman.words;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.hangman.Converter;
import com.hangman.Definition;
import com.hangman.WordConverter;
import com.hangman.WordDefinition;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Words {

    private ArrayList<Word> listOfWords;
    private Random rand;
    private Word emergencyWord;

    public Words() {
        this.listOfWords = new ArrayList<>();
        this.rand = new Random();
        this.emergencyWord = new Word("hangman");
    }

    public void addWordsToListOfWords(String wordCategory) {
        /*
         * Add words from file to the list of words
         * https://random-word-api.herokuapp.com/word
         * https://api.dictionaryapi.dev/api/v2/entries/en/fabulate
         */

        try {
            File words = new File("words\\" + wordCategory + ".txt");
            Scanner reader = new Scanner(words);

            while (reader.hasNextLine()) {
                String data = reader.nextLine().strip();
                Word word = new Word(data.toLowerCase());
                word.splitWordToLetters();
                this.listOfWords.add(word);
            }

            reader.close();
        } catch (FileNotFoundException e) {
        }
    }
    // public Word selectRandomWord() {
    // /*
    // * Selects a random word from the list of words
    // */

    // if (this.listOfWords.size() > 0) {
    // int upperbound = this.listOfWords.size();
    // return this.listOfWords.get(rand.nextInt(upperbound));
    // }
    // return this.emergencyWord;
    // }
    public Word selectRandomWord() {
        Word word = getWord();
        setDefinition(word);
        return word;
    }

    private Word getWord() {
        try {
            URL url = new URL(
                    "https://random-word-api.herokuapp.com/word");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                try {
                    Scanner scanner = new Scanner(conn.getInputStream());
                    String[] words = WordConverter.fromJsonString(scanner.nextLine());
                    System.out.println(words[0]);

                    scanner.close();

                    return new Word(words[0]);
                } catch (IOException e) {

                }

            }
        } catch (IOException e) {
        }

        return this.emergencyWord;
    }

    private void setDefinition(Word word) {
        try {
            System.out.println("Word:" + word.getWord());

            URL url = new URL(
                    String.format("https://api.dictionaryapi.dev/api/v2/entries/en/%s", word.getWord()));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                try {
                    Scanner scanner = new Scanner(conn.getInputStream());
                    WordDefinition[] data = Converter.fromJsonString(scanner.nextLine());
                    System.out.print("Data: " + data[0]);
                    StringBuilder sb = new StringBuilder();
                    for (Definition texto : data[0].getMeanings()[0].getDefinitions()) {
                        sb.append("- " + texto.getDefinition()).append("\n");
                    }
                    word.setDefinition(sb.toString());
                    scanner.close();
                } catch (IOException e) {
                    System.out.println(e);
                }

            }

        } catch (IOException e) {
            word.setDefinition("It is a amazing game!");
        }
    }

    public void resetListOfWords() {
        this.listOfWords.clear();
    }

    public void setListOfWords(ArrayList<Word> listOfWords) {
        this.listOfWords = listOfWords;
    }

}
