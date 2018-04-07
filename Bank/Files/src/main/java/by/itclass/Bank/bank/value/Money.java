package by.itclass.Bank.bank.value;

public class Money {

    private double amount;
    private Currency currency;

    public Money(Currency currency, double amount) {
        this.amount = amount;
        if (currency == null) {
            throw new IllegalArgumentException("Не определен тип валюты");
        }
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        Prices price = new Prices();
        return price.getPriceMoney(currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        return currency == money.currency;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
