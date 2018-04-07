package by.itclass.game.core;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Объект, до которого нужно добраться персонажу
 */
public class FinalPoint implements Drawable {

    private int i;
    private int j;
    private float x;
    private float y;

    private BufferedImage image;

    public FinalPoint(int i,
                      int j,
                      BufferedImage image,
                      GameMap gameMap) {

        if (gameMap == null) {
            throw new IllegalArgumentException("Отсутствует карта");
        }
        if (j < 0 || j > gameMap.getWidth()) {
            throw new IllegalArgumentException("Объект вышел за границы по оси X");
        }
        if (i < 0 || i > gameMap.getHeight()) {
            throw new IllegalArgumentException("Объект вышел за границы по оси Y");
        }
        if (image == null) {
            throw new IllegalArgumentException("Отсутствует изображение");
        }

        this.i = i;
        this.j = j;
        this.x = j * gameMap.CELL_WIDTH;
        this.y = i * gameMap.CELL_HEIGHT;
        this.image = image;
    }

    @Override
    public void draw(Graphics g, double deltaTime) {
        g.drawImage(image, (int) x, (int) y, null);
    }


    public int getCellX() {
        return j;
    }

    public int getCellY() {
        return i;
    }
}
