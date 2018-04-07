package itclass.app08_1910.generics;

import java.util.Scanner;

public class App3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("enter size: ");
        int size = sc.nextInt();

        Double[] digits = new Double[size];

        System.out.println("enter digits");
        for (int i = 0; i < size; i++) {
            digits[i] = sc.nextDouble();
        }
        Calc t = new Calc(digits);

        System.out.println("среднее "+t.mean());
        System.out.println("мин "+t.min());
        System.out.println("макс "+t.max());
    }

}
