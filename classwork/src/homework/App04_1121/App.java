package homework.App04_1121;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;


public class App {




    static List<String> list;
    static Matcher matcher;
    static Pattern pattern;

    static String pathToApp = System.getProperty("user.dir")+"/src/homework/App04_1121/";

    static void hr(){
        System.out.println("-----------------------------------");

    }

    static List<String> readFile(String pathToFile) {

        list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToApp + pathToFile))) {
            String s;
            while ((s = br.readLine()) != null) {
                list.add(s);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        return list;

    }

    static boolean checkIP(String ip) {

        String[] array = ip.split("\\.");

        for (String str : array) {
            if (Integer.parseInt(str) > 255) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        list = readFile("file1.txt");

        /*
        sum
         */
        int sum = 0;
        for (String str : list) {

            pattern = Pattern.compile("[0-9]+");
            matcher = pattern.matcher(str);

            while (matcher.find()) {
                sum += Integer.parseInt(matcher.group());
            }
        }
        System.out.println(sum);
        hr();

        /*
        IP
         */
        for (String str : list) {
            pattern = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
            matcher = pattern.matcher(str);
            while (matcher.find()) {
                String tmp = matcher.group();
                if (checkIP(tmp)){
                    str = str.replaceAll(tmp,"[secret]");
                }
            }
            System.out.println(str);
        }
        hr();

        /*
        revers
        */
        list = readFile("file2.txt");
        for (String str: list) {
            StringBuilder tmp = new StringBuilder(str);
            str = tmp.reverse().toString();
            System.out.println(str);
        }
        hr();
        /*
        pryamyaa rech
        */
        list = readFile("file2.txt");
        for (String str: list) {

            int start=0,end;

            do{
                start = str.indexOf("\"",start);
                end = str.indexOf("\"",start+1);
                if(start>0 && end>start) {
                    System.out.println(str.substring(start, end+1));
                    start = end+1;
                }
            }while (start > 0);
            System.out.println(str);
        }
        hr();

        /*
        phone
        \+[0-9]{3}\-[0-9]{2}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}
         */
        list = readFile("file1.txt");
        ArrayList<String> phones = new ArrayList<>();
        int lenght=0;
        for (String str: list) {

            pattern = Pattern.compile("\\+[0-9]{1,3}\\-[0-9]{2}\\-[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}");
            matcher = pattern.matcher(str);
            while (matcher.find()) {
                phones.add(matcher.group());
                lenght++;
            }
        }
        String[] ph = new String[lenght];

        //ph = (String[]) phones.toArray();
        ph = phones.toArray(ph);

        phones.sort(String::compareTo);

        /*
        int i = 0;
        for (String tmp:phones) {
            ph[i++] = tmp;
        }
        */
        Arrays.sort(ph);

        for (String tmp: ph) {
            System.out.println(tmp);
        }
        hr();

        for (String tmp: phones) {
            System.out.println(tmp);
        }
        hr();

        /*
        translit
         */
        list = readFile("file2.txt");
        for (String str: list) {
            System.out.println(Translit.toTranslit(str));
        }
        hr();

    }
}
