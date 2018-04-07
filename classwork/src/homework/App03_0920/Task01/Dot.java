package homework.App03_0920.Task01;

class Dot {

    private double x,y;

    public double getX() {
        return x;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y){
        this.y = y;
    }

    Dot(){
        x=0;
        y=0;
    }

    Dot(double x, double y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString(){
        return String.format("(%f,%f)",x,y);
    }


    @Override
    public boolean equals(Object obj){

        if(obj == null) return false;
        if(obj == this) return true;
        if(obj.getClass() != Dot.class) return false;

        Dot dot = (Dot)obj;
        if(dot.x == this.x && dot.y == this.y ){
            return true;
        }
        else {
            return false;
        }
    }

    public double distanceTo(Dot d){
        if(d == null){
            System.err.println("Error ");
            return -1;
        }
        double dx = this.x - d.x;
        double dy = this.y - d.y;
        return Math.sqrt(dx*dx+dy*dy);
    }

    public static double distanceBetween(Dot d1,Dot d2){
        if(d1 == null || d2 == null){
            System.err.println("Error ");
            return -1;
        }
        double dx = d1.x - d2.x;
        double dy = d1.y - d2.y;
        return Math.sqrt(dx*dx+dy*dy);
    }

}
