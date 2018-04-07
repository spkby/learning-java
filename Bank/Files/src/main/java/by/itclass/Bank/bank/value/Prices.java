package by.itclass.Bank.bank.value;

import java.util.HashMap;

public class Prices {

    private HashMap<String, Double> priceStocks = new HashMap<>();
    private HashMap<Metals, Double> priceMetals = new HashMap<>();
    private HashMap<Currency, Double> priceMoney = new HashMap<>();

    public Prices() {
        fillMetals();
        fillStocks();
        fillMoney();
    }

    private void fillStocks() {
        Stocks stocks = new Stocks();
        for (String str : stocks) {
            priceStocks.put(str, 100d);
        }
    }

    private void fillMetals() {
        priceMetals.put(Metals.GOLD, 77.30);
        priceMetals.put(Metals.SILVER, 1.01);
        priceMetals.put(Metals.PLATINUM, 55.47);
        priceMetals.put(Metals.PALLADIUM, 56.18);
    }

    private void fillMoney() {
        priceMoney.put(Currency.BYN, 1.0);
        priceMoney.put(Currency.RUR, 0.033810);
        priceMoney.put(Currency.EUR, 2.2950);
        priceMoney.put(Currency.USD, 1.9900);
    }

    public double getPriceMoney(Currency currency) {
        return priceMoney.get(currency);
    }

    public double getPriceMetal(Metals metal) {
        return priceMetals.get(metal);
    }

    public double getPriceStock(String company) {
        return priceStocks.get(company);
    }
}
