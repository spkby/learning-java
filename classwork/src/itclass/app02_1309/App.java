package itclass.app02_1309;

public class App {

    public static void main(String[] args) {

        int[] array = null;   // объявление ссылки на массив чисел
        array = new int[100]; //создание массива из 100 чисел
                              //и получение ссылки на него
        array = new int[] {1, 20, -1, 20}; //массив из 4 чисел

        array[0] = 1;                      //запись в ячейку
        System.out.println(array[1]);      //чтение (и вывод на экран)

        //array[-1] = 1;        //программа упадет здесь
        //array[4] = 10;        //так как произошел выход за границы
                                //массива
        System.out.println(array.length);   //длина массива
                                            //(в элементах)

        //Цикл обхода по массиву
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        //Ненадежный вариант многомерного массива
        int height = 5;
        int width = 5;
        int[] square = new int[height * width];
        for (int i = 0; i < height; i++) {
            //width = 6;
            for (int j = 0; j < width; j++) {
                square[i * width + j] = 10;
            }
        }

        //Двумерный массив
        int[][] sq = new int[5][5]; //явное указание размеров при создании
        for (int i = 0; i < sq.length; i++) {
            for (int j = 0; j < sq[i].length; j++) {
                System.out.println(i + "," + j + " = " + sq[i][j]);
            }
        }

        //Трехмерный массив
        int[][][] tr = new int[1][2][3];

        //Зубчатые массивы
        int[][] jagged = new int[4][];  // матрица с 4 строками
        for (int i = 0; i < jagged.length; i++) {
            jagged[i] = new int[10 - i];
        }

        System.out.println("Зубчатый массив");
        for (int i = 0; i < jagged.length; i++) {
            for (int j = 0; j < jagged[i].length; j++) {
                System.out.printf("%d, %d = %d\n", i, j, jagged[i][j]);
            }
        }

        //Строка
        String s = "Строка";
        String s1 = "sdg";
        s = s1;
        s = "dfsdfsdfs";
        s = s + "fhgfhghf";

        int s_len = s.length();     //функция, возвращающая длину строки
        for (int i = 0; i < s_len; i++) {
            System.out.println(s.charAt(i));    //charAt - функция получения символа
        }

        System.out.println(s + " дополнение");  // строки можно складывать

        s.concat("Дополнение"); // то же самое, что и сложение
        System.out.println(s.contains("тро"));  //содержится подстрока в строке
        System.out.println(s.contains("тор"));

        int k = s.indexOf("тро"); // первое вхождение в строку (индекс)
        s.lastIndexOf("тро"); // последнее вхождение в строку (индекс)

        s.indexOf("тро", k); // первое вхождение в строку (индекс)
                             // начиная с k-го символа

    }

    public static void task1() {

    }

}
