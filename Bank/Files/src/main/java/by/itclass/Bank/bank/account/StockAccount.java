package by.itclass.Bank.bank.account;

import by.itclass.Bank.bank.value.Stock;
import by.itclass.Bank.bank.NotEnoughValuableException;

public class StockAccount implements Account<Stock, StockAccount> {

    private Stock stock;

    public StockAccount(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void deposit(Stock stock) {
        if (!this.stock.equals(stock)) {
            throw new IllegalArgumentException("Акции не той компании");
        }
        this.stock.setCount(this.stock.getCount() + stock.getCount());
    }

    public void withdraw(Stock stock) {
        if (!this.stock.equals(stock)) {
            throw new IllegalArgumentException("Акции не той компании");
        }

        if (this.stock.getCount() < stock.getCount()) {
            throw new NotEnoughValuableException();
        }
        this.stock.setCount(this.stock.getCount() - stock.getCount());
    }

    @Override
    public void transfer(Stock stock, StockAccount other) {

        if (other == null) {
            throw new IllegalArgumentException("Счет не может быть пустым");
        }

        if (other == this) {
            throw new IllegalArgumentException("Нельзя переводить на этот же счет");
        }

        this.withdraw(stock);
        other.deposit(stock);
    }

    @Override
    public Stock balance() {
        return stock;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockAccount that = (StockAccount) o;

        return stock.equals(that.stock);
    }

    @Override
    public String toString() {
        return "StockAccount{" +
                "stock=" + stock +
                '}';
    }
}
