import java.io.*;

public class Traditional_IO {
    public static void main(String[] args) {
        // ========= WRITER (CHAR STREAM) =========
        // used especially for writing text data
        try (Writer writer = new FileWriter("./test/test1.txt")) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.newLine();
            bufferedWriter.write("Hello World");

            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // ========= READER (CHAR STREAM) =========
        // used especially for reading text data
        try (Reader reader = new FileReader("./test/test1.txt")) {
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // ========= FILE OUTPUT STREAM (BYTE STREAM) =========
        // writing works with byte streams and is typically used for binary data (images, audio, etc.)
        try (OutputStream fileOut = new FileOutputStream("./test/test2.txt")) {
            fileOut.write('A');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // ========= FILE INPUT STREAM (BYTE STREAM) =========
        // writing works with byte streams and is typically used for binary data (images, audio, etc.)
        try (InputStream fileIn = new FileInputStream("./test/test2.txt")) {
            System.out.println(fileIn.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
