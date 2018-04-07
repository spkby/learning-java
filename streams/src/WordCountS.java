import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordCountS {

    public static void main(String[] args) throws IOException {


        long time = System.nanoTime();

        long count = Files.lines(Paths.get("input2.txt"))
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .parallel()
                .filter(s -> s.toLowerCase().startsWith("с"))
                .count();

        long time2 = System.nanoTime();
        System.out.println(count);
        System.out.println((time2 - time) / 1e9);
    }


}
