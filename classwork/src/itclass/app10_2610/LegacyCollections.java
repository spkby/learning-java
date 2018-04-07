package itclass.app10_2610;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LegacyCollections {

    public static void main(String[] args) {

        //Dictionary - устаревший класс, необходимо его заменять Map (HashMap)
        Dictionary<String, String> dictionary = new Hashtable<>();
        dictionary.put("Петров", "+375296546565");
        dictionary.put("Иванов", "+375296546567");

        //Vector - расширяемый массив (устаревший), заменять на ArrayList
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(0, 2);
        for (Integer i : vector) {
            System.out.println(i);
        }

        Stack<Integer> integerStack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            integerStack.push(i);
        }
        while (!integerStack.isEmpty()) {
            System.out.println(integerStack.pop());
        }

        //Коллекция, предназначенная для хранения свойств (настроек) в
        //виде пар ключ-значение (ключи - String)
        Properties properties = new Properties();
        properties.put("width", "800");
        properties.put("height", "600");
        try {
            properties.store(new FileWriter("C:/out.properties"), "");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
