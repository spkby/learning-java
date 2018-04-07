package by.itclass.Bank.bank.io;


import by.itclass.Bank.bank.Client;
import by.itclass.Bank.bank.account.*;
import by.itclass.Bank.bank.comission.Commission;
import by.itclass.Bank.bank.comission.MetalCommission;
import by.itclass.Bank.bank.comission.StockCommission;

import java.util.*;

public abstract class FileIO {

    protected final String FILE_PATH = "FilesDB/";


//    private Bank<Account> bank;

    protected Set<Client> clients;
    protected Map<Long, Account> accounts;
    protected Map<Long, Long> serviceAccounts;
    protected Map<Client, Set<Long>> clientAccounts;
    protected long accountId;

    protected List<Commission<?>> commissions;

    public abstract void load();

    public abstract void save();

    public FileIO(/*Bank<Account> bank*/) {
        //this.bank = bank;

        clients = new HashSet<>();
        accounts = new HashMap<>();
        clientAccounts = new HashMap<>();
        serviceAccounts = new HashMap<>();
        commissions = new ArrayList<>();


    }

    public List<Commission<?>> getCommissions() {
        fillCommissions();
        return commissions;
    }

    public void setCommissions(List<Commission<?>> commissions) {
        this.commissions = commissions;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Map<Long, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<Long, Account> accounts) {
        this.accounts = accounts;
    }

    public Map<Long, Long> getServiceAccounts() {
        return serviceAccounts;
    }

    public void setServiceAccounts(Map<Long, Long> serviceAccounts) {
        this.serviceAccounts = serviceAccounts;
    }

    public Map<Client, Set<Long>> getClientAccounts() {
        return clientAccounts;
    }

    public void setClientAccounts(Map<Client, Set<Long>> clientAccounts) {
        this.clientAccounts = clientAccounts;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private void fillCommissions() {

        for (long idCell : serviceAccounts.keySet()) {
            long idCMA = serviceAccounts.get(idCell);

            if (accounts.get(idCell) instanceof StockAccount) {
                commissions.add(new StockCommission((StockAccount) accounts.get(idCell),
                        (CreditMoneyAccount) accounts.get(idCMA)));
            } else if (accounts.get(idCell) instanceof MetalAccount) {
                commissions.add(new MetalCommission((MetalAccount) accounts.get(idCell),
                        (CreditMoneyAccount) accounts.get(idCMA)));
            } else {
                throw new IllegalStateException("Не правильный тип счета при добавление комиссии");
            }
        }
    }

    protected int getType(Long id) {
        int type;
        if (accounts.get(id) instanceof DebitMoneyAccount) {
            type = 1;
        } else if (accounts.get(id) instanceof CreditMoneyAccount) {
            type = 2;
        } else if (accounts.get(id) instanceof PercentMoneyAccount) {
            type = 3;
        } else if (accounts.get(id) instanceof StockAccount) {
            type = 4;
        } else if (accounts.get(id) instanceof MetalAccount) {
            type = 5;
        } else {
            throw new IllegalStateException("Неверный тип счета");
        }
        return type;
    }

    protected int getPercent(Long id, int type) {
        switch (type) {
            case 2:
                return ((CreditMoneyAccount) accounts.get(id)).getPercent();
            case 3:
                return ((PercentMoneyAccount) accounts.get(id)).getPercent();
            default:
                throw new IllegalStateException("Неверный тип счета");
        }
    }
}
