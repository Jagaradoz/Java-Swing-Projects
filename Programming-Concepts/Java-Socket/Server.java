import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(1234)
        ) {
            Socket socket = serverSocket.accept();
            System.out.println("A new client connected");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String messageFromClient = reader.readLine();

            System.out.println("Client : " + messageFromClient);

            writer.write("Hello From Server!");
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
