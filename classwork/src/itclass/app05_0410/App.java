package itclass.app05_0410;

public class App {

    public static void main(String[] args) {

        //Переменная типа перечисления
        Answer a = Answer.YES;
        a = Answer.NO;
        a = Answer.CANCEL;
        //Нельзя назначить число
        //a = 0;
        //Нельзя назначить неизвестную константу
        //a = Answer.MAYBE;

        Fruit f = Fruit.GRAPEFRUIT;
        System.out.println(f.getPrice());

    }
}
