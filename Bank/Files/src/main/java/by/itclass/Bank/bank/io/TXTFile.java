package by.itclass.Bank.bank.io;

import by.itclass.Bank.bank.account.*;
import by.itclass.Bank.bank.comission.AccountCommission;
import by.itclass.Bank.bank.value.*;
import by.itclass.Bank.bank.Client;

import java.io.*;
import java.util.HashSet;


public class TXTFile extends FileIO {

    private void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + "accounts.txt"))) {

            String line = null;
            accountId = 1;

            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");

                long id = Long.parseLong(strings[1]);

                Account account = null;

                switch (strings[0]){
                    case "1":
                        account = new DebitMoneyAccount(new Money(Convertor.toCurrency(strings[3]),
                                Double.parseDouble(strings[2])));
                        commissions.add(new AccountCommission((MoneyAccount) account));
                        break;
                    case "2":
                        account = new CreditMoneyAccount(new Money(Convertor.toCurrency(strings[3]),
                                Double.parseDouble(strings[2])),Integer.parseInt(strings[4]));
                        commissions.add(new AccountCommission((MoneyAccount) account));
                        break;
                    case "3":
                        account = new PercentMoneyAccount(new Money(Convertor.toCurrency(strings[3]),
                                Double.parseDouble(strings[2])),Integer.parseInt(strings[4]));
                        commissions.add(new AccountCommission((MoneyAccount) account));
                        break;
                    case "4":
                        account = new StockAccount(new Stock(strings[2],Integer.parseInt(strings[3])));
                        break;
                    case "5":
                        account = new MetalAccount(new Metal(Convertor.toMetal(strings[2]),
                                Integer.parseInt(strings[3])));
                        break;
                }
                accounts.put(Long.parseLong(strings[1]), account);
                accountId++;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + "accounts.txt"))) {
            for (Long id : accounts.keySet()) {

                int type = getType(id);

                int integer = 0;

                switch (type) {
                    case 1:
                    case 2:
                    case 3:
                        double amount = ((MoneyAccount) accounts.get(id)).balance().getAmount();
                        Currency currency = ((MoneyAccount) accounts.get(id)).balance().getCurrency();
                        integer = getPercent(id, type);
                        writer.write(type + "," + id + "," + amount + "," + currency + "," + integer);
                        writer.newLine();
                        break;
                    case 4:
                        String company = ((StockAccount) accounts.get(id)).balance().getCompany();
                        integer = ((StockAccount) accounts.get(id)).balance().getCount();
                        writer.write(type + "," + id + "," + company + "," + integer);
                        writer.newLine();
                        break;
                    case 5:
                        Metals metal = ((MetalAccount) accounts.get(id)).balance().getType();
                        integer = ((MetalAccount) accounts.get(id)).balance().getCount();
                        writer.write(type + "," + id + "," + metal + "," + integer);
                        writer.newLine();
                        break;
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void loadServiceAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + "serviceaccounts.txt"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                serviceAccounts.put(Long.parseLong(strings[0]), Long.parseLong(strings[1]));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void saveServiceAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + "serviceaccounts.txt"))) {

            for (long id : serviceAccounts.keySet()) {
                writer.write(id + "," + serviceAccounts.get(id));
                writer.newLine();
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void loadClientAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH + "clientaccounts.txt"))) {

            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");

                Client client = new Client(strings[0], strings[1], strings[2]);

                if (clientAccounts.containsKey(client) == false) {
                    clients.add(client);
                    clientAccounts.put(client, new HashSet<>());
                }
                clientAccounts.get(client).add(Long.parseLong(strings[3]));
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void saveClientAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + "clientaccounts.txt"))) {

            for (Client client : clientAccounts.keySet()) {
                for (long account : clientAccounts.get(client)) {
                    writer.write(client.getFirstName() + "," + client.getLastName() + ","
                            + client.getPassport() + "," + account);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void load() {
        loadAccounts();
        loadClientAccounts();
        loadServiceAccounts();
    }

    @Override
    public void save() {
        saveAccounts();
        saveClientAccounts();
        saveServiceAccounts();
    }
}

