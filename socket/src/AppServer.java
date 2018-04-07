import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {


    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("0.0.0.0", 7777));

        Socket socket = serverSocket.accept();


        while (true) {

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();


            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                if (line.isEmpty()) {
                    break;
                }
                System.out.println(line);
                line = reader.readLine();
            }

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write("Hello");
            writer.write("World");
            writer.newLine();
            writer.flush();

            //socket.close();
        }
    }
}
