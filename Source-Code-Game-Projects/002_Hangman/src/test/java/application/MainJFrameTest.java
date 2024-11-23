package application;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Initialize MainJFrameTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainJFrameTest {
    private MainJFrame mainJFrame;

    @BeforeAll
    public void setUp() {
        mainJFrame = new MainJFrame();
    }

    @BeforeEach
    public void checkIfMainFrameIsNull() {
        if (mainJFrame == null) throw new RuntimeException("Main Frame Is Null");
    }

    @Test
    @DisplayName("Should Have MainFrame")
    public void shouldHaveMainFrame() {
        assertNotNull(mainJFrame);
    }

    @Test
    @DisplayName("Should Have ContentPane")
    public void shouldHaveContentPane() {
        assertNotNull(mainJFrame.getContentPane());
    }

    @Test
    @DisplayName("Should Have ImageIcon")
    public void shouldHaveImageIcon() {
        assertNotNull(mainJFrame.getIconImage());
    }

    @Test
    @DisplayName("Should Have Title")
    public void shouldHaveTitle() {
        assertNotEquals("",mainJFrame.getTitle());
    }

    @Test
    @DisplayName("Should Not Be Resizable")
    public void shouldNotBeResizable() {
        assertFalse(mainJFrame.isResizable());
    }
}