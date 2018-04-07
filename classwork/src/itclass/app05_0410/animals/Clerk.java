package itclass.app05_0410.animals;

//Класс чиновник
public class Clerk extends Human {

    public Clerk(String name, int salary) {
        super(name, salary);
    }


    @Override
    public String toString(){
        return "Чиновник "
                + getName()
                + " с зарплатой, которую вам не нужно знать";
    }

}
