package application;

import config.LayoutConfig;
import config.StyleConfig;
import util.DataUtils;
import util.StyleUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

public class HangmanJPanel extends JPanel {
    private final Random rand;
    private final List<String> words;
    private final List<Character> guesses;
    private final List<JLabel> wordLabels;

    private JPanel keyboardPanel;
    private JPanel wordPanel;

    private JLabel wrongLabel;
    private JLabel imageLabel;

    private String word;
    private boolean gameFinished;

    private final Font mainFont;
    private final Font font40px;

    public HangmanJPanel() {
        rand = new Random();
        guesses = new ArrayList<>();
        wordLabels = new ArrayList<>();
        words = DataUtils.getData();

        setWord(words.get(rand.nextInt(words.size())));

        mainFont = StyleUtils.getFont();
        font40px = mainFont.deriveFont(40f);

        setUp();
        startGame();
    }

    public void setUp() {
        // SET PANEL LAYOUT
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(StyleConfig.BG);
        setOpaque(true);

        JPanel imagePanel = getImagePanel();
        JPanel settingPanel = getSettingPanel();
        JPanel textPanel = getTextPanel();
        keyboardPanel = getKeyBoardPanel();

        add(imagePanel);
        add(Box.createVerticalStrut(10));
        add(textPanel);
        add(Box.createVerticalStrut(10));
        add(keyboardPanel);
        add(Box.createVerticalStrut(10));
        add(settingPanel);
    }

    public void startGame() {
        // CLEAR GUESSES ARRAY LIST
        // SET GAME FINISH FALSE
        // RANDOM WORD
        guesses.clear();
        gameFinished = false;
        setWord(words.get(rand.nextInt(words.size())));

        // RESET WORD PANEL
        resetWordLabels();

        // RESET KEYBOARD PANEL
        if (keyboardPanel != null) {
            for (Component c : keyboardPanel.getComponents()) {
                if (c instanceof JButton button) {
                    button.setBackground(StyleConfig.BUTTON_BG);
                    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        }

        // RESET IMAGE
        updateImage("/images/1.png");

        // RESET WRONG LABEL
        if (wrongLabel != null) {
            wrongLabel.setText("Wrong: 0/6");
        }


        // REFRESH UI
        revalidate();
        repaint();

    }

    public void resetWordLabels() {
        wordPanel.removeAll();
        wordLabels.clear();
        for (int i = 0; i < word.length(); i++) {
            JLabel wordLabel = new JLabel("_");
            wordLabel.setFont(font40px);
            wordLabel.setPreferredSize(new Dimension(20, 60));
            wordLabel.setHorizontalAlignment(JLabel.CENTER);

            wordPanel.add(wordLabel);
            wordLabels.add(wordLabel);
        }
    }

    public void checkLetter() {
        // CHECK IF LETTER IS CORRECT
        for (int i = 0; i < word.length(); i++) {
            if (guesses.contains(word.charAt(i))) {
                if (i < wordLabels.size()) { // Check if the index is within the bounds of wordLabels
                    wordLabels.get(i).setText(String.valueOf(word.charAt(i)));
                }
            }
        }
    }

    public void checkWrong() {
        // CHECK WRONG LETTER IN GUESSES ARRAY LIST
        int wrongGuesses = 0;
        for (char guess : guesses) {
            if (!word.contains(String.valueOf(guess))) {
                wrongGuesses++;
            }
        }

        // CHANGE IMAGES
        switch (wrongGuesses) {
            case 1 -> updateImage("/images/2.png");
            case 2 -> updateImage("/images/3.png");
            case 3 -> updateImage("/images/4.png");
            case 4 -> updateImage("/images/5.png");
            case 5 -> updateImage("/images/6.png");
            case 6 -> updateImage("/images/7.png");
        }

        // IF WRONG 6/6 WILL FINISH GAME
        if (wrongGuesses >= 6) {
            gameFinished = true;

            // APPEAR RED LETTERS
            for (int i = 0; i < word.length(); i++) {
                if (!guesses.contains(word.charAt(i))) {
                    if (i < wordLabels.size()) {
                        wordLabels.get(i).setForeground(Color.RED);
                        wordLabels.get(i).setText(String.valueOf(word.charAt(i)));
                    }
                }
            }

            wrongLabel.setText("You lost. Wanna try again?");
        } else {
            wrongLabel.setText("Wrong: " + wrongGuesses + "/6");
        }
    }

    public void checkWin() {
        // CHECK IF ALL LETTERS ARE CORRECT
        boolean won = true;
        for (char letter : word.toCharArray()) {
            if (!guesses.contains(letter)) {
                won = false;
                break;
            }
        }

        // CHECK WINNING
        if (won) {
            gameFinished = true;

            // TURN ALL LETTERS IN GREEN
            for (JLabel wordLabel : wordLabels) {
                wordLabel.setForeground(Color.GREEN);
            }

            // IF ZERO WRONG , IT GIVES PERFECT TEXT
            int wrongGuesses = 0;
            for (char guess : guesses) {
                if (!word.contains(String.valueOf(guess))) {
                    wrongGuesses++;
                }
            }

            if (wrongGuesses == 0) {
                wrongLabel.setText("Awesome! Perfectly win.");
            } else {
                wrongLabel.setText("Awesome! You won in " + wrongGuesses + " out of 6 wrong guesses!");
            }

        }
    }

    public void updateImage(String path) {
        //  UPDATE IMAGE
        ImageIcon newImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        imageLabel.setIcon(newImage);
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public JPanel getImagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(StyleConfig.BG);

        // IMAGE LABEL
        imageLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/1.png"))));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(imageLabel);
        return panel;
    }

    public JPanel getTextPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(StyleConfig.BG);

        // WORD LABEL
        wordPanel = new JPanel();
        wordPanel.setBackground(StyleConfig.BG);
        wordPanel.setMinimumSize(new Dimension(LayoutConfig.SCREEN_WIDTH - 100, 60));
        wordPanel.setMaximumSize(new Dimension(LayoutConfig.SCREEN_WIDTH - 100, 60));
        wordPanel.setLayout(new GridLayout(1, word.length()));

        for (int i = 0; i < word.length(); i++) {
            JLabel wordLabel = new JLabel("_");
            wordLabel.setFont(font40px);
            wordLabel.setPreferredSize(new Dimension(20, 60));
            wordLabel.setHorizontalAlignment(JLabel.CENTER);

            wordPanel.add(wordLabel);
        }

        // WRONG LABEL
        wrongLabel = new JLabel("Wrong: 0/6");
        wrongLabel.setFont(mainFont);
        wrongLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        wrongLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panel.add(wordPanel);
        panel.add(wrongLabel);

        return panel;
    }

    public JPanel getKeyBoardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setLayout(new FlowLayout());
        panel.setBackground(StyleConfig.BG);

        // START AT A
        char letter = 'A';

        // CREATE A-Z BUTTONS
        for (int i = 0; i < 26; i++) {
            panel.add(getJButton(letter++));
        }

        return panel;
    }

    public JPanel getSettingPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(StyleConfig.BG);
        panel.setMinimumSize(new Dimension(LayoutConfig.SCREEN_WIDTH - 100, 30));
        panel.setMaximumSize(new Dimension(LayoutConfig.SCREEN_WIDTH - 100, 30));

        JButton restart = new JButton("RESTART");
        restart.setFont(mainFont);
        restart.setFocusPainted(false);
        restart.setBorderPainted(false);
        restart.setContentAreaFilled(true);
        restart.setForeground(Color.WHITE);
        restart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        restart.setBackground(new Color(0, 122, 255));

        restart.addActionListener(_ -> startGame());

        JButton gitHub = new JButton("GitHub");
        gitHub.setFont(mainFont);
        gitHub.setFocusPainted(false);
        gitHub.setBorderPainted(false);
        gitHub.setContentAreaFilled(true);
        gitHub.setForeground(Color.WHITE);
        gitHub.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gitHub.setBackground(new Color(0, 122, 255));

        gitHub.addActionListener(_ -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/Jagaradoz/Java-Projects"));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(restart);
        panel.add(gitHub);

        return panel;
    }

    public JPanel getWordPanel() {
        return wordPanel;
    }

    public JLabel getWrongLabel() {
        return wrongLabel;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public JButton getJButton(char letter) {
        JButton button = new JButton(Character.toString(letter));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(null);
        button.setPreferredSize(new Dimension(50, 50));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setFocusable(false);
        button.setBackground(StyleConfig.BUTTON_BG);

        button.addMouseListener(new ButtonListener(button));

        return button;
    }

    public List<JLabel> getWordLabels() {
        return wordLabels;
    }

    public List<Character> getGuesses() {
        return guesses;
    }

    public class ButtonListener extends MouseAdapter {
        private final JButton button;
        private final char letter;

        public ButtonListener(JButton button) {
            this.button = button;
            this.letter = button.getText().charAt(0);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!gameFinished && !guesses.contains(letter)) {
                guesses.add(letter);

                // CHECK LETTER
                // CHECK HOW MANY WRONG
                // CHECK WIN OR NOT
                checkLetter();
                checkWrong();
                checkWin();

                button.setBackground(StyleConfig.BUTTON_BG.darker());
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (!gameFinished && !guesses.contains(letter)) {
                button.setBackground(StyleConfig.BUTTON_BG.darker());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (!gameFinished && !guesses.contains(letter)) {
                button.setBackground(StyleConfig.BUTTON_BG);
            }
        }


        @Override
        public void mouseEntered(MouseEvent e) {
            if (!gameFinished && !guesses.contains(letter)) {
                button.setBackground(new Color(200, 200, 200));
            }
        }


        @Override
        public void mouseExited(MouseEvent e) {
            if (!gameFinished && !guesses.contains(letter)) {
                button.setBackground(StyleConfig.BUTTON_BG);
            }
        }
    }
}
