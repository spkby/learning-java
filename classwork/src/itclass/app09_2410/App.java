package itclass.app09_2410;

import itclass.app05_0410.figures.Circle;
import itclass.app05_0410.figures.Figure;
import itclass.app05_0410.figures.Rectangle;
import itclass.app05_0410.figures.Triangle;

import java.security.SecureRandom;
import java.security.Security;
import java.util.*;

public class App {

    public static void main(String[] args) {

        //Collection - интерфейс, который реализует все коллекции


        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            int i = iterator.next();
            System.out.println(i);
        }

        // foreach
        /*
        куда будут извлекать : та коллекция из которой будут извлекать

        объект после : обязан реализовать интерфейс Iterable
         */
        for(Integer i : list){
            System.out.println(i);
        }


        // Set - коллекции в которой все элементы уникальны


        //
        System.out.println("Hashset");
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(1);
        for(Integer i : hashSet){
            System.out.println(i);
        }


        System.out.println("TreeSet");
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            treeSet.add(i % 10);
        }

        for(Integer i : treeSet){
            System.out.println(i);
        }


        // headSet
        System.out.println("TreeSet.headSet");
        for(Integer i : treeSet.headSet(5)){
            System.out.println(i);
        }


        /*
         List - интерфейс коллекций, который все элементы располагаются друг за другом и точно знают
         расположение следующего и предыдущего элемента
          */
        System.out.println("List");

        //ArrayList - это List построенный как массив
        List<Integer> integers1 = new ArrayList<>();

        // LinkedList -
        LinkedList<Integer> integers2 = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            integers1.add(i%5);
        }

        System.out.println("ArrayList");
        for(Integer i : integers1){
            System.out.println(i);
        }


        // случайные чила
        double random = Math.random();

        Random rnd = new Random();
        rnd.nextInt(100); // до этого числа от 0 до 100 (не включая)

        // защищен
        SecureRandom secureRandom = new SecureRandom();

        // очередь с приоритетами
        /*
        элементы добавляются в произвольном порядке, а извлекаются в порядке возрастания
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 50; i++) {
            queue.add(rnd.nextInt(100));
        }
        System.out.println("Содержимое PrQ");
        for (Integer i: queue) {
            System.out.println(i);
        }
        System.out.println("Содержимое PrQ извеченное спомощью " +
                "стадартныфх операций");

        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }

        /*
        по возрастанию
        PriorityQueue<Figure> figurePriorityQueue = new PriorityQueue<>(new FigureAreaComparator());
        */

        // reversed() по убыванию
        PriorityQueue<Figure> figurePriorityQueue = new PriorityQueue<>(new FigureAreaComparator().reversed());


        for (int i = 0; i < 50; i++) {
            int figures = rnd.nextInt(3);
            switch (figures){
                case 0:
                    double radius = rnd.nextInt(100)+1;
                    figurePriorityQueue.add(new Circle(radius));
                    break;
                case 1:
                    double a = rnd.nextInt(100)+1;
                    double b = rnd.nextInt(100)+1;
                    figurePriorityQueue.add(new Rectangle(a,b));
                    break;
                case 2:
                    a = rnd.nextInt(100)+1;
                    b = rnd.nextInt(100)+1;
                    double c = rnd.nextInt(100)+1;
                    try{
                        figurePriorityQueue.add(new Triangle(a,b,c));
                    }catch (Exception e){
                        radius = rnd.nextInt(100)+1;
                        figurePriorityQueue.add(new Circle(radius));
                    }
                    break;
            }

        }
        System.out.println("--------------------------------");
        System.out.println("очередь с фигурками");
        for (Figure f: figurePriorityQueue) {
            System.out.println(f + " area " + f.getArea());
        }

        System.out.println("--------------------------------");
        System.out.println("очередь с фигурками c сортировкой");
        while (figurePriorityQueue.isEmpty()) {
            Figure a = figurePriorityQueue.poll();
            System.out.println(a + " area " + a.getArea());
        }

    }

}
