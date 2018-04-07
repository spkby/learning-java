package app3011;

import java.util.List;

public class TempCalc implements TemperaturesStorage{

    List<Integer> sto;


    public Integer max(){

        List<Integer> temps = sto;


    }


    public Double mean(){

        List<Integer> temps = storage.getAll();


        double sum = 0;
        for (int i: temps) {
            sum += i;

        }

        return sum / temps.size();

    }

    @Override
    public void add(Integer temperature) {

    }

    @Override
    public void remove(int day) {

    }

    @Override
    public Integer get(int day) {
        return null;
    }

    @Override
    public void update(int day, Integer newTemp) {

    }

    @Override
    public List<Integer> getAll() {
        return null;
    }
}
