package itclass.app_1219;

import itclass.app_1205.RunnableThread;

public class app1219 {


    public static void main(String[] args) {

        PQueue<Integer> queue = new PQueue<>(10);

        new Thread(new Producer(queue,0,1000000)).start();
        new Thread(new Producer(queue,2000000,3000000)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();

    }


}



class Producer implements Runnable{

    private PQueue<Integer> queue;
    private int start;
    private int end;


    public Producer(PQueue<Integer> queue, int start, int end){
        this.queue = queue;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            queue.put(i);
        }
    }
}

class Consumer implements Runnable{

    private PQueue<Integer> queue;

    public Consumer(PQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {

        while (true){
            Integer i = queue.get();
            if(i != null){
                System.out.println("Consumer (" + hashCode() + ") - " + i);
            }
        }
    }
}


