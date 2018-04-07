package itclass.hm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


public class App {

    static List<String> list;
    static Matcher matcher;
    static Pattern pattern;

    public static List<String> readFile(String path) {

        list = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            String s;
            while((s=br.readLine())!=null){
                list.add(s);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        return list;

    }


    public static void main(String[] args) {


        list = readFile("c:\\s\\file1.txt");

        int sum=0;
        for (String str: list) {

            pattern = Pattern.compile("[0-9]+");
            matcher = pattern.matcher(str);

            while(matcher.find()){
                sum += Integer.parseInt(matcher.group());
            }
        }
        System.out.println(sum);


        pattern = Pattern.compile("[0-255]\\.[0-2][0-5][0-5]\\.[0-2][0-5][0-5]");


    }
}
