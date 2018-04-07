package itclass.app06_1110.exceptions;

public class App {

    public static void main(String[] args) {

        //В блок try мы помещаем инструкции, где может возникнуть
        //какое-либо исключение
        try {
            //int x = 4 / 0;
            testFinally();
            System.out.println("После деления на 0");
        }
        /*
        Нельзя помещать блоки catch, обрабатывающие классы-родители
        исключений, перед обработчиками детей
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        */
        catch (ArithmeticException e) {
            //блок catch реагирует на определенную ошибку
            e.printStackTrace();
            //Получаем массив элементов стека вызовов
            StackTraceElement[] elements = e.getStackTrace();
            //elements[0].
            //System.out.println("Произошла ошибка :" + e.getMessage());
        } catch (NullPointerException e) {
            //У нас может быть несколько блоков catch
            //Причем каждый должен реагировать на свой тип ошибок
            System.out.println("Обращение к нулевому указателю");
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            //Данный метод вывод "след стека" в поток
            e.printStackTrace();
        } finally {
            //Блок finally выполняется всегда, была ошибка
            //или не была
            System.out.println("Finally");
        }

    }

    public static void testFinally() {
        try {
            int x = 4 / 0;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Calc finally");
        }
    }

}
