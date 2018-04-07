import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App3 {


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


        int sum = integerList.stream()
                .map(x -> x *2)
                .filter(x -> x >4)
                .reduce(0,(x,y) -> x +y);

        System.out.println(sum);

        sum = Files.lines(Paths.get("input.txt"))
                .map(Integer::parseInt)
                .map(x -> x *2)
                .filter(x -> x >4)
                .reduce(0,(x,y) -> x +y);
        System.out.println(sum);


    }

}
