package app3011;

import java.util.List;

public interface TemperaturesStorage {
//TemperaturesStorage

    void add(Integer temperature);
    void remove(int day);
    Integer get(int day);
    void update(int day, Integer newTemp);

    List<Integer> getAll();


}
