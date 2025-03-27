import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        // Datagram Socket:
        // It used for sending and receiving UDP packets.
        DatagramSocket socketForServer = new DatagramSocket(1234);
        DatagramSocket socketForClient = new DatagramSocket();

        byte[] bytes = new byte[1024];

        // Datagram Packet:
        // It used for sending to server (4 arguments)
        DatagramPacket packetForSending = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("localhost"), 1234);
        socketForClient.send(packetForSending);
        System.out.println("SENT");

        // Datagram Packet:
        // It used for receiving from client (2 arguments)
        DatagramPacket packetForReceiving = new DatagramPacket(bytes, bytes.length);
        socketForServer.receive(packetForReceiving);
        String message = new String(packetForReceiving.getData(), 0, packetForReceiving.getLength());
        System.out.println("Client : " + message);


        socketForServer.close();
        socketForClient.close();
    }
}
