package itclass.app09_2410;

import itclass.app05_0410.figures.*;

import java.util.Comparator;

public class FigureAreaComparator implements Comparator<Figure>{


    @Override
    public int compare(Figure o1, Figure o2) {

        double area1 = o1.getArea();
        double area2 = o2.getArea();

        return 0;
    }


}
