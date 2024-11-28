import java.nio.*;
import java.nio.file.*;
import java.nio.channels.*;
import java.io.IOException;
import java.net.InetSocketAddress;

public class New_IO {
    public static void main(String[] args) throws IOException {
        // ========= FILES =========
        // handles files with better optimization
        Path path = Paths.get("./test/test3.txt");
        Path done = Files.createFile(path);

        Files.exists(Paths.get("./test3.txt"));
        Files.createFile(Paths.get("./test4.txt"));
        Files.readAllLines(Paths.get("./test5.txt"));
        Files.createDirectories(Paths.get("dir"));
        Files.writeString(done, "Hello World!!!!");
        Files.copy(Paths.get("./test6.txt"), Paths.get("./test/test6.txt"));
        Files.move(Paths.get("./test7.txt"), Paths.get("./test/test7.txt"));
        Files.delete(Paths.get("./test8.txt"));


        // ========= CHANNELS =========
        // FileChannel      -> used for managing files
        // SocketChannel    -> used for communicate through network (TCP, TCP/IP)
        FileChannel fileChannel = FileChannel.open(Paths.get(".test/test9.txt"), StandardOpenOption.READ);
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("example.com", 80));

        // ========= BUFFERS =========
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        CharBuffer charBuffer = CharBuffer.allocate(100);
        IntBuffer intBuffer = IntBuffer.allocate(5);
        FloatBuffer floatBuffer = FloatBuffer.allocate(5);
        DoubleBuffer doubleBuffer = DoubleBuffer.allocate(5);
        LongBuffer longBuffer = LongBuffer.allocate(5);

        // CHANNEL WITH BUFFER EXAMPLES

        // adding data into buffer
        byteBuffer.put(new byte[]{1, 2, 3});
        byteBuffer.put((byte) 'a');
        int bytesRead = fileChannel.read(byteBuffer);

        // reset pos and limitation values after adding data
        byteBuffer.flip();

        // read data
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
    }
}
