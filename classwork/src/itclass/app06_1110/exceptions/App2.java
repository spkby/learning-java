package itclass.app06_1110.exceptions;

public class App2 {

    public static void main(String[] args) {
        try {
            int s = sum(-1, 1);
        } catch (NegativeNumberException e) {
            System.out.println("Переменная - " + e.getValueName());
        }

        try {
            int s = sum(1, -1);
        } catch (NegativeNumberException e) {
            System.out.println("Переменная - " + e.getValueName());
        }

        try {
            int s = sum(1, 1);
            System.out.println(s);
        } catch (NegativeNumberException e) {
            System.out.println("Переменная - " + e.getValueName());
        }
    }

    public static int sum(int a, int b) {
        if (a < 0) {
            //Для выброса ошибки используется оператор throw
            throw new NegativeNumberException("Отрицательное число", "a");
        } else if (b < 0) {
            //В общем случае, если в какой-то ветке if-else
            //из функции не возвращается значение
            //то компилятор не даст скомпилировать данный код
            //но проброс ошибки заменяет return
            //так как до возврата значения дело не дойдет
            NegativeNumberException ne = new NegativeNumberException("Отрицательное число", "b");
            throw ne;
            //После throw и return не может быть кода
            //так как до него среда выполнения не дойдет
            //boolean c = true;
        } else {
            return a + b;
        }
    }

    //Для проверяемых исключений, если мы не хотим или не можем
    //обработать её в том же методе, в котором она проявилась
    //то можно пробросить её наверх с помощью конструкции throws
    public void testException() throws Exception {
        throw new Exception("Calc");
    }
}
