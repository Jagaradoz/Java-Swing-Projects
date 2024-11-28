import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        Path inputPath = Paths.get("./test/input.txt");
        Path outputPath = Paths.get("./test/output.txt");

        // Converts output.txt to char stream and rewrite it
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8))) {
            writer.println("This is a sample text written to the output file.");
            writer.println("Using PrintWriter for character-based writing.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Converts input.txt to char stream and read it
        try (InputStreamReader reader = new InputStreamReader(Files.newInputStream(inputPath), StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Read from input file: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading and writing using NIO (new I/O)
        try {
            // Read using NIO
            String content = Files.readString(inputPath);
            System.out.println("Read using NIO (new I/O):");
            System.out.println(content);

            // Write using NIO
            Files.write(outputPath, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println("Content written to output file using NIO.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
