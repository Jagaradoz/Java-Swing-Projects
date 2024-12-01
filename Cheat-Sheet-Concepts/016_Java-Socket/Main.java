import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

// Socket is an abstraction provided by operating systems to
// facilitate network communication and communicate via TCP
public class Main {
    public static void main(String[] args) throws Exception {
        // SERVER SOCKET
        // used for listen for incoming connections from client
        ServerSocket serverSocket = new ServerSocket(1234);


        // SOCKET
        // this two sockets are related but serves different purposes :
        // socketFromServer         -> created by server used for communicating to client socket
        // socketFromClient         -> created by client used for communicating to server socket
        Socket socketFromServer = serverSocket.accept();
        Socket socketFromClient = new Socket("localhost", 1234);

        // ============================= WRITER =============================
        // ==================================================================

        // BUFFERED WRITER
        // Efficient for writing text with buffering. Requires manual flush() and newLine() for line breaks.
        BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(socketFromServer.getOutputStream()));
        writer1.write("Hello World!");
        writer1.newLine();
        writer1.flush();

        // PRINT WRITER
        // Convenient for writing text with automatic line breaks and optional auto-flush.
        PrintWriter writer2 = new PrintWriter(socketFromServer.getOutputStream(), true);
        writer2.println("Hello World!");

        // DATA OUTPUT STREAM
        // Writes binary data (primitives, strings in UTF, etc.) in a structured format.
        DataOutputStream writer3 = new DataOutputStream(socketFromServer.getOutputStream()); 
        writer3.writeUTF("Hello");
        writer3.flush();
        
        // ============================= READER =============================
        // ==================================================================

        // BUFFERED READER
        // Reads text from an InputStream with buffering for improved efficiency.
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(socketFromServer.getInputStream()));
        String message = reader1.readLine();
        System.out.println(message);

        // DATA INPUT STREAM
        // Reads binary data (primitives, strings in UTF, etc.) in a structured format.
        DataInputStream reader2 = new DataInputStream(socket.getInputStream());
        String message = reader2.readUTF();
        System.out.println("Client : " + message);

        socketFromClient.close();
        socketFromServer.close();
        serverSocket.close();
    }
}
