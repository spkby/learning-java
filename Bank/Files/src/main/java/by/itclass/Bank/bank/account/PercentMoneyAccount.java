package by.itclass.Bank.bank.account;

import by.itclass.Bank.bank.value.Money;
import by.itclass.Bank.bank.NotEnoughValuableException;

public class PercentMoneyAccount extends MoneyAccount {

    private final int percent;

    public int getPercent() {
        return percent;
    }

    public PercentMoneyAccount(Money money, int percent) {
        super(money);
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент должен быть положительным");
        }
        this.percent = percent;
    }

    public void recalcDayIncome() {
        if (money.getAmount() > 0) {
            double amount = money.getAmount() + percent * money.getAmount() / 100;
            money = new Money(money.getCurrency(), amount);
        }
    }

    @Override
    public void withdraw(Money money) {
        if (!this.money.equals(money)) {
            throw new IllegalArgumentException("Не так валюта");
        }
        if (money.getAmount() <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше 0");
        }

        if (this.money.getAmount() < money.getAmount()) {
            throw new NotEnoughValuableException();
        }

        double amount = this.money.getAmount() - money.getAmount();

        this.money = new Money(money.getCurrency(), amount);
    }

    @Override
    public String toString() {
        return "PercentMoneyAccount{" +
                "money=" + money +
                ", percent=" + percent +
                '}';
    }
}
