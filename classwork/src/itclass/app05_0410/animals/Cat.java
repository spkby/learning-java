package itclass.app05_0410.animals;

/*
    Класс кошка
 */
public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Кошка по имени " + getName();
    }
}
