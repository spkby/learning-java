import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

public class UdpEchoClient {


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String line = null;


        DatagramSocket socket = new DatagramSocket();

        while (true) {
            line = scanner.nextLine();
            byte[] buffer = line.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, new InetSocketAddress("127.0.0.1", 5000));
            socket.send(packet);

            socket.receive(packet);
            String s = new String(buffer, 0, packet.getLength(), Charset.forName("UTF-8"));
            System.out.println(s);
        }
    }

}
