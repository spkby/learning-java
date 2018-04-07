package itclass.app08_1910.generics;

/*
    Стек символов
 */
public class IntStack {

    private int[] array;
    private int pointer;

    public IntStack(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Стек должен быть положительного размера");
        }
        this.array = new int[size];
        this.pointer = 0;
    }

    //Добавление элемента в стек
    public void push(int c) {
        if (pointer == array.length) {
            throw new IllegalStateException("Стек переполнен");
        }
        this.array[pointer] = c;
        pointer++;
    }

    //Забрать элемент из стека
    public int pop() {
        if (pointer == 0) {
            throw new IllegalStateException("Стек пуст");
        }
        this.pointer--;
        return this.array[pointer];
    }

    //Забрать элемент из стека (без удаления)
    public int peek() {
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
