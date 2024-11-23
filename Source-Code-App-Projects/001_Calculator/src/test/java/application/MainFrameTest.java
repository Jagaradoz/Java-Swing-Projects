package application;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Initialize MainFrameTest")
class MainFrameTest {
    private MainFrame mainFrame;

    @BeforeAll
    public void setUp() {
        mainFrame = new MainFrame();
    }

    @BeforeEach
    public void checkIfMainFrameIsNull() {
        if (mainFrame == null) throw new RuntimeException("Main Frame Is Null");
    }

    @Test
    @DisplayName("Should Have MainFrame")
    public void shouldHaveMainFrame() {
        assertNotNull(mainFrame);
    }

    @Test
    @DisplayName("Should Have ContentPane")
    public void shouldHaveContentPane() {
        assertNotNull(mainFrame.getContentPane());
    }

    @Test
    @DisplayName("Should Have ImageIcon")
    public void shouldHaveImageIcon() {
        assertNotNull(mainFrame.getIconImage());
    }

    @Test
    @DisplayName("Should Have JMenuBar")
    public void shouldHaveJMenuBar() {
        assertNotNull(mainFrame.getJMenuBar());
    }

    @Test
    @DisplayName("Should Have Title")
    public void shouldHaveTitle() {
        assertNotNull(mainFrame.getTitle());
    }

    @Test
    @DisplayName("Should Not Be Resizable")
    public void shouldNotBeResizable() {
        assertFalse(mainFrame.isResizable());
    }
}