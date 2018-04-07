package by.itclass.Bank.bank.io;

import by.itclass.Bank.bank.Client;
import by.itclass.Bank.bank.account.*;
import by.itclass.Bank.bank.comission.AccountCommission;
import by.itclass.Bank.bank.value.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;

public class XMLFile extends FileIO {

    @Override
    public void load() {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(FILE_PATH + "bankdb.xml");
            Element root = document.getDocumentElement();

            loadAccounts(root);
            loadClientAccounts(root);
            loadServiceAccounts(root);

        } catch (Exception e) {
            throw new IllegalStateException("Ошибка чтения XML-файла");
        }
    }

    @Override
    public void save() {
        try {
            Document dom;

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
            Element bankE = dom.createElement("Bank");

            bankE.appendChild(saveServiceAccounts(dom));
            bankE.appendChild(saveClientAccounts(dom));
            bankE.appendChild(saveAccounts(dom));

            dom.appendChild(bankE);

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            FileOutputStream os = new FileOutputStream(FILE_PATH + "bankdb.xml");
            tr.transform(new DOMSource(dom), new StreamResult(os));
            os.flush();
            os.close();
        } catch (ParserConfigurationException | TransformerException | IOException pce) {
            throw new IllegalStateException("Ошибка при записи XML-файла");
        }
    }

    private void loadAccounts(Element root) {

        NodeList unitList = root.getElementsByTagName("Account");

        accountId = 1;
        for (int i = 0; i < unitList.getLength(); i++) {
            Node node = unitList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;

                Account account = null;
                long id = 0;
                switch (element.getAttribute("type")) {
                    case "1":
                        id = Long.parseLong(element.getElementsByTagName("ID").
                                item(0).getTextContent());
                        double amount = Double.parseDouble(element.getElementsByTagName("Amount").
                                item(0).getTextContent());
                        Currency currency = Convertor.toCurrency(element.getElementsByTagName("Currency").
                                item(0).getTextContent());
                        account = new DebitMoneyAccount(new Money(currency, amount));
                        commissions.add(new AccountCommission((MoneyAccount) account));
                        break;
                    case "2":
                        id = Long.parseLong(element.getElementsByTagName("ID").
                                item(0).getTextContent());
                        amount = Double.parseDouble(element.getElementsByTagName("Amount").
                                item(0).getTextContent());
                        currency = Convertor.toCurrency(element.getElementsByTagName("Currency").
                                item(0).getTextContent());
                        int percent = Integer.parseInt(element.getElementsByTagName("Percent").
                                item(0).getTextContent());
                        account = new CreditMoneyAccount(new Money(currency, amount), percent);
                        commissions.add(new AccountCommission((MoneyAccount) account));
                        break;
                    case "3":
                        id = Long.parseLong(element.getElementsByTagName("ID").
                                item(0).getTextContent());
                        amount = Double.parseDouble(element.getElementsByTagName("Amount").
                                item(0).getTextContent());
                        currency = Convertor.toCurrency(element.getElementsByTagName("Currency").
                                item(0).getTextContent());
                        percent = Integer.parseInt(element.getElementsByTagName("Percent").
                                item(0).getTextContent());
                        account = new PercentMoneyAccount(new Money(currency, amount), percent);
                        commissions.add(new AccountCommission((MoneyAccount) account));
                        break;
                    case "4":
                        id = Long.parseLong(element.getElementsByTagName("ID").
                                item(0).getTextContent());
                        String str = element.getElementsByTagName("Company").
                                item(0).getTextContent();
                        int quantity = Integer.parseInt(element.getElementsByTagName("Quantity").
                                item(0).getTextContent());
                        account = new StockAccount(new Stock(str, quantity));
                        break;
                    case "5":
                        id = Long.parseLong(element.getElementsByTagName("ID").
                                item(0).getTextContent());
                        str = element.getElementsByTagName("Metal").
                                item(0).getTextContent();
                        quantity = Integer.parseInt(element.getElementsByTagName("Quantity").
                                item(0).getTextContent());
                        account = new MetalAccount(new Metal(Convertor.toMetal(str), quantity));
                        break;
                }
                accounts.put(id, account);
                accountId++;
            }
        }
    }

    private void loadServiceAccounts(Element root) {

        NodeList unitList = root.getElementsByTagName("ServiceAccount");

        for (int i = 0; i < unitList.getLength(); i++) {
            Node node = unitList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;

                long cellAccount = Long.parseLong(element.getElementsByTagName("CellAccount").
                        item(0).getTextContent());
                long creditAccount = Long.parseLong(element.getElementsByTagName("CreditAccount").
                        item(0).getTextContent());

                serviceAccounts.put(cellAccount, creditAccount);
            }
        }
    }

    private void loadClientAccounts(Element root) {

        NodeList unitList = root.getElementsByTagName("ClientAccount");

        for (int i = 0; i < unitList.getLength(); i++) {
            Node node = unitList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;

                Client client = new Client(
                        element.getElementsByTagName("Firstname").item(0).getTextContent(),
                        element.getElementsByTagName("Lastname").item(0).getTextContent(),
                        element.getElementsByTagName("Passport").item(0).getTextContent());

                if (clientAccounts.containsKey(client) == false) {
                    clients.add(client);
                    clientAccounts.put(client, new HashSet<>());
                }
                clientAccounts.get(client).add(Long.parseLong(element.
                        getElementsByTagName("AccountID").item(0).getTextContent()));
            }
        }
    }

    private Element saveServiceAccounts(Document dom) {

        Element saE = dom.createElement("ServiceAccounts");
        Element e = null;

        for (long id : serviceAccounts.keySet()) {
            e = dom.createElement("ServiceAccount");

            Element cellAccount = dom.createElement("CellAccount");
            cellAccount.setTextContent(Long.toString(id));
            e.appendChild(cellAccount);

            Element creditAccount = dom.createElement("CreditAccount");
            creditAccount.setTextContent(Long.toString(serviceAccounts.get(id)));
            e.appendChild(creditAccount);

            saE.appendChild(e);
        }
        return saE;
    }

    private Element saveClientAccounts(Document dom) {

        Element caE = dom.createElement("ClientAccounts");
        Element e = null;

        for (Client client : clientAccounts.keySet()) {
            for (long account : clientAccounts.get(client)) {

                e = dom.createElement("ClientAccount");

                Element firstname = dom.createElement("Firstname");
                firstname.setTextContent(client.getFirstName());
                e.appendChild(firstname);

                Element lastname = dom.createElement("Lastname");
                lastname.setTextContent(client.getLastName());
                e.appendChild(lastname);

                Element passport = dom.createElement("Passport");
                passport.setTextContent(client.getPassport());
                e.appendChild(passport);

                Element accountID = dom.createElement("AccountID");
                accountID.setTextContent(Long.toString(account));
                e.appendChild(accountID);

                caE.appendChild(e);
            }
        }
        return caE;
    }

    private Element saveAccounts(Document dom) {

        Element aE = dom.createElement("Accounts");
        Element e = null;

        for (Long id : accounts.keySet()) {

            int type = getType(id);

            int integer = 0;

            switch (type) {
                case 1:
                case 2:
                case 3:
                    integer = 0;
                    double amount = ((MoneyAccount) accounts.get(id)).balance().getAmount();
                    String currency = ((MoneyAccount) accounts.get(id)).balance().getCurrency().toString();
                    integer = getPercent(id, type);

                    e = dom.createElement("Account");
                    e.setAttribute("type", String.valueOf(type));

                    Element idE = dom.createElement("ID");
                    idE.setTextContent(String.valueOf(id));
                    e.appendChild(idE);

                    Element amountE = dom.createElement("Amount");
                    amountE.setTextContent(String.valueOf(amount));
                    e.appendChild(amountE);

                    Element currencyE = dom.createElement("Currency");
                    currencyE.setTextContent(currency);
                    e.appendChild(currencyE);

                    Element integerE = dom.createElement("Percent");
                    integerE.setTextContent(String.valueOf(integer));
                    e.appendChild(integerE);

                    aE.appendChild(e);
                    break;
                case 4:
                    String company = ((StockAccount) accounts.get(id)).balance().getCompany();
                    integer = ((StockAccount) accounts.get(id)).balance().getCount();

                    e = dom.createElement("Account");
                    e.setAttribute("type", String.valueOf(type));

                    idE = dom.createElement("ID");
                    idE.setTextContent(String.valueOf(id));
                    e.appendChild(idE);

                    Element companyE = dom.createElement("Company");
                    companyE.setTextContent(company);
                    e.appendChild(companyE);

                    integerE = dom.createElement("Quantity");
                    integerE.setTextContent(String.valueOf(integer));
                    e.appendChild(integerE);

                    aE.appendChild(e);
                    break;
                case 5:
                    String metal = ((MetalAccount) accounts.get(id)).balance().getType().toString();
                    integer = ((MetalAccount) accounts.get(id)).balance().getCount();

                    e = dom.createElement("Account");
                    e.setAttribute("type", String.valueOf(type));

                    idE = dom.createElement("ID");
                    idE.setTextContent(String.valueOf(id));
                    e.appendChild(idE);

                    Element metalE = dom.createElement("Metal");
                    metalE.setTextContent(metal);
                    e.appendChild(metalE);

                    integerE = dom.createElement("Quantity");
                    integerE.setTextContent(String.valueOf(integer));
                    e.appendChild(integerE);

                    aE.appendChild(e);
                    break;
            }
        }
        return aE;
    }

}
