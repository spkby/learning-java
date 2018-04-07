import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class ChatServerWorker implements Runnable {

    private Socket socket;
    private Map<Socket, BufferedWriter> clientMap;

    public ChatServerWorker(Socket socket, Map<Socket, BufferedWriter> clientMap) {
        this.socket = socket;
        this.clientMap = clientMap;
    }

    @Override
    public void run() {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            clientMap.put(socket, writer);

            String line = reader.readLine();
            while (line != null) {
                System.out.println(socket.getInetAddress().toString() + ":" + line);


                for (BufferedWriter wr : clientMap.values()){
                    wr.write(socket.getInetAddress()+": "+line);
                    wr.newLine();
                    wr.flush();
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public class ChatServer {

    private static Map<Socket, BufferedWriter> clientMap = new ConcurrentHashMap<>();

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

                executorService.submit(new ChatServerWorker(client,clientMap));

            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }
}
