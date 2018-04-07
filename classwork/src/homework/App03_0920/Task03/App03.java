package homework.App03_0920.Task03;

import java.util.Scanner;

public class App03 {

    static boolean checkDenominator(int denominator){
        if(denominator == 0) {
            System.out.println("Denomirator cant be equal to zero");
            return false;
        }
        else return true;
    }

    static void printLine(){
        System.out.println("-----------------------------------------------");
    }

    public static void main(String[] args) {

        int num,den;

        Scanner sc = new Scanner(System.in);
        System.out.println("1st fraction");
        System.out.println("Enter numerator");
        num = sc.nextInt();

        do {
            System.out.println("Enter denominator");
            den = sc.nextInt();
        }while (!checkDenominator(den));

        Fraction fr1 = new Fraction(num,den);
        System.out.println("Fraction: "+fr1);

        System.out.println("2nd fraction");
        System.out.println("Enter numerator");
        num = sc.nextInt();

        do {
            System.out.println("Enter denominator");
            den = sc.nextInt();
        }while (!checkDenominator(den));

        Fraction fr2 = new Fraction(num,den);
        System.out.println("Fraction: "+fr2);


        printLine();
        fr1.red();

        System.out.println("Fractions reduction: "+fr1+" "+fr2);
        printLine();

        System.out.println("Addition: "+fr1+" + "+fr2+" = " + fr1.add(fr2));
        printLine();

        System.out.println("Subtraction: "+fr1+" - "+fr2+" = " + fr1.sub(fr2));
        printLine();

        System.out.println("Multiplication: "+fr1+" * "+fr2+" = " + fr1.mul(fr2));
        printLine();

        System.out.println("Division: "+fr1+" / "+fr2+" = " + fr1.div(fr2));
        printLine();

        fr1.red();
        System.out.println("Fractions reduction: "+fr1+" "+fr2);
        printLine();
    }

}
