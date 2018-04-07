package by.itclass.game.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
    Тип игровой ячейки
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CellType {

    private int type;
    private boolean walkable;
    private boolean swimmable;
    private BufferedImage cellImage;

    public CellType(boolean swimmable, boolean walkable, BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Отсутствует изображение");
        }
        this.walkable = walkable;
        this.swimmable = swimmable;
        this.cellImage = image;
    }

    @JsonCreator
    public CellType(@JsonProperty("type") int type,
                    @JsonProperty("swimable") boolean swimmable,
                    @JsonProperty("walkable") boolean walkable,
                    @JsonProperty("cellImage") String image) {
        if (image == null) {
            throw new IllegalArgumentException("Отсутствует изображение");
        }
        this.walkable = walkable;
        this.swimmable = swimmable;
        this.type = type;
        try {
            this.cellImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(image));
        } catch (IOException e) {
            throw new IllegalArgumentException("Отсутствует картинка");
        }
    }

    public boolean isSwimmable() {
        return swimmable;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public BufferedImage getCellImage() {
        return cellImage;
    }

    public int getType() {
        return type;
    }
}
