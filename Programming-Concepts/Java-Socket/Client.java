import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 1234);
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter a message to a server : ");
            String messageToServer = scanner.nextLine();

            writer.write(messageToServer);
            writer.newLine();
            writer.flush();

            String messageFromServer = reader.readLine();
            System.out.println("Server : " + messageFromServer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
