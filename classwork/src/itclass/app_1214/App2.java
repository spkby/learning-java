package itclass.app_1214;

public class App2 {

    public static void main(String[] args) {

        /*SimpleQueue queue = new SimpleQueue();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();*/

        SimpleQueue2 queue = new SimpleQueue2();
        new Thread(new Producer(queue),"P1").start();
        new Thread(new Producer(queue),"P2").start();
        new Thread(new Producer(queue),"P3").start();
        new Thread(new Consumer(queue),"C").start();


    }
}

    class Producer implements Runnable {

        private SimpleQueue2 queue;

        public Producer(SimpleQueue2 queue) {

            this.queue = queue;
        }


        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                queue.put(i);
            }
        }
    }

    class Consumer implements Runnable{

        private SimpleQueue2 queue;

        public Consumer(SimpleQueue2 queue){
            this.queue = queue;
        }


        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(queue.get());
            }
        }
    }
