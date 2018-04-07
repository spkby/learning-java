package itclass.app06_1110.interfaces;

/*
    Класс "Утка", реализуюший интерфейс "Возможность летать"
    Реализация интерфейсов объявляется через ключевое слово
    implements
    Можно реализовать несколько интерфейсов, перечислив их
    через запятую после слова implements
 */

public class Duck extends Bird implements Flyable, Swimable {

    //При реализации интерфейса мы
    //мы должны реализовать все его методы
    @Override
    public void fly() {
        System.out.println("Утка летит");
    }

    @Override
    public double getMaxHeight() {
        return 500;
    }

    @Override
    public double getMaxSpeed() {
        return 10;
    }

    @Override
    public void swim() {
        System.out.println("Утка плывет");
    }
}
