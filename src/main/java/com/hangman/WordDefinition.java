package com.hangman;

import com.fasterxml.jackson.annotation.*;

public class WordDefinition {
    private String word;
    private String phonetic;
    private Phonetic[] phonetics;
    private Meaning[] meanings;
    private License license;
    private String[] sourceUrls;

    @JsonProperty("word")
    public String getWord() {
        return word;
    }

    @JsonProperty("word")
    public void setWord(String value) {
        this.word = value;
    }

    @JsonProperty("phonetic")
    public String getPhonetic() {
        return phonetic;
    }

    @JsonProperty("phonetic")
    public void setPhonetic(String value) {
        this.phonetic = value;
    }

    @JsonProperty("phonetics")
    public Phonetic[] getPhonetics() {
        return phonetics;
    }

    @JsonProperty("phonetics")
    public void setPhonetics(Phonetic[] value) {
        this.phonetics = value;
    }

    @JsonProperty("meanings")
    public Meaning[] getMeanings() {
        return meanings;
    }

    @JsonProperty("meanings")
    public void setMeanings(Meaning[] value) {
        this.meanings = value;
    }

    @JsonProperty("license")
    public License getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(License value) {
        this.license = value;
    }

    @JsonProperty("sourceUrls")
    public String[] getSourceUrls() {
        return sourceUrls;
    }

    @JsonProperty("sourceUrls")
    public void setSourceUrls(String[] value) {
        this.sourceUrls = value;
    }
}
