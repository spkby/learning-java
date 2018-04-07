package itclass.app08_1910.generics;

import itclass.app06_1110.interfaces.Animal;
import itclass.app06_1110.interfaces.Duck;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class App2 {


    public static void main(String[] args) {

        ObjectStack st = new ObjectStack(10);

        for (int i = 0; i < 5; i++) {
            st.push(i);
        }

        for (int i = 0; i < 5; i++) {
            st.push("Hello," + i);
        }

        while (st.size() != 0) {
            System.out.println(st.pop());
        }

        //Стек, который может хранить только строки
        Stack<String> s = new Stack<String>(10);
        //Стек, который может хранить только строки

        Stack<Animal> a = new Stack<Animal>(10);
        a.push(new Duck());
        //a.push("Hello");

        Pair<String, String> pair = new Pair<>("Hello", "Generics");
        //Pair<int, int> pair1 = new Pair<int, int>();

        //Каждый примитивный тип имеет у себя "классового" двойника
        Character c = new Character('a');
        Character.isDigit(c);
        Integer i = new Integer(1);
        Long l = new Long(1);
        Short s1 = new Short((short)1);
        Byte b = new Byte((byte)2);
        Float f = new Float(1.0f);
        Double d = new Double(-1.23);
        Boolean bl = Boolean.TRUE;

        getInteger(10);
        getInt(new Integer(11));

        //Классы-двойники примитивных типов можно использовать в обобщениях
        //В операторе new в знаке "<>" (diamond operator) можно не указывать типы обобщения
        Pair<String, Integer> pair1 = new Pair<>("BOEING", 747);


    }

    public static void getInteger(Integer i) {
        System.out.println(i);
    }

    public static void getInt(int i) {
        System.out.println(i);
    }

}
