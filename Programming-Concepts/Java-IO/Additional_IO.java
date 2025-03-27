import java.io.*;

public class Additional_IO {
    public static void main(String[] args) {
        // Print Writer:
        // It can write any data types (int float boolean ...) with formatted text (print , println , printf).
        // - .flush()         -> used for clear data from buffer (.close() is not required)
        try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"), true)) {
            pw.println("Hello, File!");
            pw.println("This is an example of writing to a file using PrintWriter.");
            pw.printf("Formatted number: %.2f%n", 123.456);
            pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Input Stream Reader:
        // It is a bridge between byte streams and character streams.
        // - System.in is byte stream and be converted into char stream
        InputStreamReader consoleReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(consoleReader);
    }
}