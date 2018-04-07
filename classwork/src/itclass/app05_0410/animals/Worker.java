package itclass.app05_0410.animals;

//Класс работник
public class Worker extends Human {

    public Worker(String name, int salary) {
        super(name, salary);
    }

    //Публичный метод, возвращающий protected-поле
    //Такие методы, по возможности, не должны создаваться
    //"Семейные секреты не должны раскрываться"
    //(см. антипаттерн "Public Морозов")
    public int getSalary() {
        return this.salary;
    }
}
