package by.itclass.game.io.dom;

import by.itclass.game.core.*;
import by.itclass.game.io.UnitTypeLoader;
import by.itclass.game.io.json.CellTypeLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за чтение игрового поля из файла
 */
public class MapReader {

    private String file;

    private int finalI;
    private int finalJ;

    private NodeList enemies;

    private Element hero;


    public MapReader(String file) {

        if (file == null) {
            throw new IllegalArgumentException("Отсутствует ссылка на файл");
        }


        this.file = file;

    }

    public GameMap read(CellTypeLoader loader) {
        GameMap gameMap;
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(this.getClass().getClassLoader().getResourceAsStream(file));

            Element root = document.getDocumentElement();

            hero = (Element) root.getElementsByTagName("player").item(0);

            Element sizes = (Element) root.getElementsByTagName("size").item(0);

            int width = Integer.parseInt(sizes.getAttribute("width"));
            int height = Integer.parseInt(sizes.getAttribute("height"));

            gameMap = new GameMap(width, height, loader);

            Element finalPoint = (Element) root.getElementsByTagName("finalPoint").item(0);

            finalI = Integer.parseInt(finalPoint.getAttribute("i"));
            finalJ = Integer.parseInt(finalPoint.getAttribute("j"));

            enemies = ((Element) root
                    .getElementsByTagName("enemies")
                    .item(0)).getElementsByTagName("enemy");

            NodeList rows = ((Element) root
                    .getElementsByTagName("data")
                    .item(0)).getElementsByTagName("row");

            for (int i = 0; i < height; i++) {
                Element row = (Element) rows.item(i);
                String text = row.getTextContent();
                String[] parts = text.split(",");
                if (parts.length != width) {
                    throw new IndexOutOfBoundsException("Ширина строки неверна");
                }
                for (int j = 0; j < width; j++) {
                    int type = Integer.parseInt(parts[j]);
                    Cell cell = new Cell(type);
                    gameMap.setCell(i, j, cell);
                }
            }


        } catch (Exception e) {
            throw new IllegalArgumentException("Ошибка чтения карты");
        }
        return gameMap;
    }

    public FinalPoint readFinalPoint(GameMap map, BufferedImage image) {
        return new FinalPoint(finalI, finalJ, image, map);
    }

    public List<Enemy> readEnemies(UnitTypeLoader loader, GameMap map) {
        List<Enemy> units = new ArrayList<>();
        for (int k = 0; k < enemies.getLength(); k++) {

            Element enemy = (Element) enemies.item(k);

            int type = Integer.parseInt(enemy.getAttribute("type"));
            int i = Integer.parseInt(enemy.getAttribute("i"));
            int j = Integer.parseInt(enemy.getAttribute("j"));

            Enemy unit = new Enemy(loader.getUnitType(type),
                    j * map.CELL_WIDTH,
                    i * map.CELL_HEIGHT, map);

            units.add(unit);
        }
        return units;
    }

    public Unit readHero(GameMap map, UnitTypeLoader loader) {
        int type = Integer.parseInt(hero.getAttribute("type"));
        int i = Integer.parseInt(hero.getAttribute("i"));
        int j = Integer.parseInt(hero.getAttribute("j"));

        return new Unit(loader.getUnitType(type),
                j * map.CELL_WIDTH,
                i * map.CELL_HEIGHT, map);
    }

}
