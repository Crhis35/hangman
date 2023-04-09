package com.hangman;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hangman.db.MysqlConnect;
import com.hangman.db.UsersDatabase;
import com.hangman.panels.CategoryScreenPanel;
import com.hangman.panels.MainGamePanel;
import com.hangman.panels.StartScreenPanel;
import com.hangman.panels.TableScreenPanel;
import com.hangman.words.Words;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        try {
            UsersDatabase.createTableIfNotExists();
            App instance = new App();
            instance.initComponents();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public void initComponents() {
        final int WIDTH = 800;
        final int HEIGHT = 600;
        final String TITLE = "Hangman Ultimatum";
        final ImageIcon backgroundImg = new ImageIcon(
                this.getClass().getResource("img/bgApp.jpeg"));

        JFrame screen = new JFrame();
        CardLayout cardLayout = new CardLayout();
        screen.setSize(new Dimension(WIDTH, HEIGHT));
        screen.setTitle(TITLE);
        screen.setLocationRelativeTo(null);
        screen.setResizable(false);
        screen.setDefaultCloseOperation(screen.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(cardLayout);
        screen.add(container);

        Words words = new Words();

        StartScreenPanel startScreenPanel = new StartScreenPanel(WIDTH, HEIGHT, backgroundImg, container, cardLayout);
        MainGamePanel mainGamePanel = new MainGamePanel(WIDTH, HEIGHT, container, cardLayout, words);
        CategoryScreenPanel categoryScreenPanel = new CategoryScreenPanel(WIDTH, HEIGHT, backgroundImg, container,
                cardLayout, mainGamePanel, words);
        TableScreenPanel tableScreenPanel = new TableScreenPanel(WIDTH, HEIGHT, backgroundImg, container,
                cardLayout, mainGamePanel, words);

        container.add(startScreenPanel, "1");
        container.add(categoryScreenPanel, "2");
        container.add(mainGamePanel, "3");
        container.add(tableScreenPanel, "4");

        screen.setVisible(true);
    }

    public static void createTable(String[] args) {
        System.out.println("Hello World!");
        String sql = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255) NOT NULL)";
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            Statement statement = mysqlConnect.connect().createStatement();
            statement.executeUpdate(sql);
            System.out.println("st");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }
}
