package itclass.app08_1910.generics;

//Обобщенный класс, который может принимать другой тип (класс, интерфейс, перечисление)
//в качестве параметра
public class Stack<T> {

    private T[] array;
    private int pointer;

    public Stack(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Стек должен быть положительного размера");
        }
        this.array = (T[])new Object[size];
        this.pointer = 0;
    }

    //Добавление элемента в стек
    public void push(T c) {
        if (pointer == array.length) {
            throw new IllegalStateException("Стек переполнен");
        }
        this.array[pointer] = c;
        pointer++;
    }

    //Забрать элемент из стека
    public T pop() {
        if (pointer == 0) {
            throw new IllegalStateException("Стек пуст");
        }
        this.pointer--;
        return this.array[pointer];
    }

    //Забрать элемент из стека (без удаления)
    public T peek() {
        if (pointer == 0) {
            throw new IllegalStateException("Стек пуст");
        }
        return this.array[pointer - 1];
    }

    //Размер стека
    public int size() {
        return pointer;
    }


}

//Обобщение с несколькими типами
class Pair<X, Y> {
    private X x;
    private Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
        X x1 = null;    //обобщение класса можно использовать везде в этом классе
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }
}



class NonGenericClass {

    private int x;

    //Обобщенный конструктор
    public <K> NonGenericClass() {
        K k1 = null;
        //...
    }

    //Обобщенный метод
    public <T> void doAction(T action) {
        System.out.println("Doing " + action);
    }

}

class GenericClass<T> {

    private int x;

    //Обобщенный конструктор
    public <K> GenericClass() {
        K k1 = null;
        //...
    }

    //Обобщенный метод
    public <E> void doAction(E action) {
        System.out.println("Doing " + action);
    }

}