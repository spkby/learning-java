package itclass.app_1219;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Очередь с подддержкой параллельного доступа
 * @param <T> - тип элементов
 */
public class PQueue<T> {

    /**
     * Непосредственно сама очередь, хранящая элементы
     */
    private Queue<T> data;

    /*
    максимальный размер олчереди
     */
    private volatile int maxSize;

    public PQueue(int maxSize){
        this.data = new LinkedList<>();
        this.maxSize = maxSize;
    }


    /*
    взять
     */
    public synchronized T get(){

        while (this.data.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T element = this.data.poll();
        notify();
        return element;
    }

    /**
     * положить в очередь
     * @param item - объект, который надо положить
     */
    public synchronized void put(T item){

        while (this.maxSize == this.data.size()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data.add(item);
        notify();
    }

}
