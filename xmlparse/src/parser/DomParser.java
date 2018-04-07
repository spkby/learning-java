package parser;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import entities.*;
import enums.HumanType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DomParser implements Repository {
    private static final String XSD_PATH = "human.xsd";
    private static final String HUMANS = "/humans/human";
    private static final String ROOT = "/humans";
    private static final String HUMAN_BY_ID = "/humans/human[@id='";
    private static final String END_EXPRESSION = "']";

    private String path;
    private Document document;

    private XPath xPath;

    public DomParser(String path) throws IOException, ParserConfigurationException, SAXException {
        if (XsdValidation.validateAgainstXSD(new FileInputStream(path), new FileInputStream(XSD_PATH))) {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = (Document) dBuilder.parse(inputFile);

            xPath = XPathFactory.newInstance().newXPath();
            this.path = path;
        }
    }

    @Override
    public List<Human> read() {
        List<Human> items = new ArrayList<>();
        NodeList list = document.getElementsByTagName("human");
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            Element element = (Element) item;
            HumanType type = HumanType.parse(element.getAttribute("type"));
            items.add(getHumanByElement(element, type));
        }
        return items;
    }


    private Passport getPassportByElement(Element element) {
        Passport passport = null;
        String dateOfIssue = element.getElementsByTagName("dateOfIssue").item(0).getTextContent();
        String dateOfExpiry = element.getElementsByTagName("dateOfExpiry").item(0).getTextContent();
        String authority = element.getElementsByTagName("authority").item(0).getTextContent();
        String number = element.getAttribute("number");
        passport = new Passport(number, dateOfIssue, authority, dateOfExpiry);
        return passport;
    }

    private Human getHumanByElement(Element element, HumanType type) {
        Human human = null;
        String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
        int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
        UUID id = UUID.fromString(element.getAttribute("id"));

        Element item = (Element) element.getElementsByTagName("passport").item(0);
        Passport passport = getPassportByElement(item);

        switch (type) {
            case STUDENT: {
                String group = element.getElementsByTagName("group").item(0).getTextContent();
                human = new Student(id, firstName, lastName, age, passport, group);
            }
            break;
            case EMPLOYEE: {
                int experience = Integer.parseInt(element.getElementsByTagName("experience").item(0).getTextContent());
                human = new Employee(id, firstName, lastName, age, passport, experience);
            }
            break;
            case CLIENT: {
                String order = element.getElementsByTagName("order").item(0).getTextContent();
                human = new Client(id, firstName, lastName, age, passport, order);
            }
            break;
        }
        return human;
    }


    private Element setElementByPassport(Passport passport) {
        Element element = document.createElement("passport");

        element.setAttribute("number", passport.getNumber());

        Element dateOfIssue = document.createElement("dateOfIssue");
        dateOfIssue.setTextContent(passport.getDateOfIssue());
        element.appendChild(dateOfIssue);

        Element authority = document.createElement("authority");
        authority.setTextContent(passport.getAuthority());
        element.appendChild(authority);

        Element dateOfExpiry = document.createElement("dateOfExpiry");
        dateOfExpiry.setTextContent(passport.getDateOfExpiry());
        element.appendChild(dateOfExpiry);

        return element;
    }

    private Element setElementByHuman(Human human) {
        Element element = document.createElement("human");
        HumanType type = HumanType.parse(human);
        element.setAttribute("type", type.getType());
        element.setAttribute("id", String.valueOf(human.getUid()));

        Element firstName = document.createElement("firstName");
        firstName.setTextContent(human.getFirstName());
        element.appendChild(firstName);

        Element lastName = document.createElement("lastName");
        lastName.setTextContent(human.getLastName());
        element.appendChild(lastName);

        Element age = document.createElement("age");
        age.setTextContent(String.valueOf(human.getAge()));
        element.appendChild(age);

        element.appendChild(setElementByPassport(human.getPassport()));

        switch (type) {
            case STUDENT: {
                Element group = document.createElement("group");
                group.setTextContent(((Student) human).getGroup());
                element.appendChild(group);
            }
            break;
            case EMPLOYEE: {
                Element experience = document.createElement("experience");
                experience.setTextContent(String.valueOf(((Employee) human).getExperience()));
                element.appendChild(experience);
            }
            break;
            case CLIENT: {
                Element order = document.createElement("order");
                order.setTextContent(((Client) human).getOrder());
                element.appendChild(order);
            }
            break;
        }
        return element;
    }

    @Override
    public void create(Human item) {
        Element root = (Element) document.getFirstChild();
        root.appendChild(setElementByHuman(item));
        save();
    }

    @Override
    public void update(Human item) {

//*/
        Node node = null;
        try {
            node = (Node) xPath.compile(HUMAN_BY_ID + item.getUid() + END_EXPRESSION)
                    .evaluate(document, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        node.getParentNode().replaceChild(setElementByHuman(item), node);
/*/
        NodeList list = document.getElementsByTagName("human");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            UUID id = UUID.fromString(element.getAttribute("id"));
            if (item.getUid().equals(id))
                element.getParentNode().replaceChild(setElementByHuman(item), element);
        }
//*/
        save();
    }

    @Override
    public void delete(Human item) {
/*/
        Node node = null;
        try {
            node = (Node) xPath.compile(HUMAN_BY_ID + item.getUid() + END_EXPRESSION)
                    .evaluate(document, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        node.getParentNode().removeChild(node);
/*/
        NodeList list = document.getElementsByTagName("human");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            //String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
            UUID id = UUID.fromString(element.getAttribute("id"));

            if (item.getUid().equals(id))
                element.getParentNode().removeChild(element);
        }
//*/
        save();
    }

    public void save() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "4");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
