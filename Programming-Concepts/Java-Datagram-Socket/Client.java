import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter a message : ");
            byte[] bytes = reader.readLine().getBytes();

            InetAddress ip = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, ip, 1234);
            socket.send(packet);

            System.out.println("Sent the data!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
