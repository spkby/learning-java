package itclass.app_1205;

public class RunnableThread implements Runnable {
    @Override
    public void run() {

int counter = 0;
while (true)
{
        System.out.println("This is Runnable " + counter++);
    }}
}
