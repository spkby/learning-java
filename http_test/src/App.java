import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class App {

    public static void main(String[] args) throws MalformedURLException {

        //URL - объект, содержащий в себе ссылку на ресурс (Интернет-ресурс)
        URL url = new URL("https://google.com");

        System.out.println(url.getProtocol());
        System.out.println(url.getPort());
        System.out.println(url.getHost());

        try {
            //Отправка запроса по данному ресурсу и получение ответа в виде потока байтов
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);

            FileWriter fw = new FileWriter("google.html");

            int c = isr.read();

            while (c != -1) {
                //System.out.print((char)c);
                fw.write(c);
                c = isr.read();
            }

            fw.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
