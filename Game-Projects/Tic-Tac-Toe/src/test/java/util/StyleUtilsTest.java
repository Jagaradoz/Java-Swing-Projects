package util;

import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Initialize StyleUtilsTest")
class StyleUtilsTest {
    @Test
    @DisplayName("Should get mainFont properly")
    public void shouldGetMainFont() {
        Font resultFont = StyleUtils.getFont();

        assertNotNull(resultFont, "The returned font should not be null");

        if (StyleUtils.class.getResourceAsStream("/fonts/Kanit-Regular.ttf") != null) {
            assertEquals("Kanit Regular", resultFont.getFontName(), "The font should be Itim if the custom font is available");
        } else {
            assertEquals("Arial", resultFont.getFontName(), "The font should fall back to Arial if the custom font is unavailable");
        }

        assertEquals(16, resultFont.getSize(), "The font size should be 16");
    }
}