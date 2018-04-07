import java.util.Scanner;

public class MyApp {

    static Scanner scanner = new Scanner(System.in);

    public static String read(){
        String s = scanner.nextLine();
        return s;
    }

    public static void task1(){
        System.out.println("task1");
        System.out.println("enter array size");
        String s = read();
        int n = Integer.parseInt(s);
        float[] a = new float[n];
        for (int i=0;i<n;i++){
            System.out.println("enter element of array");
            s = read();
            a[i] = Integer.parseInt(s);
        }
        System.out.println("enter index");
        s = read();
        a[Integer.parseInt(s)] *= 1.1f;
        System.out.println("array");
        for(int i=0;i<n;i++){
         System.out.print(a[i]+"\t");
        }
        System.out.print("\n");
    }

    public static void task2() {
        System.out.println("enter task2");
        String s = read();
        int[] a = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            a[i] = Integer.parseInt(s.substring(i, i + 1));
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void task3(){
        System.out.println("task3");
        System.out.println("enter distance");
        String s = read();
        int dist = Integer.parseInt(s);
        System.out.println("enter time");
        s = read();
        int time = Integer.parseInt(s);
        float speed = dist*1.0f/time;
        System.out.println(speed);

    }

    public static float task4calc(int salary, float hours){
        if(salary < 8){
            return -3;
        }
        if (hours > 60) {
            return -2;
        }
        if (hours > 40){
            hours = (hours-40)*1.5f + 40;
            System.out.println("hours " + hours);
        }
        return salary*hours;
    }

    public static void task4(){
        System.out.println("task4");
        System.out.println("enter Dollars Per Hour");
        String s = read();
        int salary = Integer.parseInt(s);
        System.out.println("enter Hours in Week");
        s = read();
        int hours = Integer.parseInt(s);
        float amount = task4calc(salary,hours);

        if (amount == -3){
            System.out.println("error 3");
            return;
        }

        if (amount == -2){
            System.out.println("error 2");
            return;
        }
        System.out.println("Dollars in Week " + amount);
    }

    public static void main(String[] args ){
        task1();
        task2();
        task3();
        task4();

    }
}
