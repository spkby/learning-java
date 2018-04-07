package by.itclass.Bank.bank.io;

import by.itclass.Bank.bank.Client;
import by.itclass.Bank.bank.account.MetalAccount;
import by.itclass.Bank.bank.account.MoneyAccount;
import by.itclass.Bank.bank.account.StockAccount;
import com.fasterxml.jackson.core.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class JSONFile extends FileIO {

    @Override
    public void load() {

        String json = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + "bankdb.json"))) {
            json = reader.readLine();
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка чтения Json файла");
        }

        JsonFactory factory = new JsonFactory();
        JsonParser parser = null;
        try {
            parser = factory.createParser(json);

            while (!parser.isClosed()) {
                JsonToken token = parser.nextToken();
                if (JsonToken.FIELD_NAME.equals(token)) {
                    switch (parser.getCurrentName()) {
                        case "Accounts":
                            parser.nextToken();
                            loadAccounts(parser);
                            break;
                        case "ClientAccounts":
                            parser.nextToken();
                            loadClientAccounts(parser);
                            break;
                        case "ServiceAccounts":
                            parser.nextToken();
                            loadServiceAccounts(parser);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator generator = factory.createGenerator(
                new File(FILE_PATH + "bankdb.json"), JsonEncoding.UTF8)) {

            generator.writeStartObject();
            saveServiceAccounts(generator);
            saveClientAccounts(generator);
            saveAccounts(generator);

            generator.writeEndObject();
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка записи Json файла");
        }
    }

    private void saveAccounts(JsonGenerator generator) throws IOException {
        generator.writeObjectFieldStart("Accounts");
        for (Long id : accounts.keySet()) {

            int type = getType(id);

            int integer = 0;

            switch (type) {
                case 1:
                case 2:
                case 3:
                    double amount = ((MoneyAccount) accounts.get(id)).balance().getAmount();
                    String currency = ((MoneyAccount) accounts.get(id)).balance().getCurrency().toString();
                    integer = getPercent(id, type);

                    generator.writeObjectFieldStart(String.valueOf(type));
                    generator.writeObjectField("id", id);
                    generator.writeObjectField("Amount", amount);
                    generator.writeObjectField("Currency", currency);
                    generator.writeObjectField("Percent", integer);
                    break;
                case 4:
                    String company = ((StockAccount) accounts.get(id)).balance().getCompany();
                    integer = ((StockAccount) accounts.get(id)).balance().getCount();

                    generator.writeObjectFieldStart(String.valueOf(type));
                    generator.writeObjectField("id", id);
                    generator.writeObjectField("Company", company);
                    generator.writeObjectField("Quantity", integer);
                    break;
                case 5:
                    String metal = ((MetalAccount) accounts.get(id)).balance().getType().toString();
                    integer = ((MetalAccount) accounts.get(id)).balance().getCount();

                    generator.writeObjectFieldStart(String.valueOf(type));
                    generator.writeObjectField("id", id);
                    generator.writeObjectField("Metal", metal);
                    generator.writeObjectField("Quantity", integer);
                    break;
            }
            generator.writeEndObject();
        }
    }

    private void saveClientAccounts(JsonGenerator generator) throws IOException {

        generator.writeObjectFieldStart("ClientAccounts");
        for (Client client : clientAccounts.keySet()) {
            for (long account : clientAccounts.get(client)) {
                generator.writeObjectFieldStart(String.valueOf(account));
                generator.writeObjectField("Firstname", client.getFirstName());
                generator.writeObjectField("Lastname", client.getLastName());
                generator.writeObjectField("Passport", client.getPassport());
                generator.writeEndObject();
            }
        }
        generator.writeEndObject();
    }

    private void saveServiceAccounts(JsonGenerator generator) throws IOException {
        generator.writeObjectFieldStart("ServiceAccounts");
        for (long id : serviceAccounts.keySet()) {
            generator.writeObjectField(String.valueOf(id), serviceAccounts.get(id));
        }
        generator.writeEndObject();
    }

    private void loadAccounts(JsonParser parser) {
        //TODO: Json.loadAccounts
    }

    private void loadClientAccounts(JsonParser parser) {
        try {
            JsonToken token = parser.nextToken();

            while (!JsonToken.END_OBJECT.equals(token)) {
                while (!JsonToken.END_OBJECT.equals(token)) {
                    // FIELD_NAME
                    long id = Long.parseLong(parser.getCurrentName());

                    while (!JsonToken.VALUE_STRING.equals(token)) {
                        token = parser.nextToken();
                    }

                    String firstName = parser.getValueAsString();
                    parser.nextToken(); // FIELD_NAME
                    parser.nextToken();
                    String lastName = parser.getValueAsString();
                    parser.nextToken();// FIELD_NAME
                    parser.nextToken();
                    String passport = parser.getValueAsString();

                    Client client = new Client(firstName, lastName, passport);

                    if (clientAccounts.containsKey(client) == false) {
                        clients.add(client);
                        clientAccounts.put(client, new HashSet<>());
                    }
                    clientAccounts.get(client).add(id);

                    token = parser.nextToken();
                }
                token = parser.nextToken();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка парсинга Json файла");
        }

    }

    private void loadServiceAccounts(JsonParser parser) {
        try {
            JsonToken token = parser.nextToken();
            while (!JsonToken.END_OBJECT.equals(token)) {
                long idCell = Long.parseLong(parser.getCurrentName());
                parser.nextToken();
                long idCredit = parser.getLongValue();
                serviceAccounts.put(idCell, idCredit);
                token = parser.nextToken();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка парсинга Json файла");
        }
    }
}
