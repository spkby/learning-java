import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class App {

    public static void main(String[] args) throws IOException {

        URL url = null;

        ObjectMapper mapper = new ObjectMapper();


        for (int i = 1; i <= 31; i++) {
            url = new URL(String.format("https://api.fixer.io/2000-01-%02d?base=USD&symbols=EUR", i));
            CurrencyRate rate = mapper.readValue(url, CurrencyRate.class);
            System.out.println(String.format("2000-01-%02d - %f", i, rate.getRates().get("EUR")));
        }





    }

}
