import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(1234);) {

            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

            socket.receive(packet);

            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.print("Client : " + message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
