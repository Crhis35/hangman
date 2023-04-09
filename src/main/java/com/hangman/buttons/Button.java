package com.hangman.buttons;

import javax.swing.*;

public class Button extends JButton {

    private String text;
    private int x;
    private int y;
    private int width;
    private int height;

    public Button(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.setBounds(this.x, this.y, this.width, this.height);
        this.setText(this.text);
        this.setFocusable(false);
    }

    public Button(String text) {
        this.text = text;

        this.setText(this.text);
        this.setFocusable(false);
    }
}
