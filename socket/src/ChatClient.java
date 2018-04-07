import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class ChatClient
{
    public static void main(String[] args) {

        try (Socket socket = new Socket("192.168.0.122", 9999)) {
            //try (Socket socket = new Socket("127.0.0.1", 7777)) {

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            new Thread(new ChatClientWorker(socket));

            Scanner sc = new Scanner(System.in);

            String line = sc.nextLine();
            while (line != null) {
                if (line.equals("exit")) {
                    break;
                }
                writer.write(line);
                writer.newLine();
                writer.flush();
                line = sc.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ChatClientWorker implements Runnable {

    private Socket socket;

    public ChatClientWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(socket.getInetAddress().toString() + ": " + line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}