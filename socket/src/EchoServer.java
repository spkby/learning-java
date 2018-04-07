import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {


    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("0.0.0.0", 7777));
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {


            try (Socket client = serverSocket.accept()) {

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));


                String line = reader.readLine();
                while (line != null) {
                    System.out.println(client.getInetAddress().toString() + ":" + line);
                    writer.write(line);
                    writer.newLine();
                    writer.flush();
                    line = reader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
