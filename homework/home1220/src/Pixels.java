import java.awt.image.BufferedImage;

public class Pixels {

    private int height;
    private int width;
    private int[][] array;

    public Pixels(BufferedImage img) {
        this.width = img.getWidth();
        this.height = img.getHeight();
        fill(img);
    }

    void fill(BufferedImage img) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                array[x][y] = img.getRGB(x, y);
            }
        }
    }

    public void set(int x, int y, int rgba) {
        array[x][y] = rgba;
    }

    public int get(int x, int y) {
        return array[x][y];
    }

    public int[][] getArray(){
        return array;
    }
}