package app2911;

import org.junit.*;

public class AnnotationSampleTest {

    private FakeFunc f;


    // @Before - вызывать перед каждым методом
    @Before
    public void setupTestEnviroment(){
        f = new FakeFunc();
    }


    @After
    public void tearDown(){
        f = null;
    }

    // метод вызывается перед всеми тестами
    @BeforeClass
    public void setupTests(){

    }

    @AfterClass
    public void tearTests(){

    }

    // тест который явно ожидает появления исключения
    @Test(expected = IllegalArgumentException.class)
    public void testNullArrray(){

    int[] array = null;

    FakeFunc.sum(array);
}

    @Test
    public void testArray(){

        int[] array = new int[] {1,2,3};
        int actual = FakeFunc.sum(array);

        Assert.assertEquals(6,actual);

    }


    // время выполнения теста
    @Test(timeout = 1)
    public void testSlow(){


        Assert.assertFalse(false);

    }


}
