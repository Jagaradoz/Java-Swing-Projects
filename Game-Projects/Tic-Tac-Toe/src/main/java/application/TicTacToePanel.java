package application;

import config.StyleConfig;
import util.StyleUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class TicTacToePanel extends JPanel {
    private final Random rand;
    private final JButton[][] buttons;

    private final JLabel gameLabel;
    private final JPanel gamePanel;

    private boolean gameOver;

    private final Font mainFont;
    private final Font font40f;

    public TicTacToePanel() {
        // Initializations.
        rand = new Random();
        buttons = new JButton[3][3];

        // Set font.
        mainFont = StyleUtils.getFont();
        font40f = mainFont.deriveFont(40f);

        // Set background and layout.
        setBackground(StyleConfig.LIGHT_BG);
        setLayout(null);

        // Assign components.
        gamePanel = generateGamePanel();
        gameLabel = generateGameLabel();

        // Add child components.
        add(gameLabel);
        add(gamePanel);

        startGame();
    }

    public void startGame() {
        gameOver = false;

        gameLabel.setText("Tic Tac Toe");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BUTTON_BG_1 : StyleConfig.LIGHT_BUTTON_BG_1);
                buttons[i][j].setForeground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_TEXT : StyleConfig.LIGHT_TEXT);
            }
        }

        // Update UI.
        repaint();
        revalidate();
    }

    public void computerMove() {
        if (isBoardFull()) return;

        int i;
        int j;

        do {
            // Randomize i and j for computer movees.
            i = rand.nextInt(3);
            j = rand.nextInt(3);
        } while (!buttons[i][j].getText().isEmpty());

        buttons[i][j].setText("O");
        buttons[i][j].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void disableButtons() {
        for (JButton[] rowButton : buttons) {
            for (JButton button : rowButton) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public void playerMove(int i, int j) {
        // Check if button text is empty.
        JButton button = buttons[i][j];
        if (button.getText().isEmpty()) {
            button.setText("X");
            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            // Check the player wins.
            if (checkWinner()) {
                gameOver = true;
                gameLabel.setText("Player X wins");
                disableButtons();
                return;
            }

            // Check the board is full to allow computer move.
            if (!isBoardFull()) {
                computerMove();
            }

            // Check the computer wins.
            if (checkWinner()) {
                gameOver = true;
                gameLabel.setText("Player O wins");
                disableButtons();
                return;
            }

            // Check draw.
            if (isBoardFull() && !gameOver) {
                gameLabel.setText("DRAW!");
                disableButtons();
            }
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkWinner() {
        // Check rows (Horizontal)
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty() &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText())) {
                // Change color for winning row
                buttons[i][0].setBackground(StyleConfig.WINNER_BG);
                buttons[i][1].setBackground(StyleConfig.WINNER_BG);
                buttons[i][2].setBackground(StyleConfig.WINNER_BG);
                buttons[i][0].setForeground(StyleConfig.WINNER_TEXT);
                buttons[i][1].setForeground(StyleConfig.WINNER_TEXT);
                buttons[i][2].setForeground(StyleConfig.WINNER_TEXT);
                return true;
            }
        }

        // Check cols (Vertical)
        for (int j = 0; j < 3; j++) {
            if (!buttons[0][j].getText().isEmpty() &&
                    buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[1][j].getText().equals(buttons[2][j].getText())) {
                // Change color for winning column
                buttons[0][j].setBackground(StyleConfig.WINNER_BG);
                buttons[1][j].setBackground(StyleConfig.WINNER_BG);
                buttons[2][j].setBackground(StyleConfig.WINNER_BG);
                buttons[0][j].setForeground(StyleConfig.WINNER_TEXT);
                buttons[1][j].setForeground(StyleConfig.WINNER_TEXT);
                buttons[2][j].setForeground(StyleConfig.WINNER_TEXT);
                return true;
            }
        }

        // Check diagonal (Top-left to bottom-right)
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText())) {
            // Change color for winning diagonal
            buttons[0][0].setBackground(StyleConfig.WINNER_BG);
            buttons[1][1].setBackground(StyleConfig.WINNER_BG);
            buttons[2][2].setBackground(StyleConfig.WINNER_BG);
            buttons[0][0].setForeground(StyleConfig.WINNER_TEXT);
            buttons[1][1].setForeground(StyleConfig.WINNER_TEXT);
            buttons[2][2].setForeground(StyleConfig.WINNER_TEXT);
            return true;
        }

        // Check diagonal (Top-right to bottom-left)
        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText())) {
            // Change color for winning diagonal
            buttons[0][2].setBackground(StyleConfig.WINNER_BG);
            buttons[1][1].setBackground(StyleConfig.WINNER_BG);
            buttons[2][0].setBackground(StyleConfig.WINNER_BG);
            buttons[0][2].setForeground(StyleConfig.WINNER_TEXT);
            buttons[1][1].setForeground(StyleConfig.WINNER_TEXT);
            buttons[2][0].setForeground(StyleConfig.WINNER_TEXT);
            return true;
        }

        // No winner found
        return false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public JLabel generateGameLabel() {

        JLabel newLabel = new JLabel("Tic Tac Toe");
        newLabel.setFont(font40f);
        newLabel.setHorizontalAlignment(JLabel.CENTER);

        newLabel.setBounds(0, 0, 500, 70);

        return newLabel;
    }

    public JPanel generateGamePanel() {
        JPanel newPanel = new JPanel();
        newPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
        newPanel.setLayout(new GridLayout(3, 3, 3, 3));
        newPanel.setBackground(StyleConfig.LIGHT_BG);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = getJButton(i, j);

                newPanel.add(button);
                buttons[i][j] = button;
            }
        }

        newPanel.setBounds(75, 75, 350, 350);

        return newPanel;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JLabel getGameLabel() {
        return gameLabel;
    }

    public JButton getJButton(int i, int j) {
        Font font40px = mainFont.deriveFont(40f);

        JButton button = new JButton("");
        button.setBorder(null);
        button.setFocusable(false);
        button.setForeground(StyleConfig.LIGHT_TEXT);
        button.setBackground(StyleConfig.LIGHT_BUTTON_BG_1);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(font40px);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.addMouseListener(new ButtonListener(i, j, button));

        return button;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public class ButtonListener extends MouseAdapter {
        private final int row;
        private final int col;

        private final JButton button;

        public ButtonListener(int row, int col, JButton button) {
            this.row = row;
            this.col = col;
            this.button = button;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!gameOver) playerMove(row, col);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (button.getText().isEmpty() && !gameOver)
                button.setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BUTTON_BG_1.darker() : StyleConfig.LIGHT_BUTTON_BG_1.darker());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (button.getText().isEmpty() && !gameOver)
                button.setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BUTTON_BG_1 : StyleConfig.LIGHT_BUTTON_BG_1);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (button.getText().isEmpty() && !gameOver)
                button.setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BUTTON_BG_1.darker() : StyleConfig.LIGHT_BUTTON_BG_2);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (button.getText().isEmpty() && !gameOver)
                button.setBackground(TicTacToeJMenuBar.DARK_MODE ? StyleConfig.DARK_BUTTON_BG_1.darker() : StyleConfig.LIGHT_BUTTON_BG_1);
        }
    }


}
