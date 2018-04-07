package itclass.app06_1110.exceptions;

/*
    Собственный класс исключения, возникающий тогда,
    когда при вызове метода вызывающий передал отрицательное число
 */
public class NegativeNumberException extends RuntimeException {

    //Какая переменная (имя) оказалась неправильной
    private String valueName;

    public NegativeNumberException(String message, String valueName) {
        super(message);
        this.valueName = valueName;
    }

    public String getValueName() {
        return valueName;
    }

}
