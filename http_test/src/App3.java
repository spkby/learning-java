import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class App3 {

    public static void main(String[] args) throws MalformedURLException {

        //URL - объект, содержащий в себе ссылку на ресурс (Интернет-ресурс)
        URL url = new URL("https://api.fixer.io/latest");

        System.out.println(url.getProtocol());
        System.out.println(url.getPort());
        System.out.println(url.getHost());

        try {
            //Отправка запроса по данному ресурсу и получение ответа в виде потока байтов
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);


            int c = isr.read();

            while (c != -1) {
                System.out.print((char)c);
                c = isr.read();
            }

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
