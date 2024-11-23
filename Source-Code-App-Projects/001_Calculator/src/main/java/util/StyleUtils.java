package util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class StyleUtils {
    public static Font getFont() {
        Font mainFont;

        try {
            InputStream fontStream = StyleUtils.class.getResourceAsStream("/fonts/Itim-Regular.ttf");
            if (fontStream == null) {
                throw new IOException("Font resource not found.");
            }

            mainFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(16f);
        } catch (IOException | FontFormatException e) {
            mainFont = new Font("Arial", Font.PLAIN, 16);
        }

        return mainFont;
    }
}
