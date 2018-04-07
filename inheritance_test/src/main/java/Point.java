//Наследование от конкретного класса
public class Point extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("Point");
    }
}

//Наследование от интерфейса (наследование поведения)
class Figure implements Comparable<Figure> {

    @Override
    public int compareTo(Figure o) {
        return 0;
    }
}
