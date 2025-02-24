package application;

import config.StyleConfig;
import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Initialize TicTacToePanelTest")
class TicTacToePanelTest {
    private TicTacToePanel ticTacToePanel;
    private JButton[][] buttons;

    @BeforeEach
    public void setUp() {
        ticTacToePanel = new TicTacToePanel();
        buttons = ticTacToePanel.getButtons();
    }

    @Test
    @DisplayName("Should get gamePanel properly")
    public void shouldGetGamePanelProperly() {
        assertNotNull(ticTacToePanel.getGamePanel());
    }

    @Test
    @DisplayName("Should get gameLabel properly")
    public void shouldGetGameLabelProperly() {
        assertNotNull(ticTacToePanel.getGameLabel());
    }

    @Test
    @DisplayName("Should start game properly")
    public void shouldStartGameProperly() {
        ticTacToePanel.startGame();

        assertFalse(ticTacToePanel.isGameOver());
        assertEquals("Tic Tac Toe", ticTacToePanel.getGameLabel().getText());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(buttons[i][i].isEnabled());
                assertEquals("", buttons[i][i].getText());
            }
        }
    }

    @Test
    @DisplayName("Computer should move properly")
    public void computerShouldMoveProperly() {
        buttons[0][0].setText("X");
        buttons[1][1].setText("X");

        ticTacToePanel.computerMove();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("O")) {
                    assertEquals("O", buttons[i][j].getText());
                }
            }
        }
    }

    @Test
    @DisplayName("Player should move properly")
    public void playerShouldMoveProperly() {
        ticTacToePanel.playerMove(0, 0);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("X")) {
                    assertEquals("X", buttons[i][j].getText(), "Expected cell (" + i + ", " + j + ") to contain 'X' after the player's move.");
                }
            }
        }
    }

    @Test
    @DisplayName("Board should be full properly")
    public void boardShouldBeFullProperly() {
        ticTacToePanel.computerMove();
        ticTacToePanel.computerMove();
        ticTacToePanel.computerMove();

        assertFalse(ticTacToePanel.isBoardFull());

        ticTacToePanel.computerMove();
        ticTacToePanel.computerMove();
        ticTacToePanel.computerMove();

        assertFalse(ticTacToePanel.isBoardFull());

        ticTacToePanel.computerMove();
        ticTacToePanel.computerMove();
        ticTacToePanel.computerMove();

        assertTrue(ticTacToePanel.isBoardFull());

    }

    @Nested
    @DisplayName("Check win in 4 possibility")
    class CheckWin4Possibility {
        @Test
        @DisplayName("should return true for a winning row")
        public void shouldReturnTrueForWinningRow() {
            buttons[0][0].setText("X");
            buttons[0][1].setText("X");
            buttons[0][2].setText("X");

            assertTrue(ticTacToePanel.checkWinner(), "Expected checkWinner to return true for a winning row.");

            assertEquals(StyleConfig.WINNER_BG, buttons[0][0].getBackground(), "Expected winning row cell to have WINNER_BG color.");
            assertEquals(StyleConfig.WINNER_TEXT, buttons[0][0].getForeground(), "Expected winning row cell to have WINNER_TEXT color.");
        }

        @Test
        @DisplayName("should return true for a winning column")
        public void shouldReturnTrueForWinningColumn() {
            buttons[0][0].setText("O");
            buttons[1][0].setText("O");
            buttons[2][0].setText("O");

            assertTrue(ticTacToePanel.checkWinner(), "Expected checkWinner to return true for a winning column.");

            assertEquals(StyleConfig.WINNER_BG, buttons[0][0].getBackground(), "Expected winning column cell to have WINNER_BG color.");
            assertEquals(StyleConfig.WINNER_TEXT, buttons[0][0].getForeground(), "Expected winning column cell to have WINNER_TEXT color.");
        }

        @Test
        @DisplayName("should return true for a winning diagonal from top-left to bottom-right")
        public void shouldReturnTrueForTopLeftToBottomRightDiagonal() {
            buttons[0][0].setText("X");
            buttons[1][1].setText("X");
            buttons[2][2].setText("X");

            assertTrue(ticTacToePanel.checkWinner(), "Expected checkWinner to return true for a winning diagonal from top-left to bottom-right.");

            assertEquals(StyleConfig.WINNER_BG, buttons[0][0].getBackground(), "Expected winning diagonal cell to have WINNER_BG color.");
            assertEquals(StyleConfig.WINNER_TEXT, buttons[0][0].getForeground(), "Expected winning diagonal cell to have WINNER_TEXT color.");
        }

        @Test
        @DisplayName("should return true for a winning diagonal from top-right to bottom-left")
        public void shouldReturnTrueForTopRightToBottomLeftDiagonal() {
            buttons[0][2].setText("O");
            buttons[1][1].setText("O");
            buttons[2][0].setText("O");

            assertTrue(ticTacToePanel.checkWinner(), "Expected checkWinner to return true for a winning diagonal from top-right to bottom-left.");

            assertEquals(StyleConfig.WINNER_BG, buttons[0][2].getBackground(), "Expected winning diagonal cell to have WINNER_BG color.");
            assertEquals(StyleConfig.WINNER_TEXT, buttons[0][2].getForeground(), "Expected winning diagonal cell to have WINNER_TEXT color.");
        }
    }
}