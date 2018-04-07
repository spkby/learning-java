package by.itclass.game.core;

import java.awt.image.BufferedImage;

/*
    Тип боевой единицы
 */
public class UnitType {

    private BufferedImage image;
    private int maxMovementSpeed;
    private double swimFactor;
    private boolean canSwim;

    private int width;
    private int height;

    public UnitType(BufferedImage image,
                    int maxMovementSpeed,
                    double swimFactor,
                    boolean canSwim) {
        if (image == null) {
            throw new IllegalArgumentException("Отсутствует изображение");
        }
        if (maxMovementSpeed < 0) {
            throw new IllegalArgumentException("Неправильная скорость");
        }
        if (swimFactor < 0) {
            throw new IllegalArgumentException("Отрицательный штраф за плавание");
        }
        this.canSwim = canSwim;
        this.image = image;
        this.maxMovementSpeed = maxMovementSpeed;
        this.swimFactor = swimFactor;
        this.height = image.getHeight();
        this.width = image.getWidth();

    }

    public BufferedImage getImage() {
        return image;
    }

    public int getMaxMovementSpeed() {
        return maxMovementSpeed;
    }

    public double getSwimFactor() {
        return swimFactor;
    }

    public boolean isCanSwim() {
        return canSwim;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
