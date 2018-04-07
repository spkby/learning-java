package itclass.app08_1910.generics;

public class Calc<T extends Number> implements Stats {

    private T[] digits;
    private int pointer;

    public Calc(T[] digits){
        this.digits = digits;
        sort();
    }

    @Override
    public double mean() {
        Number s = 0;
        for (int i = 0; i < digits.length; i++) {

            s = s.doubleValue() + digits[i].doubleValue();
        }
        return s.doubleValue() / digits.length;
    }

    void sort() {
        for (int i = digits.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (digits[j].doubleValue() > digits[j + 1].doubleValue()) {
                    T tmp = digits[j];
                    digits[j] = digits[j + 1];
                    digits[j + 1] = tmp;
                }
            }
        }
    }

    @Override
    public Number max() {
        return digits[digits.length-1];
    }

    @Override
    public Number min() {
        return digits[0];
    }
}
