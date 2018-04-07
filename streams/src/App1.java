import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App1 {


    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        List<Integer> integerList = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            integerList.add(Integer.parseInt(line));
            line = reader.readLine();
        }

        for (int i = 0; i < integerList.size(); i++) {
            Integer val = integerList.get(i);
            integerList.set(i, val * 2);
        }

        List<Integer> is = new ArrayList<>();

/*
        for (int i = 0; i < integerList.size(); i++) {
            if(integerList.get(i) > 4){
                is.add(integerList.get(i));
            }
        }
*/

        for (Integer i : integerList) {
            if (i > 4) is.add(i);
        }

        long sum = 0;
        for (Integer i : is) {
            sum += i;
        }

        System.out.println(integerList);
        System.out.println(is);
        System.out.println(sum);


    }

}
