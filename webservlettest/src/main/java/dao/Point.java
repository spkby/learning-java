package dao;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point setX(int x) {
        return new Point(x, y);
    }

    public Point setY(int y) {
        return new Point(x, y);
    }
}
