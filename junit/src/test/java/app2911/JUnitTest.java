package app2911;

import org.junit.Assert;
import org.junit.Test;

import app2911.ProdTest.*;

public class JUnitTest {


    @Test
    public void testAssetSame(){

        Point expected = new Point(10,5);
        Point actual = new Point(10,5);

        //проверяет логически
        Assert.assertSame(expected,actual);

    }

    @Test
    public void testAssetEquals(){

        Point expected = new Point(10,5);
        Point actual = new Point(10,5);
        Assert.assertEquals(expected,actual);
    }


    @Test
    public void testAssertNotSame(){
        Point expected = new Point(10,5);
        Point actual = new Point(10,5);

        //объекты логически не равныф
        Assert.assertNotSame(expected,actual);
    }


    @Test
    public void testAssetNotEquals(){

        Point expected = new Point(10,5);
        Point actual = new Point(10,5);

        // ссылки не указывают на один и тот же объект
        Assert.assertNotEquals(expected,actual);
    }


    @Test
    public void testArrayEquality(){

        int[] expected = new int[] {1,2,3};
        int[] actual = new int[] {1,2,3};

        //Assert.assertEquals(expected,actual);
        //Assert.assertSame(expected,actual);

        /*
        сравнение массивов осуществляется с помощью метода assertArrayEquals
         */
        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void testFalse(){

        boolean filmDownloaded = false;

        // проверить результат
        Assert.assertFalse(filmDownloaded);
    }


    @Test
    public void testNull(){

        Point expected = new Point(10,5);

        // ссылки не указывают на один и тот же объект
        Assert.assertNull(expected);
    }


    @Test
    public void testFail(){

        Assert.fail();
    }

}
