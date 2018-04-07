package app2911;

public class FakeFunc {






    public static String wordFunc(int i){










        if(i < 0){
            return "-";
        } else if (i > 0){
            return "+";
        } else {
            return "0";
        }


    }


    public static int sum (int[] array){

        if(array == null){
            throw new IllegalArgumentException();
        }

        int sum = 0;
        for (int x: array) {
            sum += x;
        }
        return sum;
    }



}
