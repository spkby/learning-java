package itclass.app05_0410.figures;

/*
    Класс окружности
 */
public class Circle extends Figure {

    //Радиус
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Окружность с радиусом %.2f", radius);
    }
}
