import entities.Employee;
//import entities.Generics;
import entities.Human;
import entities.Student;
import org.xml.sax.SAXException;
import parser.DomParser;
import parser.SaxParser;
import parser.XsdValidation;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException,
            TransformerException, XPathExpressionException {

        System.out.println(XsdValidation.validateAgainstXSD(new FileInputStream("humans.xml")
                , new FileInputStream("human.xsd")));

/*/
        DomParser parser = new DomParser("humans.xml");
/*/
        SaxParser parser = new SaxParser("humans.xml");
//*/

//        Human human = new Employee("Svetlana", "Petrova", 19, 3);
//        parser.create(human);

        List<Human> humans = parser.read();

//        parser.delete(humans.get(5));
//        humans.get(4).setFirstName("Elena");
//        ((Student)humans.get(4)).setGroup("T-3");
//        parser.update(humans.get(4));

        for (Human item : humans) {
            System.out.println(item);
        }
    }
}
