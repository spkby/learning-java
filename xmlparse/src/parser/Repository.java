package parser;

import entities.Human;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.util.List;

public interface Repository {
    List<Human> read();

    void create(Human item);

    void update(Human item);

    void delete(Human item);
}
