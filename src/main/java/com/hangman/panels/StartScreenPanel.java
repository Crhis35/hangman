package com.hangman.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.hangman.buttons.*;

public class StartScreenPanel extends JPanel implements ActionListener {

    private PlayButton playButton;
    private PlayButton resultsButton;
    private ExitButton exitButton;

    public StartScreenPanel(int WIDTH, int HEIGHT, ImageIcon backgroundImg, JPanel container, CardLayout cardLayout) {

        JLabel startScreenBg = new JLabel(backgroundImg);

        // Sets up the button dimensions
        int buttonWidth = 100;
        int buttonHeight = 50;
        int buttonY = HEIGHT - (buttonHeight * 3);

        // Sets up the PLAY button
        String playButtonText = "PLAY";
        int playButtonX = (WIDTH / 3) - (buttonWidth + 20);
        this.playButton = new PlayButton(playButtonText, playButtonX, buttonY, buttonWidth, buttonHeight, container,
                cardLayout);
        this.playButton.addActionListener(this);
        // Sets up the PLAY button
        String resultsButtonText = "RESULTS";
        int resultsButtonX = (WIDTH / 2) - (buttonWidth + 20);
        this.resultsButton = new PlayButton(resultsButtonText, resultsButtonX, buttonY, buttonWidth, buttonHeight,
                container,
                cardLayout);
        this.resultsButton.addActionListener(this);

        // Sets up the EXIT button
        String exitButtonText = "EXIT";
        int exitButtonX = (WIDTH / 3) + (2 * buttonWidth + 20);
        this.exitButton = new ExitButton(exitButtonText, exitButtonX, buttonY, buttonWidth, buttonHeight);
        this.exitButton.addActionListener(this);

        this.setLayout(new BorderLayout());
        this.add(startScreenBg);

        startScreenBg.setLayout(null);
        startScreenBg.add(this.playButton);
        startScreenBg.add(this.resultsButton);
        startScreenBg.add(this.exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.playButton) {
            this.playButton.swapCard("2");
        }
        if (e.getSource() == this.resultsButton) {
            this.resultsButton.swapCard("4");
        }

        if (e.getSource() == this.exitButton) {
            System.exit(0);
        }
    }
}
