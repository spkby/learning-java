package homework.App03_0920.Task01;

import java.util.Random;

public class App01 {

    static double lenghtToDot(Dot dot, Line line){

        Dot A = line.getStart();
        Dot B = line.getEnd();
        Dot C = dot;

        Triangle tr = new Triangle(A, B, C);
        double c = Dot.distanceBetween(A,B);
        double a = Dot.distanceBetween(C,B);
        double b = Dot.distanceBetween(A,C);

        double alfa, beta;

        alfa = Math.acos( (b*b + c*c - a*a) / (2*b*c) );
        beta = Math.acos( (a*a + c*c - b*b) / (2*a*c) );
        if(alfa > 90 || beta > 90){
            if(a > b) return b;
            else return a;
        }
        return c*Math.sin(alfa);
    }

    static int x,y;

    static void getXY(){
    Random rnd = new Random();
    x = rnd.nextInt((10 - 0) + 1) + 0;
    y = rnd.nextInt((10 - 0) + 1) + 0;
}

    public static void main(String[] args) {

        getXY();

        Dot dot, start, end,A, B, C;

        // dot
		dot = new Dot(x,y);

		// line
        getXY();
        start = new Dot(x,y);
        getXY();
        end = new Dot(x,y);

		Line line1 = new Line(start, end);

        getXY();
        start = new Dot(x,y);
        getXY();
        end = new Dot(x,y);
        Line line2 = new Line(start, end);

		// triangel
        getXY();
		A = new Dot(x,y);
        getXY();
		B = new Dot(x,y);
        getXY();
        C = new Dot(x,y);

		Triangle triangle = new Triangle(A,B,C);

        System.out.println(dot);
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(triangle);

        System.out.println("Lenght Dot to Line: " + lenghtToDot(dot,line1));
        System.out.println("Lenght Dot to Line: " + lenghtToDot(dot,line2));

        if (Line.getIntersectionPoint(line1,line2) == true){
            System.out.println("Line is Intersection");
        } else {
            System.out.println("Line is not Intersection");
        }

    }
}
