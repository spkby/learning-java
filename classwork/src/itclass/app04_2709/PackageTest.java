package itclass.app04_2709;

// Эта конструкция говорит о том, что класс Bird можно использовать
// по имени (неполному)
import itclass.app03_2009.Bird;

import itclass.app02_1309.App;
//import app03_2009.io;

public class PackageTest {

    public static void main(String[] args) {
        Bird bird = new Bird();

        itclass.app03_2009.App app = new itclass.app03_2009.App();
        App app1 = new App();

        int a = 1 / 0;

    }

}
