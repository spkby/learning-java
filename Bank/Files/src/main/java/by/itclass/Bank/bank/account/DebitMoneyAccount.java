package by.itclass.Bank.bank.account;

import by.itclass.Bank.bank.value.Money;
import by.itclass.Bank.bank.NotEnoughValuableException;

public class DebitMoneyAccount extends MoneyAccount {

    public DebitMoneyAccount(Money money) {
        super(money);
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
        return "DebitMoneyAccount{" +
                "money=" + money +
                '}';
    }
}
