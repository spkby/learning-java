package itclass.app_1220;

import javax.swing.text.TabableView;
import java.util.concurrent.atomic.AtomicLong;

public class app1220 {

    public static void main(String[] args) {

        final int TASKS_COUNT = 100000;
        final int TASKS_SIZE = 1000000;

        SumTask[] tasks = new SumTask[TASKS_COUNT];
        AtomicLong fullSum = new AtomicLong(0);

        for (int i = 0; i < TASKS_COUNT; i++) {
            tasks[i] = new SumTask(i*TASKS_SIZE,(i+1)*TASKS_SIZE,fullSum);
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
    }

}


/*
класс нахождения суммы последовательности числе
 */
class SumTask implements Runnable{

    private int start;
    private int end;

    private AtomicLong fullSum;

    public SumTask(int start, int end, AtomicLong fullSum){
        this.end = end;
        this.start = start;
        this.fullSum = fullSum;
    }

    @Override
    public void run() {

        long sum = 0;

        for (int i = start; i < end; i++) {
            sum += i;
        }
        fullSum.addAndGet(sum);
    }
}
