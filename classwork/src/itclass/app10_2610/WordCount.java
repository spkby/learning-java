package itclass.app10_2610;

import java.util.*;

public class WordCount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();

        //Слова из строки
        String[] words = line.split(" ");

        //Как посчитать наличие уникальных слов
        Set<String> uniqueWords = new TreeSet<>();
        uniqueWords.addAll(Arrays.asList(words));

        System.out.println("Все слова в тексте");
        for (String word : uniqueWords) {
            System.out.println(word);
        }

        //Как посчитать количество слов
        Map<String, Integer> counts = new TreeMap<>();
        for (String word : words) {
            if (!counts.containsKey(word)) {
                counts.put(word, 1);
            } else {
                int count = counts.get(word);
                counts.put(word, count + 1);
            }
        }

        System.out.println("Количество слов в тексте");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.printf("%s - %d\n", entry.getKey(), entry.getValue());
        }

    }

}
