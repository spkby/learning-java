package itclass.app05_0410.animals;


public class App {

    public static void main(String[] args) {

        Animal a1 = new Animal("Кеша");
        System.out.println(a1);
        System.out.println(a1.getName());

        Animal a2 = new Animal("Кузя");
        System.out.println(a2);
        System.out.println(a2.getName());

        Human h1 = new Human("Василий", 100);
        System.out.println(h1.salary);
        System.out.println(h1.getName());


        Worker w1 = new Worker("Петр", 200);
        System.out.println(w1);
        System.out.println(w1.getSalary());
        System.out.println(w1.getName());

        Clerk c1 = new Clerk("Иван Васильевич", 5000);
        System.out.println(c1);
        //System.out.println(c1.getSalary());
        System.out.println(c1.getName());

        Animal a = a1;
        //Несмотря на то, что все объекты являются объектами различных классов
        //все они наследуются от класса Animal, и по ссылке "a" класса Animal
        //мы можем обращаться только к методам и полям класса Animal
        //System.out.println(a.getName());
        System.out.println(a);
        a = a2;
        System.out.println(a);
        a = h1;
        System.out.println(a);
        //System.out.println(a.getName());
        a = w1;
        System.out.println(a);
        a = c1;
        System.out.println(a);

        a = new Cat("Маркиза");
        System.out.println(a);

        Cat cat1 = new Cat("Дарья");
        System.out.println(cat1);

        //Нельзя присвоить ссылке значения родительского класса
        //или класса-соседа по иерархии
        //(можно присвоить только тот же класс или класс-наследник)
        //cat1 = w1;
        //w1 = h1;

        //В данном случае мы используем приведение типов
        //1. Если в "a" лежит объект типа кошка, значит все пройдеит без ошибок
        //и ссылка cat1 будет указывать на кошку
        //2. Если в "a" лежит объект другого типа, возникнет ошибка (ClassCastException)
        cat1 = (Cat)a;
        //P.S. Приведение работает только тогда, когда мы пытаемся привести объект родительского
        //класса к объекту дочернего класса
        //cat1 = (Cat)w1;

    }

}
