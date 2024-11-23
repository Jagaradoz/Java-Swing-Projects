package application;

import config.LayoutConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Initialize SudokuPanelTest")
class SudokuPanelTest {
    private SudokuPanel sudokuPanel;

    @BeforeEach
    public void setUp() {
        sudokuPanel = new SudokuPanel();
    }

    @Test
    @DisplayName("Should start game properly")
    public void shouldStartGameProperly() {
        assertFalse(sudokuPanel.isGameFinished());
        assertEquals(0, sudokuPanel.getWrongCount());
        assertEquals(0, sudokuPanel.getElapsedTime());

        assertEquals("Wrong : 0/3", sudokuPanel.getWrongLabel().getText());
        assertEquals("Continuing...", sudokuPanel.getCenterLabel().getText());
        assertEquals("00:00", sudokuPanel.getTimerLabel().getText());
        assertTrue(sudokuPanel.getTimer().isRunning());
    }

    @Test
    @DisplayName("Should check win game properly")
    public void ShouldCheckWinGameProperly() {
        int[][] correctedBoard = sudokuPanel.getCorrectedBoard();

        for (int i = 0; i < correctedBoard.length; i++) {
            for (int j = 0; j < correctedBoard[i].length; j++) {
                sudokuPanel.getGameBoard()[i][j] = correctedBoard[i][j];
            }
        }

        sudokuPanel.checkWinGame();

        assertTrue(sudokuPanel.isGameFinished());
        assertEquals("You win!", sudokuPanel.getCenterLabel().getText());
        assertFalse(sudokuPanel.getTimer().isRunning());
    }

    @Test
    @DisplayName("Should check lose game properly")
    public void ShouldCheckLoseGameProperly() {
        sudokuPanel.setWrongCount(1);
        sudokuPanel.checkLoseGame();

        assertFalse(sudokuPanel.isGameFinished());
        assertEquals("Continuing...", sudokuPanel.getCenterLabel().getText());
        assertTrue(sudokuPanel.getTimer().isRunning());
        sudokuPanel.setWrongCount(1);

        sudokuPanel.setWrongCount(2);
        sudokuPanel.checkLoseGame();

        assertFalse(sudokuPanel.isGameFinished());
        assertEquals("Continuing...", sudokuPanel.getCenterLabel().getText());
        assertTrue(sudokuPanel.getTimer().isRunning());
        sudokuPanel.setWrongCount(1);

        sudokuPanel.setWrongCount(3);
        sudokuPanel.checkLoseGame();

        assertTrue(sudokuPanel.isGameFinished());
        assertEquals("You lost!", sudokuPanel.getCenterLabel().getText());
        assertFalse(sudokuPanel.getTimer().isRunning());
    }

    @Test
    @DisplayName("Should generate gameBoard properly")
    public void shouldGenerateGameBoardProperly() {
        int[][] gameBoard = sudokuPanel.getGameBoard();

        int disappearBlocks = 0; // SHOULD BE 40
        for (int[] rows : gameBoard) {
            for (int block : rows) {
                if (block == 0) disappearBlocks++;
            }
        }

        assertEquals(40, disappearBlocks);
    }

    @Test
    @DisplayName("Should generate correctedBoard properly")
    public void shouldGenerateCorrectedBoardProperly() {
        int[][] correctedBoard = sudokuPanel.getCorrectedBoard();

        boolean hasNumberEveryBlock = true;

        for (int[] rows : correctedBoard) {
            for (int block : rows) {
                if (block == 0) {
                    hasNumberEveryBlock = false;
                    break;
                }
            }
        }

        assertTrue(hasNumberEveryBlock);
    }

    @Test
    @DisplayName("Should get SettingPanel")
    public void shouldGetSettingPanel() {
        assertNotNull(sudokuPanel.getSettingPanel());
    }

    @Test
    @DisplayName("Should get TextPanel")
    public void shouldGetTextPanel() {
        assertNotNull(sudokuPanel.getTextPanel());

    }

    @Test
    @DisplayName("Should get BoardPanel")
    public void shouldGetBoardPanel() {
        assertNotNull(sudokuPanel.getBoardPanel());

    }
}