import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App4 {


    public static void main(String[] args) throws IOException {


        long t1 = System.nanoTime();

        long l = Files.lines(Paths.get("input2.txt"))
                .parallel()
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .filter(s -> s.toLowerCase().startsWith("c"))
                .count();

        long t2 = System.nanoTime();

        System.out.println(l);
        System.out.println( (t2-t1) / 1e9);
    }

}
