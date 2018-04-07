package by.itclass.Bank.bank.value;

import java.util.HashSet;

public class Stocks extends HashSet<String> {

    public Stocks() {
        fillStocks();
    }

    public void fillStocks() {

        add("APPL");
        add("GOOGL");
        add("MSFT");
    }
}
