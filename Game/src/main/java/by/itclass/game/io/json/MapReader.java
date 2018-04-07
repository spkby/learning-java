package by.itclass.game.io.json;

import by.itclass.game.core.*;
import by.itclass.game.io.UnitTypeLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, отвечающий за чтение игрового поля из файла
 */
public class MapReader {

    private String file;

    private int finalI;
    private int finalJ;

    private ArrayList<Map<String, Object>> enemies;

    private HashMap<String, Object> hero;


    public MapReader(String file) {

        if (file == null) {
            throw new IllegalArgumentException("Отсутствует ссылка на файл");
        }


        this.file = file;

    }

    public GameMap read(CellTypeLoader loader) {
        GameMap gameMap = null;
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> data = objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream(file),
                    new TypeReference<Map<String, Object>>() {
                    });

            HashMap<String, Object> map = (HashMap<String, Object>) data.get("map");

            hero = (HashMap<String, Object>) map.get("player");

            HashMap<String, Object> sizes = (HashMap<String, Object>) map.get("size");

            int width = (Integer) sizes.get("width");
            int height = (Integer) sizes.get("height");

            gameMap = new GameMap(width, height, loader);

            HashMap<String, Object> finalPoint = (HashMap<String, Object>) map.get("finalPoint");

            finalI = (Integer) finalPoint.get("i");
            finalJ = (Integer) finalPoint.get("j");


            enemies = (ArrayList<Map<String, Object>>) map.get("enemies");

            ArrayList<ArrayList<Integer>> mapData = (ArrayList<ArrayList<Integer>>) map.get("data");


            for (int i = 0; i < height; i++) {
                ArrayList<Integer> row = mapData.get(i);
                if (row.size() != width) {
                    throw new IndexOutOfBoundsException("Ширина строки неверна");
                }
                for (int j = 0; j < width; j++) {
                    int type = row.get(j);
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
        for (int k = 0; k < enemies.size(); k++) {

            Map<String, Object> enemy = enemies.get(k);

            int type = (Integer) (enemy.get("type"));
            int i = (Integer) (enemy.get("i"));
            int j = (Integer) (enemy.get("j"));

            Enemy unit = new Enemy(loader.getUnitType(type),
                    j * map.CELL_WIDTH,
                    i * map.CELL_HEIGHT, map);

            units.add(unit);
        }
        return units;
    }

    public Unit readHero(GameMap map, UnitTypeLoader loader) {
        int type = (Integer) hero.get("type");
        int i = (Integer) hero.get("i");
        int j = (Integer) hero.get("j");

        return new Unit(loader.getUnitType(type),
                j * map.CELL_WIDTH,
                i * map.CELL_HEIGHT, map);
    }

}
