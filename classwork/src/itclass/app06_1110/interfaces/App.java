package itclass.app06_1110.interfaces;

public class App {

    public static void main(String[] args) {

        //Получение ссылки на объект через его класс,
        //или через родительский класс
        Duck duck1 = new Duck();
        Bird duck2 = new Duck();
        Animal duck3 = new Duck();
        //Мы можем ссылаться на объект через чего интерфейс
        Flyable flyingAnimal = new Duck();
        //Через эту ссылку мы можем вызвать только те методы,
        //объявленные в данном интерфейсе
        flyingAnimal.fly();
        System.out.println(flyingAnimal.getMaxSpeed());
        System.out.println(flyingAnimal.getMaxHeight());

        RoomPigeon pigeon1 = new RoomPigeon();
        GrayPigeon pigeon2 = new GrayPigeon();
        Pigeon pigeon3 = new RoomPigeon();
        Flyable pigeon4 = new RoomPigeon();
        System.out.println(pigeon1.getMaxSpeed());
        System.out.println(pigeon2.getMaxSpeed());
        System.out.println(pigeon3.getMaxSpeed());
        System.out.println(pigeon4.getMaxSpeed());

        //Все варианты использования объекта класса
        Duck duck4 = new Duck();
        Flyable duck5 = new Duck();
        Swimable duck6 = new Duck();

        //Если ссылаемся на класс, реализующий несколько интерфейсов
        //то можем вызвать любой из методов интерфейсов
        duck4.swim();
        duck4.fly();

        //Иначе нам доступны только методы интерфейса
        duck5.fly();
        duck6.swim();

        Flyable flyableAnimal2 = new Duck();
        //Объекты интерфейсов можно приводить к классам
        //или другим интерфейсам, в случае несовпадения
        //типов мы получим ошибку
        Duck duckReference = (Duck)flyableAnimal2;
        Swimable swimableReference = (Swimable)flyableAnimal2;

    }

}
