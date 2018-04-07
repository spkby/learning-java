import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class App2 {

    public static void main(String[] args) throws MalformedURLException {

        //URL - объект, содержащий в себе ссылку на ресурс (Интернет-ресурс)
        URL url = new URL("ftp://ftp.byfly.by/byfly/Byfly_by1.8.chm");

        System.out.println(url.getProtocol());
        System.out.println(url.getPort());
        System.out.println(url.getHost());

        try {
            //Отправка запроса по данному ресурсу и получение ответа в виде потока байтов
            InputStream is = url.openStream();
            //InputStreamReader isr = new InputStreamReader(is);

            FileOutputStream fw = new FileOutputStream("Byfly_by1.8.chm");

            byte[] bytes = new byte[4096];

            int c = is.read(bytes);

            while (c != -1) {
                //System.out.print((char)c);
                fw.write(bytes, 0, c);
                fw.flush();
                c = is.read(bytes);
            }

            fw.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
