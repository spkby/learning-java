package itclass.app06_1110.interfaces;

//Голубь
//Абстрактный класс не обязан реализовывать все методы интерфейса
//Если класс не хочет реализовывать некоторые методы интерфейса
//то он должен быть абстрактным
public abstract class Pigeon extends Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("Голубь летит");
    }

    @Override
    public double getMaxHeight() {
        return 600;
    }
}
