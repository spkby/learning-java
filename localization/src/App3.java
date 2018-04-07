import java.util.Currency;
import java.util.Locale;
import java.util.Set;

public class App3 {

    public static void main(String[] args) {

        Currency currency = Currency.getInstance(Locale.getDefault());


        System.out.println(currency);

        Set<Currency> currencies = Currency.getAvailableCurrencies();

        for (Currency c:currencies
             ) {
            System.out.println(c.getDisplayName() + " " + c.getSymbol());

        }


    }


}
