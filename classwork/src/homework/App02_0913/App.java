
package homework.App02_0913;

public class App {

    public static void main(String[] args){

        // динамическое создание массива
        int[] array; // объявление ссылки на массив числе
        array = new int[100]; // создание маассива из 100 чисел и получение ссылки на него

        int[][] jagged = new int[4][]; // матрица с 4 строками

        for(int i=0;i<jagged.length;i++){
            jagged[i] = new int[10+1];
        }

        for(int i=0; i < jagged.length;i++){
            for (int j=0; j< jagged[i].length;j++){
                //
            }
        }



        String s = "string";
        s.length();

        for (int i=0;i<s.length();i++){
            System.out.println(s.charAt(i));
        }



    }

}
