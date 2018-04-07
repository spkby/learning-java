package app3011;

import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.*;

import java.util.ArrayList;

public class TempCalcTests {

    @Test
    public void testMaxTemp(){


        TemperaturesStorage storage = null;

        storage = mock(TemperaturesStorage.class);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);

        when(storage.getAll()).thenReturn(integers);

        storage.add(10);
        storage.add(20);


        TempCalc calc = new TempCalc();


        double expecteed = 15;
        double actual = calc.mean();


        Assert.assertEquals(expecteed,actual,1e-7);

    }





}
