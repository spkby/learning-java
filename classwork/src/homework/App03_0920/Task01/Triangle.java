package homework.App03_0920.Task01;

class Triangle {

    private Dot A,B,C;

    Triangle(Dot A, Dot B, Dot C){
        if(A == null || B == null || C == null){
            System.err.print("One or more point is null");
        } else if(A.equals(B) || A.equals(C) || B.equals(C)){
            System.err.println();
        } else {
            double b = Dot.distanceBetween(A,C);
            double c = Dot.distanceBetween(A,B);
            double a = Dot.distanceBetween(B,C);
            if(a+b <= c || a+c <= b || b+c <= a){
                System.err.println("Dot on line");
            } else {
                this.A = A;
                this.B = B;
                this.C = C;
            }
        }
    }

    private double getLineLenght(double xStart, double xEnd, double yStart, double yEnd){

        double lenght;

        double dx = xStart-xEnd;
        double dy = yStart-yEnd;

        lenght = Math.sqrt(dx*dx+dy*dy);

        return lenght;
    }

    @Override
    public String toString(){
        return "Triangle with coordinates: " + "A: " + A + " B: " + B + " C: " + C;
    }

    @Override
    public boolean equals(Object obj){

        if(obj == null || obj.getClass() != Triangle.class){
            return false;
        }
        Triangle ta = (Triangle)obj;

        Triangle.sortABC(this);
        Triangle.sortABC(ta);

        return false;
    }

    static void sortABC(Triangle tr){

        Dot D;

        // A > B or A = B + B -> A
        if((tr.A.getX() > tr.B.getX()) || (tr.A.getX() == tr.B.getX() && tr.A.getY() > tr.B.getY())) {
            D = tr.A;
            tr.A = tr.B;
            tr.A = D;

            // A > C or A = C + C -> A
            if((tr.A.getX() > tr.C.getX()) || (tr.A.getX() == tr.C.getX() && tr.A.getY() > tr.C.getY())){
                D = tr.A;
                tr.A = tr.C;
                tr.A = D;

                // B > C or B = C + C -> B
                if((tr.B.getX() > tr.C.getX()) || (tr.B.getX() == tr.C.getX() && tr.B.getY() > tr.C.getY())) {
                    D = tr.B;
                    tr.B = tr.C;
                    tr.B = D;
                }
            }
        }

        // A > C or A = C + C -> A
        if((tr.A.getX() > tr.C.getX()) || (tr.A.getX() == tr.C.getX() && tr.A.getY() > tr.C.getY())) {
            D = tr.A;
            tr.A = tr.B;
            tr.A = D;

            // B > C or B = C + C -> B
            if((tr.B.getX() > tr.C.getX()) || (tr.B.getX() == tr.C.getX() && tr.B.getY() > tr.C.getY())) {
               D = tr.B;
                 tr.B = tr.C;
                 tr.B = D;
            }
        }

    }

}
