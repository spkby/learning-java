package by.itclass.game.io.json;

import by.itclass.game.core.CellType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, отвечаютщий за загрузку ячеек для игрового поля
 */
public class CellTypeLoader {

    private String listFile;
    private Map<Integer, CellType> cellTypeMap;


    public CellTypeLoader(String listFile) {
        if (listFile == null) {
            throw new IllegalArgumentException("Файл с картинками отсуствует");
        }
        this.listFile = listFile;
        this.cellTypeMap = new HashMap<>();
    }

    public void load() {

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(this.getClass()
                            .getClassLoader()
                            .getResourceAsStream(listFile)));

            ObjectMapper mapper = new ObjectMapper();


            List<CellType> cells = mapper.readValue(reader, new TypeReference<List<CellType>>() {
            });

            for (int i = 0; i < cells.size(); i++) {
                cellTypeMap.put(cells.get(i).getType(), cells.get(i));
            }


            reader.close();
        } catch (IOException e) {
            throw new IllegalStateException("Список поврежден или отсутствует картинка");
        }

    }

    public CellType getCellType(int type) {
        if (type < 0) {
            throw new IllegalArgumentException("Неверный тип картинки");
        }
        return cellTypeMap.get(type);
    }

}
