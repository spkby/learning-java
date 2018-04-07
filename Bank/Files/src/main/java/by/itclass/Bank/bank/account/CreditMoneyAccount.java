package by.itclass.Bank.bank.account;

import by.itclass.Bank.bank.value.Money;

public class CreditMoneyAccount extends MoneyAccount {

    private final int percent;

    public int getPercent() {
        return percent;
    }

    public CreditMoneyAccount(Money money, int percent) {
        super(money);
        if (percent < 0) {
            throw new IllegalArgumentException("Процент должен быть положительным");
        }
        this.percent = percent;
    }

    @Override
    public void withdraw(Money money) {
        if (!this.money.equals(money)) {
            throw new IllegalArgumentException("Не так валюта");
        }
        if (money.getAmount() <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше 0");
        }

        double amount = this.money.getAmount() - money.getAmount();

        this.money = new Money(money.getCurrency(), amount);
    }

    public void recalcDayOutcome() {

        if (money.getAmount() < 0) {
            double amount = money.getAmount() - Math.abs(money.getAmount()) * (percent / 30) / 100;
            money = new Money(money.getCurrency(), amount);
        }
    }

    @Override
    public String toString() {
        return "CreditMoneyAccount{" +
                "money=" + money +
                ", percent=" + percent +
                '}';
    }
}
