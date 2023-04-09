package com.hangman.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.*;

import com.hangman.buttons.BackButton;
import com.hangman.buttons.NewButton;
import com.hangman.db.User;
import com.hangman.db.UsersDatabase;
import com.hangman.words.Words;

public class TableScreenPanel extends JPanel implements ActionListener {
    private BackButton backButton;
    private NewButton prevButton;
    private NewButton refreshButton;
    private NewButton nextButton;
    private DefaultTableModel tableModel;
    private int currentPage = 0;
    private static final int PAGE_SIZE = 20;

    public TableScreenPanel(int WIDTH, int HEIGHT, ImageIcon backgroundImg, JPanel container, CardLayout cardLayout,
            MainGamePanel mainGamePanel, Words words) {
        JLabel tableScreenBg = new JLabel(backgroundImg);
        this.setLayout(new BorderLayout());
        this.add(tableScreenBg);

        tableScreenBg.setLayout(null);
        JPanel buttonsPanel = new JPanel();
        int buttonsPanelWidth = 500;
        int buttonsPanelHeight = 50;
        int buttonsPanelX = (WIDTH / 2) - (buttonsPanelWidth / 2);
        int buttonsPanelY = HEIGHT - (buttonsPanelHeight * 3);
        buttonsPanel.setBounds(buttonsPanelX, buttonsPanelY, buttonsPanelWidth, buttonsPanelHeight);
        buttonsPanel.setLayout(new GridLayout());

        tableScreenBg.add(buttonsPanel);

        tableModel = new DefaultTableModel(new Object[] { "Username", "Timestamp", "Score", "Time" }, 0);

        JTable table = new JTable(tableModel);

        JScrollPane panel = new JScrollPane(table);
        int tablePanelX = (WIDTH / 5);
        int tablePanelY = (HEIGHT / 5);

        panel.setBounds(tablePanelX, tablePanelY, buttonsPanelWidth, 300);
        panel.setLayout(new ScrollPaneLayout());
        tableScreenBg.add(panel);

        int buttonWidth = 80;
        int buttonHeight = 30;
        int buttonX = 10;

        String nextButtonText = "NEXT";
        int nextButtonY = 10;
        this.nextButton = new NewButton(nextButtonText, buttonX, nextButtonY, buttonWidth, buttonHeight);
        this.nextButton.addActionListener(this);

        String refreshButtonText = "REFRESH";
        int refreshButtonY = 10;
        this.refreshButton = new NewButton(refreshButtonText, buttonX, refreshButtonY, buttonWidth, buttonHeight);
        this.refreshButton.addActionListener(this);

        String prevButtonText = "PREV";
        int prevButtonY = nextButtonY + buttonHeight + 10;
        this.prevButton = new NewButton(prevButtonText, buttonX, prevButtonY, buttonWidth, buttonHeight);
        this.prevButton.addActionListener(this);

        String backButtonText = "BACK";
        this.backButton = new BackButton(backButtonText, container, cardLayout);
        this.backButton.addActionListener(this);
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(this.nextButton);
        buttonsPanel.add(this.refreshButton);
        buttonsPanel.add(this.prevButton);
        updateTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.backButton) {
            this.backButton.swapCard("1");
        }
        if (e.getSource() == this.nextButton) {
            this.currentPage++;
        }
        if (e.getSource() == this.prevButton) {
            this.currentPage--;
        }
        updateTable();

    }

    private void updateTable() {
        tableModel.setRowCount(0);
        List<User> users = new ArrayList<>();
        try {
            users.addAll(UsersDatabase.listUsersByScore(currentPage));
        } catch (Exception e) {
            System.err.println(e);
        }
        NumberFormat f = NumberFormat.getInstance();
        f.setGroupingUsed(true); // Utilizar separadores de miles
        f.setMinimumFractionDigits(0); // No mostrar decimales
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (User user : users) {
            Object[] row = new Object[] { user.getUsername(), dateFormat.format(user.getTimestamp()),
                    user.getScore(),
                    f.format(user.getTime()) };
            tableModel.addRow(row);
        }
        prevButton.setEnabled(currentPage > 0);
        nextButton.setEnabled(users.size() == PAGE_SIZE);
    }

}
