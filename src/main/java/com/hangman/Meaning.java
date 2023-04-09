package com.hangman;

import com.fasterxml.jackson.annotation.*;

public class Meaning {
    private String partOfSpeech;
    private Definition[] definitions;
    private String[] synonyms;
    private Object[] antonyms;

    @JsonProperty("partOfSpeech")
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    @JsonProperty("partOfSpeech")
    public void setPartOfSpeech(String value) {
        this.partOfSpeech = value;
    }

    @JsonProperty("definitions")
    public Definition[] getDefinitions() {
        return definitions;
    }

    @JsonProperty("definitions")
    public void setDefinitions(Definition[] value) {
        this.definitions = value;
    }

    @JsonProperty("synonyms")
    public String[] getSynonyms() {
        return synonyms;
    }

    @JsonProperty("synonyms")
    public void setSynonyms(String[] value) {
        this.synonyms = value;
    }

    @JsonProperty("antonyms")
    public Object[] getAntonyms() {
        return antonyms;
    }

    @JsonProperty("antonyms")
    public void setAntonyms(Object[] value) {
        this.antonyms = value;
    }
}