package homework.App03_0920.Task03;

public class Fraction {

    int numerator, denominator;

    Fraction(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        if(denominator < 0){
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    @Override
    public String toString(){
        if(denominator == 1){
            return String.format("%d",numerator);
        } else if(numerator>denominator){
            int whole, numer;
            whole = numerator / denominator;
            numer = Math.abs(numerator % denominator);
            return  whole+" "+numer+"/"+denominator;
        }
        else return numerator+"/"+denominator;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || obj.getClass() != Fraction.class){
            return false;
        }
        Fraction fr = (Fraction)obj;
        if(fr.numerator == this.numerator && fr.denominator == this.denominator){
            return true;
        }
        else {
            return false;
        }
    }

    public Fraction add(Fraction fraction){
        if(fraction == null){
            System.err.println("2nd fraction is null");
            return null;
        } else {

        int numer = this.numerator*fraction.denominator + this.denominator * fraction.numerator;
        int denom = fraction.denominator * this.denominator;

        return new Fraction(numer,denom);
        }
    }

    public Fraction sub(Fraction fraction){
        if(fraction == null){
            System.err.println("2nd fraction is null");
            return null;
        } else {

            int numer = this.numerator*fraction.denominator - this.denominator * fraction.numerator;
            int denom = fraction.denominator * this.denominator;

            return new Fraction(numer,denom);
        }
    }

    public Fraction mul(Fraction fraction){
        if(fraction == null){
            System.err.println("2nd fraction is null");
            return null;
        } else {

            int numer = this.numerator * fraction.numerator;
            int denom = fraction.denominator * this.denominator;

            return new Fraction(numer,denom);
        }
    }

    public Fraction div(Fraction fraction){
        if(fraction == null){
            System.err.println("2nd fraction is null");
            return null;
        } else {

            int numer = this.numerator * fraction.denominator;
            int denom = fraction.denominator * this.denominator;

            return new Fraction(numer,denom);
        }
    }

    public Fraction red(){
        int deleter = NOD(numerator, denominator);
        return new Fraction(numerator/deleter, denominator/deleter);
    }

    static int NOD(int a, int b){
        while (a != 0 && b != 0){
            if (a >= b){
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return Math.abs(a + b);
    }
}
