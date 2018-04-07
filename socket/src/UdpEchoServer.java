import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.Charset;

public class UdpEchoServer {


    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket(5000);
        byte[] buffer = new byte[4096];
        DatagramPacket packet;

        while (true) {
            packet = new DatagramPacket(buffer,4096);
            socket.receive(packet);
            String s = new String(buffer,0,packet.getLength(), Charset.forName("UTF-8"));
            System.out.println(s);

            byte[] received = new byte[4096];
            packet = new DatagramPacket(received, received.length,packet.getAddress(),5000);
            socket.send(packet);
        }
    }

}
