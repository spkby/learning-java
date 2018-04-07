package itclass.app_1220;

import java.util.concurrent.RecursiveTask;

public class app1220_5 {

    public static void main(String[] args) {

    }
}


/*
 ForkJoinPool - класс который упраляет потоками, запуском задач и тех частью

 ForkJoinTask - класс зачач, выполняемый с помощью бибилиотеки

 RecursiveAction -
 RecursiveTask<V> -
  */


class SearchTask extends RecursiveTask<Integer>{

    private int[] array;
    private int s,e, v;

    public SearchTask(int[] array,int s,int e, int v){
        this.array = array;
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    protected Integer compute(){


        if(e - s <= 1000){
            for (int i = s; i < e; i++) {
                if(array[i] == v) return i;
            }
            return -1;
        }

        int m = (s+e)/2;

        SearchTask t1 = new SearchTask(array,s,m,v);
        SearchTask t2 = new SearchTask(array,m,e,v);

        t1.fork();
        t2.fork();

        int i1 = t1.join();
        int i2 = t2.join();

        if(i1 == -1 && i2 == -1){
            return -1;
        } else if(i1 != -1 && i2 == -1){
            return i1;
        } else if(i1 == -1 && i2 != -1){
            return i2;
        } else {
            return i1;
        }
    }

}