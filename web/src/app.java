import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class app {


    public static void main(String[] args) throws IOException {


        URL u = new URL("http://pila.by");

        System.out.println(u.getProtocol());
        System.out.println(u.getPort());
        System.out.println(u.getHost());


        InputStream stream = u.openStream();
        InputStreamReader reader = new InputStreamReader(stream);

        FileWriter writer = new FileWriter("pila.html");

        int c = reader.read();

        while (c != -1){
            writer.write(c);
            c = reader.read();
        }

        writer.flush();
        writer.close();

    }



}
