package by.itclass.Bank.bank;

import by.itclass.Bank.bank.account.*;
import by.itclass.Bank.bank.comission.AccountCommission;
import by.itclass.Bank.bank.comission.Commission;
import by.itclass.Bank.bank.comission.MetalCommission;
import by.itclass.Bank.bank.comission.StockCommission;
import by.itclass.Bank.bank.io.FileIO;
import by.itclass.Bank.bank.io.JSONFile;
import by.itclass.Bank.bank.io.TXTFile;
import by.itclass.Bank.bank.io.XMLFile;
import by.itclass.Bank.bank.value.Currency;
import by.itclass.Bank.bank.value.Metal;
import by.itclass.Bank.bank.value.Money;
import by.itclass.Bank.bank.value.Stock;

import java.util.*;

public class Bank<V> {

    private Set<Client> clients;
    private Map<Long, Account> accounts;
    private Map<Long, Long> serviceAccounts;
    private Map<Client, Set<Long>> clientAccounts;

    private List<Commission<?>> commissions;

    private long accountId = 1;

    public Bank() {
        clients = new HashSet<>();
        accounts = new HashMap<>();
        clientAccounts = new HashMap<>();
        serviceAccounts = new HashMap<>();
        commissions = new ArrayList<>();
    }

    /*
    регистрация клиента
     */
    public void registerClient(String firstName, String lastName, String passport) {
        if (searchClientByPassport(passport)) {
            throw new IllegalArgumentException("Клиент с таким паспортом уже существует");
        }
        Client client = new Client(firstName, lastName, passport);
        clients.add(client);
        clientAccounts.put(client, new HashSet<>());
    }

    /*
    новый дебетовый
     */
    public Long askForNewDebitMoneyAccount(Client client, Money money) {

        DebitMoneyAccount account = new DebitMoneyAccount(money);
        accounts.put(accountId, account);
        clientAccounts.get(client).add(accountId);
        commissions.add(new AccountCommission(account));
        return accountId++;
    }

    /*
    новый кредитный счет
     */
    public Long askForNewCreditMoneyAccount(Client client, Money money, int percent) {

        CreditMoneyAccount account = new CreditMoneyAccount(money, percent);
        accounts.put(accountId, account);
        clientAccounts.get(client).add(accountId);
        commissions.add(new AccountCommission(account));
        return accountId++;
    }

    /*
    новый процентный счет
     */
    public Long askForNewPercentMoneyAccount(Client client, Money money, int percent) {

        PercentMoneyAccount account = new PercentMoneyAccount(money, percent);
        accounts.put(accountId, account);
        clientAccounts.get(client).add(accountId);
        commissions.add(new AccountCommission(account));
        return accountId++;
    }

    /*
    пополнить счет
     */
    public void depositOnAccount(Client client, long id, V value) {
        if (client == null || !clients.contains(client)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }

        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }

        if (!clientAccounts.get(client).contains(id)) {
            throw new IllegalArgumentException("У клиента такого счета не существует");
        }

        accounts.get(id).deposit(value);
    }

    public int getTypeAccount(long id) {

        if (accounts.get(id) instanceof DebitMoneyAccount) {
            return 1;
        }
        if (accounts.get(id) instanceof CreditMoneyAccount) {
            return 2;
        }
        if (accounts.get(id) instanceof PercentMoneyAccount) {
            return 3;
        }
        if (accounts.get(id) instanceof StockAccount) {
            return 4;
        }
        if (accounts.get(id) instanceof MetalAccount) {
            return 5;
        }
        return -1;
    }

    public V generateValue(long id, double count) {
        switch (getTypeAccount(id)) {
            case 1:
            case 2:
            case 3:
                Money money = (Money) accounts.get(id).balance();
                return (V) new Money(money.getCurrency(), count);
            case 4:
                Stock stock = (Stock) accounts.get(id).balance();
                return (V) new Stock(stock.getCompany(), (int) count);
            case 5:
                Metal metal = (Metal) accounts.get(id).balance();
                return (V) new Metal(metal.getType(), (int) count);
        }
        return null;
    }

    /*
    снять со счета
     */
    public void withdrawFromAccount(Client client, long id, V value) {
        if (client == null || !clients.contains(client)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }
        if (!clientAccounts.get(client).contains(id)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        accounts.get(id).withdraw(value);
    }

    /*
    проверка клиента по паспорту
     */
    public boolean searchClientByPassport(String passport) {
        Client fakeClient = new Client("_", "_", passport);
        return this.clients.contains(fakeClient);
    }

    /*
    получение состояния счета
     */
    public V getAccountBalance(Client client, long id) {
        if (client == null || !clients.contains(client)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(id)) {
            throw new IllegalArgumentException("Такого счета не существует");
        }
        if (!clientAccounts.get(client).contains(id)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        return (V) accounts.get(id).balance();
    }

    /*
    перевод между счетами
     */
    public void transfer(Client client, long source, long destination, V value) {
        if (client == null || !clients.contains(client)) {
            throw new IllegalArgumentException("Нельзя передавать несуществующего клиента");
        }
        if (!accounts.containsKey(source) || !accounts.containsKey(destination)) {
            throw new IllegalArgumentException("Счета не существует");
        }
        if (!clientAccounts.get(client).contains(source)) {
            throw new IllegalArgumentException("Счет не принадлежит клиенту");
        }
        accounts.get(source).transfer(value, accounts.get(destination));
    }

    /*
    проверка наличия счета
     */
    public boolean searchAccountById(long id) {
        return this.accounts.containsKey(id);
    }

    /*
    новый счет-ячейка для акций
     */
    public long askForNewStockAccount(Client client, Stock stock) {
        StockAccount account = new StockAccount(stock);
        accounts.put(accountId, account);
        clientAccounts.get(client).add(accountId);
        long tmp = accountId++;
        long number = askForNewCreditMoneyAccount(client, new Money(Currency.BYN, 0), 0);
        putInMapper(tmp, number);
        commissions.add(new StockCommission(account, (CreditMoneyAccount) accounts.get(number)));
        return tmp;
    }

    /*
    новый счет-ячейка для драг металов
     */
    public long askForNewMetalAccount(Client client, Metal metal) {
        MetalAccount account = new MetalAccount(metal);
        accounts.put(accountId, account);
        clientAccounts.get(client).add(accountId);
        long tmp = accountId++;
        long number = askForNewCreditMoneyAccount(client, new Money(Currency.BYN, 0), 0);
        putInMapper(tmp, number);
        commissions.add(new MetalCommission(account, (CreditMoneyAccount) accounts.get(number)));
        return tmp;
    }

    /*
    связь между ячейкой и денежным счетом
     */
    public void putInMapper(long cell, long money) {
        serviceAccounts.put(cell, money);
    }

    /*

     */
    public long getFromMapper(long cell) {
        return serviceAccounts.get(cell);
    }

    /*
    получить список всех счетов клиента
     */
    public Set<Long> getArrayAccountId(Client client) {
        return clientAccounts.get(client);
    }

    public void checkCommissionsMonthly() {
        for (Commission<?> commission : this.commissions) {
            commission.checkCommissionMonthly();
        }
    }

    public void loadData(int type) {

        FileIO file = null;

        switch (type) {
            case 1:
                file = new TXTFile();
                break;
            case 3:
                file = new XMLFile();
                break;
            case 5:
                file = new JSONFile();
                break;
        }

        file.load();
        commissions = file.getCommissions();
        accountId = file.getAccountId();
        accounts = file.getAccounts();
        clientAccounts = file.getClientAccounts();
        clients = file.getClients();
        serviceAccounts = file.getServiceAccounts();
    }

    public void saveData(int type) {
        FileIO file = null;

        switch (type) {
            case 2:
                file = new TXTFile();
                break;
            case 4:
                file = new XMLFile();
                break;
            case 6:
                file = new JSONFile();
                break;
        }

        file.setAccountId(accountId);
        file.setAccounts(accounts);
        file.setClientAccounts(clientAccounts);
        file.setServiceAccounts(serviceAccounts);
        file.setCommissions(commissions);
        file.save();
    }
}
