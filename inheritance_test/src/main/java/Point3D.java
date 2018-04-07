//Композиция - добавление объектов одного класса
//внутрь объектов другого класса
public final class Point3D {

    private Double x;
    private Double y;
    private Double z;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }
}

class App {

    public static void main(String[] args) {
        Point3D p1 = new Point3D();
        p1.setX(10.0);
        System.out.println(p1.getX());

        double[] array = new double[] {2,1,3,4};
        System.out.println(new AverageCounter(array).getAverage());
        System.out.println(new StdDevCounter(array).getStdDev());

        runThread(new Thread());
        runThread(new Point());
    }

    public static void runThread(Thread thread) {
        thread.start();
    }

}

//Когда класс получает свою переменную (члена класса) извне
//например посредством конструктора (или сеттера)
//то такое отношение называется агрегацией
//Объект обычно не контролирует время жизни другого объекта
class AverageCounter {

    protected double[] array;

    public AverageCounter(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Массив не должен быть пустой");
        }
        this.array = array;
    }

    public double getAverage() {
        double sum = 0;
        for (double d : array) {
            sum += d;
        }
        return sum / array.length;
    }

}

class StdDevCounter extends AverageCounter {


    public StdDevCounter(double[] array) {
        super(array);
    }

    public double getStdDev() {

        double mean = getAverage();
        double std_sum = 0;
        for (double a : array) {
            std_sum += Math.pow(mean - a, 2);
        }

        return std_sum / array.length;
    }

}

interface StatsCounter {
    double getAverage();
    double getStdDev();
}