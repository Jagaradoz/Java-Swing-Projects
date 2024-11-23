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
    public void checkIfMainJFrameIsNull() {
        if (mainJFrame == null) throw new RuntimeException("MainJFrame is null");
    }

    @Test
    @DisplayName("Should get MainJFrame properly")
    public void shouldGetMainFrameProperly() {
        assertNotNull(mainJFrame);
    }

    @Test
    @DisplayName("Should get contentPane properly")
    public void shouldGetContentPaneProperly() {
        assertNotNull(mainJFrame.getContentPane());
    }

    @Test
    @DisplayName("Should get imageIcon properly")
    public void shouldGetImageIconProperly() {
        assertNotNull(mainJFrame.getIconImage());
    }

    @Test
    @DisplayName("Should get title properly")
    public void shouldGetTitleProperly() {
        assertEquals("Tic Tac Toe", mainJFrame.getTitle());
    }

    @Test
    @DisplayName("Should not be resizable properly")
    public void shouldNotBeResizableProperly() {
        assertFalse(mainJFrame.isResizable());
    }
}