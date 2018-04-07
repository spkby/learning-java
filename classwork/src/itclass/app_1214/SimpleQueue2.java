package itclass.app_1214;

public class SimpleQueue2 {

    private Integer number;
    private volatile boolean isDataWritten;

    /** volatile - для JVM не заносить переменную в кэш, а всегда брать из ОЗУ, чтобы избежать
     ощшибок в многопоточных программах
     **/

    public synchronized void put(int newInt){

        while(isDataWritten) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number = newInt;
        isDataWritten = true;
        notify();
    }

    public synchronized Integer get() {
        while (!isDataWritten) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isDataWritten = false;
        notify();
        return number;
    }

}
