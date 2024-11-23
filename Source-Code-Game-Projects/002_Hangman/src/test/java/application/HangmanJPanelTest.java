package application;

import org.junit.jupiter.api.*;

import javax.swing.*;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Initialize HangmanJPanelTest")
class HangmanJPanelTest {
    private HangmanJPanel hangmanJPanel;

    @BeforeEach
    public void setUp() {
        hangmanJPanel = new HangmanJPanel();
    }

    @Test
    @DisplayName("CheckWin Works Properly")
    public void checkWinWorksProperly() {
        String wordToGuess = "APPLE";

        List<Character> guesses = hangmanJPanel.getGuesses();

        hangmanJPanel.setWord(wordToGuess);

        guesses.add('A');
        guesses.add('E');
        hangmanJPanel.checkWin();

        assertEquals("Wrong: 0/6", hangmanJPanel.getWrongLabel().getText());
        assertFalse(hangmanJPanel.isGameFinished());

        guesses.add('L');
        guesses.add('P');
        hangmanJPanel.checkWin();

        assertEquals("Awesome! Perfectly win.", hangmanJPanel.getWrongLabel().getText());
        assertTrue(hangmanJPanel.isGameFinished());
    }

    @RepeatedTest(10)
    @DisplayName("CheckLetter Works Properly")
    public void checkLetterWorksProperly() {
        String wordToGuess = "APPLE";

        List<JLabel> wordLabels = hangmanJPanel.getWordLabels();
        List<Character> guesses = hangmanJPanel.getGuesses();

        hangmanJPanel.setWord(wordToGuess);
        hangmanJPanel.resetWordLabels();

        guesses.add('A');
        guesses.add('E');

        hangmanJPanel.checkLetter();

        assertEquals("A", wordLabels.get(0).getText(), "The first letter should be 'A'");
        assertEquals("_", wordLabels.get(1).getText(), "The second letter should remain '_'");
        assertEquals("_", wordLabels.get(2).getText(), "The third letter should remain '_'");
        assertEquals("_", wordLabels.get(3).getText(), "The fourth letter should remain '_'");
        assertEquals("E", wordLabels.get(4).getText(), "The fifth letter should be 'E'");
    }


    @Test
    @DisplayName("CheckWrong Works Properly")
    public void checkWrongWorksProperly() {
        String wordToGuess = "APPLE";

        List<Character> guesses = hangmanJPanel.getGuesses();

        hangmanJPanel.setWord(wordToGuess);

        guesses.add('B');
        guesses.add('C');
        guesses.add('D');
        guesses.add('F');
        hangmanJPanel.checkWrong();

        assertEquals("Wrong: 4/6", hangmanJPanel.getWrongLabel().getText());
        assertFalse(hangmanJPanel.isGameFinished());

        guesses.add('G');
        guesses.add('H');
        hangmanJPanel.checkWrong();

        assertEquals("You lost. Wanna try again?", hangmanJPanel.getWrongLabel().getText());
        assertTrue(hangmanJPanel.isGameFinished());
    }

    @Test
    @DisplayName("UpdateImage Works Properly")
    public void updateImageShouldSetImageIconCorrectly() {
        String testImagePath = "/images/1.png";
        ImageIcon expectedIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(testImagePath)));

        hangmanJPanel.updateImage(testImagePath);

        assertNotNull(hangmanJPanel.getImageLabel().getIcon(), "ImageIcon should not be null after update");
        assertEquals(expectedIcon.getImage(), ((ImageIcon) hangmanJPanel.getImageLabel().getIcon()).getImage(),
                "The ImageIcon set in imageLabel should match the expected icon");
    }

    @Test
    @DisplayName("TextPanel Should Not Be Null")
    public void textPanelShouldNotBeNull() {
        assertNotNull(hangmanJPanel.getTextPanel(), "TextPanel should not be null");
    }

    @Test
    @DisplayName("KeyboardPanel Should Not Be Null")
    public void keyboardPanelShouldNotBeNull() {
        assertNotNull(hangmanJPanel.getKeyBoardPanel(), "KeyboardPanel should not be null");
    }

    @Test
    @DisplayName("WordPanel Should Not Be Null")
    public void wordPanelShouldNotBeNull() {
        assertNotNull(hangmanJPanel.getWordPanel(), "WordPanel should not be null");
    }

    @Test
    @DisplayName("WrongLabel Should Not Be Null")
    public void wrongLabelShouldNotBeNull() {
        assertNotNull(hangmanJPanel.getWrongLabel(), "WrongLabel should not be null");
    }

    @Test
    @DisplayName("ImageLabel Should Not Be Null")
    public void imageLabelShouldNotBeNull() {
        assertNotNull(hangmanJPanel.getImageLabel(), "ImageLabel should not be null");
    }

    @Test
    @DisplayName("ImagePanel Should Not Be Null")
    public void imagePanelShouldNotBeNull() {
        assertNotNull(hangmanJPanel.getImagePanel(), "ImagePanel should not be null");
    }
}