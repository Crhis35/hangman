package com.hangman.buttons;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class BackButton extends Button {

    private JPanel container;
    private CardLayout cardLayout;

    public BackButton(String text, int x, int y, int width, int height, JPanel container, CardLayout cardLayout) {
        super(text, x, y, width, height);
        this.container = container;
        this.cardLayout = cardLayout;
    }

    public BackButton(String text, JPanel container, CardLayout cardLayout) {
        super(text);
        this.container = container;
        this.cardLayout = cardLayout;
    }

    public void swapCard(String cardNum) {
        this.cardLayout.show(this.container, cardNum);
    }
}