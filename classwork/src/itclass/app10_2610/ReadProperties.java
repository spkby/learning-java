package itclass.app10_2610;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class ReadProperties {

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:/out.properties"));

            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                System.out.printf("%s -> %s\n", key, properties.get(key));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
