package parser;

import entities.*;
import enums.HumanType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SaxParser extends DefaultHandler implements Repository {
    private static final String XSD_PATH = "human.xsd";
    private String path;
    private String tmpValue;
    private Human tmpHuman;
    private Passport tmpPassport;
    private List<Human> humans;

    public SaxParser(String path) throws FileNotFoundException {
        if (XsdValidation.validateAgainstXSD(new FileInputStream(path), new FileInputStream(XSD_PATH))) {
            this.path = path;
            humans = new ArrayList<>();
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("human")) {
            HumanType type = HumanType.parse(attributes.getValue("type"));
            switch (type) {
                case CLIENT:
                    tmpHuman = new Client();
                    break;
                case STUDENT:
                    tmpHuman = new Student();
                    break;
                case EMPLOYEE:
                    tmpHuman = new Employee();
                    break;
            }
            tmpHuman.setUid(UUID.fromString(attributes.getValue("id")));
        }
        if (qName.equalsIgnoreCase("passport")) {
            tmpPassport = new Passport();
            tmpPassport.setNumber(attributes.getValue("number"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("human")) {
            humans.add(tmpHuman);
        }
        if (qName.equalsIgnoreCase("firstName")) {
            tmpHuman.setFirstName(tmpValue);
        }
        if (qName.equalsIgnoreCase("lastName")) {
            tmpHuman.setLastName(tmpValue);
        }
        if (qName.equalsIgnoreCase("age")) {
            tmpHuman.setAge(Integer.parseInt(tmpValue));
        }
        if (qName.equalsIgnoreCase("order")) {
            ((Client) tmpHuman).setOrder(tmpValue);
        }
        if (qName.equalsIgnoreCase("group")) {
            ((Student) tmpHuman).setGroup(tmpValue);
        }
        if (qName.equalsIgnoreCase("experience")) {
            ((Employee) tmpHuman).setExperience(Integer.parseInt(tmpValue));
        }

        /*
        passport
         */
        if (qName.equalsIgnoreCase("passport")) {
            tmpHuman.setPassport(tmpPassport);
        }
        if (qName.equalsIgnoreCase("dateOfIssue")) {
            tmpPassport.setDateOfIssue(tmpValue);
        }
        if (qName.equalsIgnoreCase("authority")) {
            tmpPassport.setAuthority(tmpValue);
        }
        if (qName.equalsIgnoreCase("dateOfExpiry")) {
            tmpPassport.setDateOfExpiry(tmpValue);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tmpValue = new String(ch, start, length);
    }

    @Override
    public List<Human> read() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            FileInputStream file = new FileInputStream(path);
            parser.parse(file, this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return humans;
    }

    @Override
    public void create(Human item) {

    }

    @Override
    public void update(Human item) {

    }

    @Override
    public void delete(Human item) {

    }
}
