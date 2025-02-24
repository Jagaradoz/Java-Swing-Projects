package util;

import org.junit.jupiter.api.*;

import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Initialize DataUtilsTest")
class DataUtilsTest {
    @Test
    @DisplayName("Should Get Data")
    public void shouldGetData() {
        ArrayList<String> data = DataUtils.getData();
        URL fileUrl = DataUtils.class.getResource("/files/words.txt");

        assertNotNull(data, "getData() should return non-null ArrayList");
        assertNotNull(fileUrl, "words.txt file should exist in resources/files directory");
    }
}