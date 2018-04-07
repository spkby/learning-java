package homework.App03_0920.Task02;

import java.util.Scanner;

public class App02 {

    public static void main(String[] args) {

        byte filled, capacity;
        boolean ok;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter capacity of stomach");
        capacity = sc.nextByte();

        ok = false;

        do {
            System.out.println("Enter filled of stomach");
            filled = sc.nextByte();
            if(filled > capacity){
                System.out.println("Filled more than capacity. Try again");
            } else {
                ok = true;
            }
        } while (!ok);

        Duck duck = new Duck(capacity,filled);
        System.out.println(duck.toString());

        System.out.println("Enter bread");
        byte breadVolume = sc.nextByte();
        duck.eat(breadVolume);
        System.out.println(duck.toString());

        Duck duck2 = new Duck((byte)10,(byte)5);

        if(duck2.equals(duck)){
            System.out.println("Ducks similar");
        } else {
            System.out.println("Ducks not similar");
        }

    }


}
