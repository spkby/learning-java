package itclass.app05_0410.animals;

/*
    Класс, представляющий собой некоторое животное
 */
public class Animal {

    //Поле "имя" - у каждого животного есть имя
    //Если поставить модификатор final перед полем,
    //то оно становится полем только для чтения
    //при этом его можно изменить только в конструкторе
    private final String name;

    //При создании объекта "животное" имя выдается "на всю жизнь"
    public Animal(String name) {
        this.name = name;
    }

    //Метод, возвращающий имя животного
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Животное по имени " + name;
    }
}
