package application;

import config.LayoutConfig;
import config.StyleConfig;
import util.StyleUtils;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

import java.io.*;

import java.net.URISyntaxException;
import java.util.Random;

import java.net.URI;

public class SudokuPanel extends JPanel {
    private final Random rand;
    private Timer timer;

    private final JPanel textPanel;
    private final JPanel boardPanel;
    private final JPanel settingPanel;

    private final JTextField[][] textFields;

    private JLabel wrongLabel;
    private JLabel centerLabel;
    private JLabel timerLabel;

    private int elapsedTime;
    private int wrongCount;

    private boolean gameFinished;

    private final int[][] correctedBoard;
    private final int[][] gameBoard;

    private final Font mainFont;
    private final Font font14f;
    private final Font font30f;

    public SudokuPanel() {
        // Initializations.
        mainFont = StyleUtils.getFont();
        font14f = mainFont.deriveFont(14f);
        font30f = mainFont.deriveFont(30f);

        rand = new Random();

        correctedBoard = new int[LayoutConfig.GRID_SIZE][LayoutConfig.GRID_SIZE];
        gameBoard = new int[LayoutConfig.GRID_SIZE][LayoutConfig.GRID_SIZE];
        textFields = new JTextField[LayoutConfig.GRID_SIZE][LayoutConfig.GRID_SIZE];

        // Assign components.
        textPanel = generateTextPanel();
        settingPanel = generateSettingPanel();
        boardPanel = generateBoardPanel();

        JPanel mainPanel = generateMainPanel();

        // Add child components.
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        startGame();
    }

    public void startGame() {
        // Reset all values.
        gameFinished = false;
        wrongCount = 0;
        elapsedTime = 0;

        wrongLabel.setText("Wrong : 0/3");
        centerLabel.setText("Continuing...");
        centerLabel.setForeground(Color.BLACK);
        timerLabel.setText("00:00");

        // Generate corrected board.
        // Generate game board.
        generateCorrectedBoard();
        generateGameBoard();

        // Generate timer.
        if (timer == null) {
            timer = new Timer(1000, _ -> {
                elapsedTime += 1;

                int seconds = elapsedTime % 60;
                int minutes = elapsedTime / 60;

                timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
            });
        }

        timer.start();
    }

    public void checkWinGame() {
        // Check all numbers are correct (win = true).
        boolean win = true;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (correctedBoard[i][j] != gameBoard[i][j]) {
                    win = false;
                    break;
                }
            }
        }

        // If win = true, set win game.
        if (win) {
            gameFinished = true;

            centerLabel.setText("You win!");
            centerLabel.setForeground(Color.GREEN);

            timer.stop();
        }
    }

    public void checkLoseGame() {
        // If wrong < 3, do nothing.
        if (wrongCount < 3) {
            return;
        }

        // Set game finish.
        gameFinished = true;

        centerLabel.setText("You lost!");
        centerLabel.setForeground(Color.RED);

        timer.stop();

        // Set all text field can't be edited.
        for (Component component : boardPanel.getComponents()) {
            if (component instanceof JTextField textField) {
                textField.setEditable(false);
                textField.setFocusable(false);
            }
        }


    }

    public void generateGameBoard() {
        // Copy corrected board t the game board.
        for (int i = 0; i < correctedBoard.length; i++) {
            System.arraycopy(correctedBoard[i], 0, gameBoard[i], 0, correctedBoard[i].length);
        }

        // Make some number disappear.
        int count = 40;
        while (count > 0) {
            int row = rand.nextInt(LayoutConfig.GRID_SIZE);
            int col = rand.nextInt(LayoutConfig.GRID_SIZE);

            if (gameBoard[row][col] != 0) {
                gameBoard[row][col] = 0;
                count--;
            }
        }

        // Check default numbers on the board, adjust appearance.
        for (int i = 0; i < LayoutConfig.GRID_SIZE; i++) {
            for (int j = 0; j < LayoutConfig.GRID_SIZE; j++) {
                JTextField textField = textFields[i][j];
                textField.setForeground(Color.BLACK);

                if (gameBoard[i][j] == 0) {
                    textField.setEditable(true);
                    textField.setFocusable(true);
                    textField.setText(" ");

                } else {
                    textField.setEditable(false);
                    textField.setText(String.valueOf(gameBoard[i][j]));

                }
            }
        }
    }

    public void generateCorrectedBoard() {
        // Generate numbers 1-9 on diagonal boxes first.
        for (int box = 0; box < LayoutConfig.GRID_SIZE; box += 3) {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

            // Shuffle array.
            for (int i = arr.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

            // Assign numbers.
            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    correctedBoard[box + i][box + j] = arr[index++];
                }
            }
        }

        // Solve the corrected board.
        solveCorrectedBoard(3, 0);
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public int[][] getCorrectedBoard() {
        return correctedBoard;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public boolean checkAnswer(int i, int j, int number) {
        // Check if the player input the corrected number.
        return correctedBoard[i][j] == number;
    }

    public boolean isValidPlacement(int row, int col, int number) {
        // Check i there is the number on the row.
        for (int i = 0; i < LayoutConfig.GRID_SIZE; i++) {
            if (correctedBoard[row][i] == number) {
                return false;
            }
        }

        // Check if there is the number on the column.
        for (int i = 0; i < LayoutConfig.GRID_SIZE; i++) {
            if (correctedBoard[i][col] == number) {
                return false;
            }
        }

        // Check if there is the number on the box.
        int localRowBox = row - row % 3;
        int localColBox = col - col % 3;
        for (int i = localRowBox; i < localRowBox + 3; i++) {
            for (int j = localColBox; j < localColBox + 3; j++) {
                if (correctedBoard[i][j] == number) {
                    return false;
                }
            }

        }

        return true;
    }

    public boolean solveCorrectedBoard(int row, int col) {
        // If row == 9, move to another column.
        if (row == LayoutConfig.GRID_SIZE) {
            row = 0;
            col++;
            if (col == LayoutConfig.GRID_SIZE) {
                return true;
            }
        }

        // If the block has already a number, skip it.
        if (correctedBoard[row][col] != 0) {
            return solveCorrectedBoard(row + 1, col);
        }

        // Try to put 1-9 numbers on the block.
        for (int num = 1; num <= LayoutConfig.GRID_SIZE; num++) {
            if (isValidPlacement(row, col, num)) {
                correctedBoard[row][col] = num;
                if (solveCorrectedBoard(row + 1, col)) {
                    return true;
                }
                correctedBoard[row][col] = 0; // Back tracking.
            }
        }
        return false;
    }

    public JPanel generateMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(StyleConfig.BG_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add text panel.
        // Add board panel.
        // Add setting panel.
        panel.add(textPanel);
        panel.add(boardPanel);
        panel.add(settingPanel);

        return panel;
    }

    public JPanel generateTextPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setMaximumSize(new Dimension(414, 30));
        panel.setBackground(StyleConfig.BG_COLOR);

        wrongLabel = new JLabel("Wrong : 0/3");
        wrongLabel.setHorizontalAlignment(JLabel.CENTER);
        wrongLabel.setPreferredSize(new Dimension(100, 30));
        wrongLabel.setFont(font14f);

        centerLabel = new JLabel("Continuing...");
        centerLabel.setHorizontalAlignment(JLabel.CENTER);
        centerLabel.setPreferredSize(new Dimension(100, 30));
        centerLabel.setFont(font14f);

        timerLabel = new JLabel("00:00");
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setPreferredSize(new Dimension(100, 30));
        timerLabel.setFont(font14f);

        panel.add(wrongLabel);
        panel.add(centerLabel);
        panel.add(timerLabel);
        return panel;
    }

    public JPanel generateBoardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(StyleConfig.BG_COLOR);
        panel.setLayout(new GridLayout(9, 9, 0, 0));
        panel.setMaximumSize(new Dimension(450, 450));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (int i = 0; i < LayoutConfig.GRID_SIZE; i++) {
            for (int j = 0; j < LayoutConfig.GRID_SIZE; j++) {
                JTextField textField = generateJTextField(i, j);

                textFields[i][j] = textField;
                panel.add(textField);
            }
        }

        return panel;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JPanel getTextPanel() {
        return textPanel;
    }

    public JPanel getSettingPanel() {
        return settingPanel;
    }

    public JPanel generateSettingPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(StyleConfig.BG_COLOR);

        panel.setPreferredSize(new Dimension(LayoutConfig.SCREEN_WIDTH - 100, 50));
        panel.setMaximumSize(new Dimension(LayoutConfig.SCREEN_WIDTH - 100, 50));

        JButton restart = new JButton("RESTART");
        restart.setBackground(new Color(0, 122, 255));
        restart.setForeground(Color.WHITE);
        restart.setFocusPainted(false);
        restart.setBorderPainted(false);
        restart.setContentAreaFilled(true);
        restart.setFont(mainFont);
        restart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        restart.addActionListener(_ -> startGame());

        JButton gitHub = new JButton("GitHub");
        gitHub.setFont(mainFont);
        gitHub.setBackground(new Color(0, 122, 255));
        gitHub.setForeground(Color.WHITE);
        gitHub.setFocusPainted(false);
        gitHub.setBorderPainted(false);
        gitHub.setContentAreaFilled(true);
        gitHub.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gitHub.addActionListener(_ -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/Jagaradoz/Java-Swing-Projects"));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(restart);
        panel.add(gitHub);

        return panel;
    }

    public JLabel getWrongLabel() {
        return wrongLabel;
    }

    public JLabel getCenterLabel() {
        return centerLabel;
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }

    public Timer getTimer() {
        return timer;
    }

    public JTextField generateJTextField(int i, int j) {
        JTextField textField = new JTextField();
        textField.setFont(font30f);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(new LineBorder(StyleConfig.LINE_COLOR, 1));

        // Make chess-background.
        if ((i / 3 + j / 3) % 2 == 0) {
            textField.setBackground(StyleConfig.GRAY_BG_COLOR);
        } else {
            textField.setBackground(Color.WHITE);
        }

        // If the block has no value (0), it will add key listener.
        if (gameBoard[i][j] != 0) {
            textField.setText(String.valueOf(gameBoard[i][j]));
            textField.setFocusable(false);
            textField.setEditable(false);
        } else {
            textField.addKeyListener(new KeyboardListener(i, j, textField));
        }


        return textField;
    }

    public class KeyboardListener extends KeyAdapter {
        private final int i;
        private final int j;
        private final JTextField textField;

        public KeyboardListener(int i, int j, JTextField textField) {
            this.i = i;
            this.j = j;
            this.textField = textField;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (gameFinished) return;

            char c = e.getKeyChar();
            int number = Character.getNumericValue(c);

            // Only input 1-9 numbers.
            if (c < '1' || c > '9') {
                e.consume();
                return;
            }

            textField.setText(String.valueOf(number));
            gameBoard[i][j] = number;

            // Check the answer every time the player input.
            if (checkAnswer(i, j, number)) {
                textField.setForeground(Color.BLUE);
                checkWinGame();
            } else {
                textField.setForeground(Color.RED);

                // Increase wrong count.
                wrongCount++;
                wrongLabel.setText("Wrong : " + wrongCount + "/3");

                checkLoseGame();
            }

            e.consume();
        }
    }
}

// Print board method:
//    public void printBoard(int[][] board) {
//        for (int i = 0; i < LayoutConfig.GRID_SIZE; i++) {
//            if (i % 3 == 0 && i != 0) {
//                System.out.println("- - - - - - - - - - - -");
//            }
//            for (int j = 0; j < LayoutConfig.GRID_SIZE; j++) {
//                if (j % 3 == 0 && j != 0) {
//                    System.out.print("| ");
//                }
//                if (board[i][j] == 0) {
//                    System.out.print(". ");
//                } else {
//                    System.out.print(board[i][j] + " ");
//                }
//            }
//            System.out.println();
//        }
//    }
