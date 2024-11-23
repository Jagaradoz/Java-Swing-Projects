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
        // INITIALIZE FONT
        mainFont = StyleUtils.getFont();
        font14f = mainFont.deriveFont(14f);
        font30f = mainFont.deriveFont(30f);

        // INITIALIZE UTILS
        rand = new Random();

        // INITIALIZE ARRAYS
        correctedBoard = new int[LayoutConfig.GRID_SIZE][LayoutConfig.GRID_SIZE];
        gameBoard = new int[LayoutConfig.GRID_SIZE][LayoutConfig.GRID_SIZE];
        textFields = new JTextField[LayoutConfig.GRID_SIZE][LayoutConfig.GRID_SIZE];

        // GENERATE PANELS
        textPanel = generateTextPanel();
        settingPanel = generateSettingPanel();
        boardPanel = generateBoardPanel();

        JPanel mainPanel = generateMainPanel();

        // ADD MAIN PANEL
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // START GAME
        startGame();
    }

    public void startGame() {
        // RESET VALUES
        gameFinished = false;
        wrongCount = 0;
        elapsedTime = 0;

        // RESET ALL LABELS
        wrongLabel.setText("Wrong : 0/3");
        centerLabel.setText("Continuing...");
        centerLabel.setForeground(Color.BLACK);
        timerLabel.setText("00:00");

        // GENERATE CORRECTED BOARD
        // GENERATE GAME BOARD
        generateCorrectedBoard();
        generateGameBoard();

        // GENERATE TIMER
        if (timer == null) {
            timer = new Timer(1000, _ -> {
                elapsedTime += 1;

                int seconds = elapsedTime % 60;
                int minutes = elapsedTime / 60;

                timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
            });
        }

        // START TIMER
        timer.start();
    }

    public void checkWinGame() {
        // CHECK ALL NUMBERS IS CORRECT (WIN = TRUE)
        boolean win = true;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (correctedBoard[i][j] != gameBoard[i][j]) {
                    win = false;
                    break;
                }
            }
        }

        // IF WIN = TRUE , SET WIN GAME
        if (win) {
            gameFinished = true;

            centerLabel.setText("You win!");
            centerLabel.setForeground(Color.GREEN);

            timer.stop();
        }
    }

    public void checkLoseGame() {
        // IF WRONG IS LESS THAN 3 , DO NOTHING
        if (wrongCount < 3) {
            return;
        }

        // SET GAME FINISH
        gameFinished = true;

        centerLabel.setText("You lost!");
        centerLabel.setForeground(Color.RED);

        timer.stop();

        // SET ALL TEXT FIELDS CAN'T BE EDITED
        for (Component component : boardPanel.getComponents()) {
            if (component instanceof JTextField textField) {
                textField.setEditable(false);
                textField.setFocusable(false);
            }
        }


    }

    public void generateGameBoard() {
        // COPY CORRECTED BOARD TO GAME BOARD
        for (int i = 0; i < correctedBoard.length; i++) {
            System.arraycopy(correctedBoard[i], 0, gameBoard[i], 0, correctedBoard[i].length);
        }

        // MAKE SOME NUMBERS DISAPPEAR
        int count = 40;
        while (count > 0) {
            int row = rand.nextInt(LayoutConfig.GRID_SIZE);
            int col = rand.nextInt(LayoutConfig.GRID_SIZE);

            if (gameBoard[row][col] != 0) {
                gameBoard[row][col] = 0;
                count--;
            }
        }

        // CHECK DEFAULT NUMBERS ON THE BOARD , ADJUST APPEARANCE
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
        // GENERATE NUMBERS 1-9 ON DIAGONAL BOXES
        for (int box = 0; box < LayoutConfig.GRID_SIZE; box += 3) {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

            // SHUFFLE ARRAY
            for (int i = arr.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

            // PUT NUMBERS
            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    correctedBoard[box + i][box + j] = arr[index++];
                }
            }
        }

        // SOLVE CORRECTED BOARD
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
        // CHECK IF PLAYER INPUT CORRECT NUMBER
        return correctedBoard[i][j] == number;
    }

    public boolean isValidPlacement(int row, int col, int number) {
        // CHECK IF THERE IS THE NUMBER ON THE ROW
        for (int i = 0; i < LayoutConfig.GRID_SIZE; i++) {
            if (correctedBoard[row][i] == number) {
                return false;
            }
        }

        // CHECK IF THERE IS THE NUMBER ON THE COLUMN
        for (int i = 0; i < LayoutConfig.GRID_SIZE; i++) {
            if (correctedBoard[i][col] == number) {
                return false;
            }
        }

        // CHECK IF THERE IS THE NUMBER ON THE BOX
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
        // IF ROW == 9 , MOVE TO ANOTHER COLUMN
        if (row == LayoutConfig.GRID_SIZE) {
            row = 0;
            col++;
            if (col == LayoutConfig.GRID_SIZE) {
                return true;
            }
        }

        // IF THE BLOCK HAS ALREADY A NUMBER , IT WILL SKIP
        if (correctedBoard[row][col] != 0) {
            return solveCorrectedBoard(row + 1, col);
        }

        // TRY TO PUT 1-9 NUMBERS ON THE BLOCK
        for (int num = 1; num <= LayoutConfig.GRID_SIZE; num++) {
            if (isValidPlacement(row, col, num)) {
                correctedBoard[row][col] = num;
                if (solveCorrectedBoard(row + 1, col)) {
                    return true;
                }
                correctedBoard[row][col] = 0; // BACK TRACKING
            }
        }
        return false;
    }

    public JPanel generateMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(StyleConfig.BG_COLOR);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ADD TEXT PANEL
        // ADD BOARD PANEL
        // ADD SETTING PANEL
        panel.add(textPanel);
        panel.add(boardPanel);
        panel.add(settingPanel);

        return panel;
    }

    public JPanel generateTextPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setMaximumSize(new Dimension(414, 30));
        panel.setBackground(StyleConfig.BG_COLOR);

        // WRONG LABEL
        wrongLabel = new JLabel("Wrong : 0/3");
        wrongLabel.setHorizontalAlignment(JLabel.CENTER);
        wrongLabel.setPreferredSize(new Dimension(100, 30));
        wrongLabel.setFont(font14f);

        // CENTER LABEL
        centerLabel = new JLabel("Continuing...");
        centerLabel.setHorizontalAlignment(JLabel.CENTER);
        centerLabel.setPreferredSize(new Dimension(100, 30));
        centerLabel.setFont(font14f);

        // TIMER LABEL
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

        // GENERATE TEXT FIELDS AND PUT THEM INTO PANEL , ARRAY
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

        // RESTART BUTTON
        JButton restart = new JButton("RESTART");
        restart.setBackground(new Color(0, 122, 255));
        restart.setForeground(Color.WHITE);
        restart.setFocusPainted(false);
        restart.setBorderPainted(false);
        restart.setContentAreaFilled(true);
        restart.setFont(mainFont);
        restart.setCursor(new Cursor(Cursor.HAND_CURSOR));

        restart.addActionListener(_ -> startGame());

        // GITHUB BUTTON
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
                Desktop.getDesktop().browse(new URI("https://github.com/Jagaradoz/Java-Projects"));
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

        // MAKE CHESS_BACKGROUND
        if ((i / 3 + j / 3) % 2 == 0) {
            textField.setBackground(StyleConfig.GRAY_BG_COLOR);
        } else {
            textField.setBackground(Color.WHITE);
        }

        // IF THE BLOCK HAS NO VALUE (0) , IT WILL ADD KEY LISTENER
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

            // ONLY INPUT 1-9 NUMBERS
            if (c < '1' || c > '9') {
                e.consume();
                return;
            }

            textField.setText(String.valueOf(number));
            gameBoard[i][j] = number;

            // CHECK ANSWER EVERY TIME PLAYER INPUT
            if (checkAnswer(i, j, number)) {
                textField.setForeground(Color.BLUE);
                checkWinGame();
            } else {
                textField.setForeground(Color.RED);

                // INCREASE WRONG COUNT
                wrongCount++;
                wrongLabel.setText("Wrong : " + wrongCount + "/3");

                checkLoseGame();
            }

            e.consume();
        }
    }
}

// PRINT BOARD METHOD
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