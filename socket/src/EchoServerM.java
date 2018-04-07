import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ClientWorker implements Runnable {

    private Socket socket;


    public ClientWorker(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {


            String line = reader.readLine();
            while (line != null) {
                System.out.println(socket.getInetAddress().toString() + ":" + line);
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


public class EchoServerM {


    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("0.0.0.0", 7777));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        while (true) {

            try (Socket client = serverSocket.accept()) {

                executorService.submit(new ClientWorker(client));

            } catch (IOException e) {
            }


        }
    }
}
