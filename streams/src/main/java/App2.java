import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App2 {


    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader("input2.txt"));

        List<String> strings = new ArrayList<>();


        long time = System.nanoTime();

        String line = reader.readLine();
        while (line != null) {
            strings.add(line);
            line = reader.readLine();
        }

        long count = 0;
        for (String str : strings) {

            //count += str.split(" ").length;

            String[] strs = str.split(" ");
            for (String word : strs) {
                if (word.toLowerCase().startsWith("c")) {
                    count++;
                }
            }

        }
        long time2 = System.nanoTime();
        System.out.println(count);
        System.out.println((time2 - time) / 1e9);

    }

}
