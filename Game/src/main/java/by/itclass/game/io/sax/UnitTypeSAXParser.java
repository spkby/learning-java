package by.itclass.game.io.sax;

import by.itclass.game.core.UnitType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public class UnitTypeSAXParser extends DefaultHandler {

    int type;
    String image;
    int maxMovementSpeed;
    float swimFactor;
    boolean canSwim;
    private boolean isUnit = false;
    private boolean isType = false;
    private boolean isImage = false;
    private boolean isSpeed = false;
    private boolean isSwimFactor = false;
    private boolean isCanSwim = false;
    private Map<Integer, UnitType> unitTypes;

    public UnitTypeSAXParser(Map<Integer, UnitType> unitTypes) {
        if (unitTypes == null) {
            throw new IllegalArgumentException("Отсутствует словарь юнитов");
        }
        this.unitTypes = unitTypes;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("unit")) {
            isUnit = true;
        } else if (qName.equals("type")) {
            isType = true;
        } else if (qName.equals("image")) {
            isImage = true;
        } else if (qName.equals("speed")) {
            isSpeed = true;
        } else if (qName.equals("swimFactor")) {
            isSwimFactor = true;
        } else if (qName.equals("canSwim")) {
            isCanSwim = true;
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        try {
            if (isType) {
                type = Integer.parseInt(new String(ch, start, length));
            } else if (isImage) {
                image = new String(ch, start, length);
            } else if (isSpeed) {
                maxMovementSpeed = Integer.parseInt(new String(ch, start, length));
            } else if (isSwimFactor) {
                swimFactor = Float.parseFloat(new String(ch, start, length));
            } else if (isCanSwim) {
                canSwim = Boolean.parseBoolean(new String(ch, start, length));
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Ошибка чтения юнита");
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("unit")) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(image));
            } catch (IOException e) {
                throw new IllegalArgumentException("Ошибка чтения юнита");
            }

            UnitType unitType = new UnitType(img, maxMovementSpeed, swimFactor, canSwim);

            unitTypes.put(type, unitType);

            isUnit = false;
        } else if (qName.equals("type")) {
            isType = false;
        } else if (qName.equals("image")) {
            isImage = false;
        } else if (qName.equals("speed")) {
            isSpeed = false;
        } else if (qName.equals("swimFactor")) {
            isSwimFactor = false;
        } else if (qName.equals("canSwim")) {
            isCanSwim = false;
        }
    }
}
