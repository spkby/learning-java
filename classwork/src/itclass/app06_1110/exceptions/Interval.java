package itclass.app06_1110.exceptions;

public class Interval {

    private final int a;
    private final int b;

    public Interval(int a, int b) {
        if (a == b) {
            throw new IllegalArgumentException("Точки интервала не могут быть равны");
        } else if (a > b) {
            throw new IllegalArgumentException("Точка а должна быть меньше точки b");
        }
        this.a = a;
        this.b = b;
    }

}
