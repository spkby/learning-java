package itclass.app09_2410;

import itclass.app05_0410.figures.Figure;
import itclass.app05_0410.figures.*;

import java.util.Comparator;

    public class FigurePerimetrComparator implements Comparator<Figure> {


        @Override
        public int compare(Figure o1, Figure o2) {

            double p1 = o1.getPerimeter();
            double p2 = o2.getPerimeter();

            return 0;
        }


    }
