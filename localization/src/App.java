import sun.rmi.server.LoaderHandler;

import java.util.Locale;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {

        Locale locale = Locale.getDefault();
        System.out.println(locale);

        /*locale.setDefault(Locale.forLanguageTag("by-BY"));

        Locale[] locales = Locale.getAvailableLocales();
        for (Locale loc:locales) {
            System.out.println(loc);
        }*/

        Scanner scanner = new Scanner(System.in);

        Locale en = Locale.ENGLISH;
        Locale ru = Locale.forLanguageTag("ru");

        String input = null;

        Locale.setDefault(en);

        do{

            if(en.getLanguage().equals(locale.getDefault().getLanguage())){
                printMenuEnglish();
            } else if(ru.getLanguage().equals(locale.getDefault().getLanguage())) {
            printMenuRussian();
            }

            input = scanner.nextLine();
        }while (!input.equals("3"));

    }

    private static void printMenuRussian(){

        System.out.println("1 Подключиться к серверу");
        System.out.println("2 Чтение из файла");
        System.out.println("3 Выход");

    }

    private static void printMenuEnglish(){

        System.out.println("1 Connect to server");
        System.out.println("2 Read from a file");
        System.out.println("3 Exit");

    }

}
