import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient


{


    public static void main(String[] args) {


        try (Socket socket = new Socket("192.168.0.122", 9999)) {
        //try (Socket socket = new Socket("127.0.0.1", 7777)) {

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sc = new Scanner(System.in);


            String line = sc.nextLine();
            while (line != null) {
                if (line.equals("exit")) {
                    break;
                }
                writer.write(line);
                writer.newLine();
                writer.flush();


                String response = reader.readLine();
                System.out.println("Answer " + response);
                line = sc.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
