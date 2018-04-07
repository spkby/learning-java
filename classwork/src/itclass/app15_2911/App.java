package itclass.app15_2911;

public class App
{


    public static void main(String[] args) {


        assert prod(1,2) == 2;
        assert prod(-2,2) == -4;
        assert prod(0,-4) == -1;
        assert prod(0,-4) == 0;


    }




    public static int prod(int a, int b){

        return a*b;

    }


}
