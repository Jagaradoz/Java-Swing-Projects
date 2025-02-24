package application;

import org.junit.jupiter.api.*;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Initialize CalculatorMenuBarTest")
class CalculatorMenuBarTest {
    private CalculatorMenuBar calculatorMenuBar;

    @BeforeAll
    public void setUp() {
        MainFrame mainFrame = new MainFrame();
        CalculatorPanel calculatorPanel = new CalculatorPanel();

        calculatorMenuBar = new CalculatorMenuBar(mainFrame, calculatorPanel);
    }

    @Test
    @DisplayName("Should Have ViewJMenu")
    public void shouldHaveViewJMenu() {
        assertNotNull(calculatorMenuBar.getViewJMenu());
    }

    @Test
    @DisplayName("Should Have HelpJMenu")
    public void shouldHaveHelpJMenu() {
        assertNotNull(calculatorMenuBar.getHelpJMenu());
    }

    @Test
    @DisplayName("ResizeIcon Works Properly")
    @SuppressWarnings("might be null")
    public void resizeIconWorksProperly() {
        URL lightModeUrl = getClass().getResource("/images/light-mode.png");
        URL nightModeUrl = getClass().getResource("/images/night-mode.png");


        ImageIcon lightIcon = null;
        ImageIcon darkIcon = null;

        if (lightModeUrl != null && nightModeUrl != null) {
            lightIcon = new ImageIcon(lightModeUrl);
            darkIcon = new ImageIcon(nightModeUrl);
        }


        assertNotNull(calculatorMenuBar.resizeIcon(Objects.requireNonNull(lightIcon)));
        assertNotNull(calculatorMenuBar.resizeIcon(Objects.requireNonNull(darkIcon)));
    }
}