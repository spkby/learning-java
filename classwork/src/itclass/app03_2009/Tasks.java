package itclass.app03_2009;

import java.util.Scanner;

public class Tasks {

    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();  //читаем строку
        int i = Integer.parseInt(s);    //извлекаем из строки число
        float f = Float.parseFloat(s);
        double d = Double.parseDouble(s);

        for (int j = 0; j < 10; j++) {
            System.out.printf("Введите число #%d = ", j+1);
            s = scanner.nextLine();  //читаем строку
            int k = Integer.parseInt(s);
        }

    }

    public static void task2() {

    }
}
