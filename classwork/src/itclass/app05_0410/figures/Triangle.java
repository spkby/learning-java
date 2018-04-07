package itclass.app05_0410.figures;

/*
    Класс треугольника
    final - от класса нельзя наследоваться
 */
public final class Triangle extends Figure {

    //длины сторон треугольника
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {

        if((a+b <= c) || (c+b <= a) || (a+c <= b)){
            throw new IllegalArgumentException("bad triangel");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public String toString() {
        return String.format("Треугольник со сторонами %.2f, %.2f, %.2f", a, b, c);
    }
}

/*
От final-класса нельзя наследоваться
class SuperTriangle extends Triangle {

}
*/
