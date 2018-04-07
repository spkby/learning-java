package itclass.app_1205;

public class ThreadRunner {

    public static void main(String[] args) throws InterruptedException {


//*/
        MyThread thread = new MyThread();
        Thread runnableThread = new Thread(new RunnableThread());
        thread.start();
        runnableThread.start();

        thread.join();
        runnableThread.join();
/*/
        int counter = 0;
        while (true)
        {
            System.out.println("This " + counter++);
        }
/*/
        int counter = 0;
        while (true)
        {
            System.out.println("This " + counter++);
        }
//*/
    }
}
