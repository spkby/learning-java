package by.itclass.game.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс, отвечающий за загрузку картинки точки назначения
 */
public class PointImageReader {

    private String file;

    public PointImageReader(String file) {
        if (file == null) {
            throw new IllegalArgumentException("Отсутствует ссылка на файл");
        }
        this.file = file;
    }

    public BufferedImage readImage() {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(this.getClass()
                            .getClassLoader()
                            .getResourceAsStream(file)));
            String name;
            name = bufferedReader.readLine();
            if (name != null) {
                return ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(name));
            } else {
                throw new IllegalArgumentException("Отсутсвует изображение");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка чтения изображения");
        }

    }
}
