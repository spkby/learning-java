package itclass.app_1220;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;

public class app1220_2 {

    public static void main(String[] args) {

        final int TASKS_COUNT = 100000;
        final int TASKS_SIZE = 1000000;

        SumTask_2[] tasks = new SumTask_2[TASKS_COUNT];

        Set<String> threads_names = new ConcurrentSkipListSet<>();

        AtomicLong fullSum = new AtomicLong(0);

        for (int i = 0; i < TASKS_COUNT; i++) {
            tasks[i] = new SumTask_2(i*TASKS_SIZE,(i+1)*TASKS_SIZE,fullSum,threads_names);
        }


/*/
        long startTime = System.nanoTime();
        for (int i = 0; i < TASKS_COUNT; i++) {
            tasks[i].run();
        }
        long endTime = System.nanoTime();
/*/

        Thread[] threads = new Thread[TASKS_COUNT];
        long startTime = System.nanoTime();
        for (int i = 0; i < TASKS_COUNT; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (int i = 0; i < TASKS_COUNT; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.nanoTime();


//*/

        System.out.printf("Time: %.4f %n", (endTime-startTime)/1e9);
        System.out.printf("Sum: %d %n", fullSum.get());
        System.out.printf("Count threads: %d %n", threads_names.size());
    }

}


/*
класс нахождения суммы последовательности числе
 */
class SumTask_2 implements Runnable{

    private int start;
    private int end;
private Set<String> threads_names;
    private AtomicLong fullSum;

    public SumTask_2(int start, int end, AtomicLong fullSum, Set<String> threads_names){
        this.end = end;
        this.start = start;
        this.fullSum = fullSum;
        this.threads_names = threads_names;
    }

    @Override
    public void run() {

        long sum = 0;
        threads_names.add(Thread.currentThread().getName());

        for (int i = start; i < end; i++) {
            sum += i;
        }
        fullSum.addAndGet(sum);
    }
}
