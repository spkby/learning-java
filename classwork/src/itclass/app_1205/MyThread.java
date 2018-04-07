package itclass.app_1205;

public class MyThread extends Thread {

    @Override
    public void run() {
        int counter = 0;
        while (true) {

            System.out.println("This is my thread " + counter++);
        }
    }

}
