package com.hangman;

import com.fasterxml.jackson.annotation.*;

public class Phonetic {
    private String text;
    private String audio;

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.text = value;
    }

    @JsonProperty("audio")
    public String getAudio() {
        return audio;
    }

    @JsonProperty("audio")
    public void setAudio(String value) {
        this.audio = value;
    }
}