import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App2 {


    public static void main(String[] args) {

        Properties properties = new Properties();

        //Locale.setDefault(Locale.forLanguageTag("en-US"));


        Scanner scanner = new Scanner(System.in);
        String input = null;


        do {
            printMenu();
            input = scanner.nextLine();
        } while (!input.equals("3"));
    }

    private static String getTranslation(String bundleName, String key){

        ResourceBundle bundle = ResourceBundle.getBundle(bundleName,Locale.getDefault());

//*/
      return bundle.getString(key);
        /*/
        try {
            return new String(bundle.getString(key).getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        //*/
    }

    private static void printMenu() {

        System.out.println("1 " + getTranslation("strings","connectMenu"));
        System.out.println("2 " + getTranslation("strings","fileMenu"));
        System.out.println("3 " + getTranslation("strings","exitMenu"));

    }

}
