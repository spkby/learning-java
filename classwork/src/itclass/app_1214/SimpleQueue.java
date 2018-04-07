package itclass.app_1214;

public class SimpleQueue  {

    private Integer number;
    private volatile boolean isDataWritten;

    /** volatile - для JVM не заносить переменную в кэш, а всегда брать из ОЗУ, чтобы избежать
     ощшибок в многопоточных программах
     **/

    public  void put(int newInt){

        while(isDataWritten);
        //if(!isDataWritten) {
            number = newInt;
            isDataWritten = true;
        //}
    }

    public Integer get() {
        while (!isDataWritten) ;
        //if(isDataWritten){
        isDataWritten = false;
        return number;
        //} else {
      //  return null;
    //}
    }
}
