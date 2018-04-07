import java.io.*;
import java.net.Socket;

public class AppClient {


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("192.168.0.122", 6666);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write("Hi");
        writer.newLine();
        writer.flush();


        BufferedReader reader= new BufferedReader(new InputStreamReader(is));

        String line = reader.readLine();
        while (line != null){
            if(line.isEmpty()){
                break;
            }
            System.out.println(line);
            line = reader.readLine();
        }

        socket.close();

    }

}
