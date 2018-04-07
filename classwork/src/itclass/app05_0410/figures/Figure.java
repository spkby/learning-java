package itclass.app05_0410.figures;

/*
    Класс фигуры
    Абстрактный класс может содержать абстрактные методы
    и объекты данного класса нельзя создать
 */
public abstract class Figure {

    //Абстрактный метод не имеет никакого тела
    //Метод нужно обязательно переопределить в классе-наследнике

    //Площадь фигуры
    public abstract double getArea();

    //Периметр фигуры
    public abstract double getPerimeter();

    /*
    @Override
    public String toString() {
        return "Геометрическая фигура";
    }
    */
}
