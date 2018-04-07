package itclass.app03_2009;

//Класс Птица (app03_2009.Bird)
public class Bird {


    //Объявление поля (переменной) "имя" типа String
    String name; // = null - то есть, если мы
    //не проинициализиуем поле, то оно будет
    //иметь нулевое значение

    //Приватное поле - доступ только внутри класса
    private int year;

    //Метод класса, ничего не возращает, ничего не принимает
    void sayHello() {
        System.out.println("Привет, я " + name);
    }

    //Перегруженный метод sayHello с параметрами
    void sayHello(String to) {
        System.out.println("Привет, " + to + ", я " + name);
    }

    //Метод класса, принимающий параметры
    void rename(String name) {
        this.name = name;
    }

    //Конструктор без параметров
    public Bird() {
        name = "Птица";
        year = 0;
        birdCount++;
    }

    //Перегруженный конструктор
    Bird(String name) {
        this.name = name;
        year = 0;
        birdCount++;
    }

    //Публичный метод, который можно вызвать в любой точке программы
    public int getYear() {
        return year;
    }

    //Статическое поле (одна на весь класс,
    public static String birdKind = "Попугаи";
    //private static String birdKind = "Попугаи";

    //Количество птиц, созданных за всю работу программы
    private static int birdCount = 0;

    //Публичный статический метод
    public static int getBirdCount() {
        return birdCount;
    }

    //Объявление константы
    final int WINGS_COUNT = 2;

    //Статическая константа (одна на весь класс)
    static final int BIRD_TYPES = 4;

    //Пример метода с переменным количеством параметров
    public static int sum(int... array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static int sum2(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    //Метод toString используется там, где требуется вывести
    //строку (в частности, на консоль), а получаем объект
    @Override
    public String toString() {
        return "Птица " + name + ", " + year + " лет";
    }

    @Override
    public boolean equals(Object obj) {

        //Проверяем, не передан ли был пустой объект
        if (obj == null) {
            return false;
        }

        //Узнаем, сравниваем объект с тем же классом
        if (obj.getClass() != Bird.class) {
            return false;
        }

        //Значит obj является объектом класса app03_2009.Bird;
        Bird other = (Bird)obj;

        if (this.name.equals(other.name) && this.year == other.year) {
            return true;
        } else {
            return false;
        }

    }
}



