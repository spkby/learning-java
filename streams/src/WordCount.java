import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordCount {

    public static void main(String[] args) throws IOException {

        BufferedReader reader =
                new BufferedReader(new FileReader("input2.txt"));

        List<String> strings = new ArrayList<>();

        long time = System.nanoTime();
        String line = reader.readLine();
        while (line != null) {
            strings.add(line);
            line = reader.readLine();
        }

        long count = 0;
        for (String s : strings) {
            //count += s.split(" ").length;
            String[] strs = s.split(" ");
            for (String word : strs) {
                if (word.toLowerCase().startsWith("с")) {
                    count += 1;
                }
            }
        }
        long time2 = System.nanoTime();
        System.out.println(count);
        System.out.println((time2 - time) / 1e9);
    }

}
