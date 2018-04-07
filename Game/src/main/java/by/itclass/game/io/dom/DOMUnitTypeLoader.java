package by.itclass.game.io.dom;

import by.itclass.game.core.UnitType;
import by.itclass.game.io.UnitTypeLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, отвечаютщий за загрузку информации о героях
 */
public class DOMUnitTypeLoader implements UnitTypeLoader {

    private String listFile;
    private Map<Integer, UnitType> unitTypes;

    public DOMUnitTypeLoader(String listFile) {
        if (listFile == null) {
            throw new IllegalArgumentException("Файл с картинками отсуствует");
        }

        this.listFile = listFile;
        this.unitTypes = new HashMap<>();
    }

    public void load() {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(this.getClass().getClassLoader().getResourceAsStream(listFile));

            Element root = document.getDocumentElement();    //корневой элемент (тег) xml-документа

            NodeList unitList = root.getElementsByTagName("unit");

            for (int i = 0; i < unitList.getLength(); i++) {
                Node node = unitList.item(i);
                if (node instanceof Element) {
                    Element element = (Element) node;

                    Element idElement = (Element) element.getElementsByTagName("type").item(0);
                    Element imageElement = (Element) element.getElementsByTagName("image").item(0);
                    Element speedElement = (Element) element.getElementsByTagName("speed").item(0);
                    Element swimFactorElement = (Element) element.getElementsByTagName("swimFactor").item(0);
                    Element canSwimElement = (Element) element.getElementsByTagName("canSwim").item(0);
                    
                    int type = Integer.parseInt(idElement.getTextContent()); //у любого элемента можно узнать текстовое содержимое через функцию getTextContent
                    String image = imageElement.getTextContent();
                    int maxMovementSpeed = Integer.parseInt(speedElement.getTextContent());
                    float swimFactor = Float.parseFloat(swimFactorElement.getTextContent());
                    boolean canSwim = Boolean.parseBoolean(canSwimElement.getTextContent());

                    BufferedImage img = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(image));

                    UnitType unitType = new UnitType(img, maxMovementSpeed, swimFactor, canSwim);

                    unitTypes.put(type, unitType);
                }
            }

        } catch (Exception e) {
            throw new IllegalStateException("Ошибка чтения XML-файла");
        }

    }

    public UnitType getUnitType(int type) {
        if (type < 0) {
            throw new IllegalArgumentException("Неверный тип героя");
        }
        return unitTypes.get(type);
    }

}
