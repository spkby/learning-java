package app2911;

import org.junit.Assert;
import org.junit.Test;

public class ProdTest {

/*
тестовый метод
 */
    @Test
    public void testTwoIntProduction(){

        int result = 6*8;

        // сравнивает два объекта(числа, строки и тд)
        Assert.assertEquals(48,result);

    }


    @Test
    public void testWithError(){
        int result = 6*8;
        Assert.assertEquals(40,result);
    }


    @Test
    public void testPositiveNumber(){
        String expected = "+";
        String actual = FakeFunc.wordFunc(10);

        Assert.assertEquals(expected,actual);
    }
    @Test
    public void testNegativeNumber(){
        String expected = "-";
        String actual = FakeFunc.wordFunc(-10);

        Assert.assertEquals(expected,actual);
    }
    @Test
    public void testZero(){
        String expected = "0";
        String actual = FakeFunc.wordFunc(0);

        Assert.assertEquals(expected,actual);
    }
}
