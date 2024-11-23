package util;

import java.io.*;
import java.util.*;

public class DataUtils {
    public static ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();

        try (InputStream inputStream = DataUtils.class.getResourceAsStream("/files/words.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            // LOAD WORDS INTO ARRAY LIST
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line.toUpperCase());
            }
        } catch (Exception e) {
            System.out.println("Error loading words.txt: " + e.getMessage());
            list.addAll(Arrays.asList("PROGRAMMER", "LANGUAGE", "ELECTRICITY", "PSYCHOLOGY"));
        }

        return list;
    }
}
