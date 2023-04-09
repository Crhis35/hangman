package com.hangman;

import com.fasterxml.jackson.annotation.*;

public class Definition {
    private String definition;
    private Object[] synonyms;
    private Object[] antonyms;

    @JsonProperty("definition")
    public String getDefinition() {
        return definition;
    }

    @JsonProperty("definition")
    public void setDefinition(String value) {
        this.definition = value;
    }

    @JsonProperty("synonyms")
    public Object[] getSynonyms() {
        return synonyms;
    }

    @JsonProperty("synonyms")
    public void setSynonyms(Object[] value) {
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