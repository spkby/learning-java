package itclass.app_1220;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class app1220_4 {

    public static void main(String[] args) {

        final int TASKS_COUNT = 100000;
        final int TASKS_SIZE = 1000000;

        SumTask_4[] tasks = new SumTask_4[TASKS_COUNT];

        Set<String> threads_names = new ConcurrentSkipListSet<>();


        for (int i = 0; i < TASKS_COUNT; i++) {
            tasks[i] = new SumTask_4(i*TASKS_SIZE,(i+1)*TASKS_SIZE,threads_names);
        }


        long startTime = System.nanoTime();

        Executor executor = null;
        ExecutorService service = null;

        // исполнитель который выполняет все задачи в одном потоке
        service = Executors.newSingleThreadExecutor();

        // указываем сколько потоков
        int cpuCount = Runtime.getRuntime().availableProcessors();
        service = Executors.newFixedThreadPool(cpuCount);

        /*
         если в очереди отсутствуют потоки, то они создаются с нуля
         иначе переиспользются
          */
        service = Executors.newCachedThreadPool();

        /*
        если задача начала что-то ждать (syncrhonize, wait), то этот исполнитель
        перекинет поток на другую задачу, которая ничего не ждет
         */
        service = Executors.newWorkStealingPool();

        /*
         объект который позволяет следить за выполнением задачи
         которая может вернуть объект типа V
          */
        Future<?>[] futures = new Future[TASKS_COUNT];

        for (int i = 0; i < TASKS_COUNT; i++) {
            futures[i] = service.submit(tasks[i]);
        }

        long sum = 0;

        for (int i = 0; i < TASKS_COUNT; i++) {
            try {
               /*sum +=*/ futures[i].get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.nanoTime();

        System.out.printf("Time: %.4f %n", (endTime-startTime)/1e9);
        System.out.printf("Sum: %d %n", sum);
        System.out.printf("Count threads: %d %n", threads_names.size());

        service.shutdown();
    }

}


/*
класс нахождения суммы последовательности числе
 */
class SumTask_4 implements Callable<Long>{

    private int start;
    private int end;
    private Set<String> threads_names;

    public SumTask_4(int start, int end, Set<String> threads_names){
        this.end = end;
        this.start = start;
        this.threads_names = threads_names;
    }


    @Override
    public Long call() {
        threads_names.add(Thread.currentThread().getName());
        long sum;


        /*for (int i = start; i < end; i++) {
            sum += i;
        }*/

        sum = ((long)end-start) * ((long)start - end + 1) / 2;
        return  sum;
    }
}
