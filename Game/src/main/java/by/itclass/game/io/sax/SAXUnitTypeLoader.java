package by.itclass.game.io.sax;

import by.itclass.game.core.UnitType;
import by.itclass.game.io.UnitTypeLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, отвечаютщий за загрузку информации о героях
 */
public class SAXUnitTypeLoader implements UnitTypeLoader {

    private String listFile;
    private Map<Integer, UnitType> unitTypes;


    public SAXUnitTypeLoader(String listFile) {
        if (listFile == null) {
            throw new IllegalArgumentException("Файл с картинками отсуствует");
        }
        this.listFile = listFile;
        this.unitTypes = new HashMap<>();
    }

    public void load() {

        UnitTypeSAXParser parser = new UnitTypeSAXParser(unitTypes);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(this.getClass().getClassLoader().getResourceAsStream(listFile), parser);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalArgumentException("Ошибка чтения юнита");
        }
    }

    public UnitType getUnitType(int type) {
        if (type < 0) {
            throw new IllegalArgumentException("Неверный тип героя");
        }
        return unitTypes.get(type);
    }

}
