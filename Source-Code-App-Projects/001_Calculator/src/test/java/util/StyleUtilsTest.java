package util;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Initialize StyleUtilsTest")
class StyleUtilsTest {
    @Test
    @DisplayName("Should Get MainFont")
    public void shouldGetMainFont() {
        assertNotNull(StyleUtils.getFont());
    }
}