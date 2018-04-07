package by.itclass.Bank.bank.account;

import by.itclass.Bank.bank.value.Money;

public abstract class MoneyAccount implements Account<Money, MoneyAccount> {

    protected Money money;

    public MoneyAccount(Money money) {
        this.money = money;
    }

    @Override
    public void deposit(Money money) {
        if (!this.money.equals(money)) {
            throw new IllegalArgumentException("Не так валюта");
        }
        if (money.getAmount() <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше 0");
        }
        this.money.setAmount(this.money.getAmount() + money.getAmount());
    }

    @Override
    public abstract void withdraw(Money money);

    @Override
    public void transfer(Money money, MoneyAccount other) {

        if (!this.money.equals(money)) {
            throw new IllegalArgumentException("Не так валюта");
        }
        if (money.getAmount() <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше 0");
        }

        if (other == null) {
            throw new IllegalArgumentException("Счет не может быть пустым");
        }

        if (other == this) {
            throw new IllegalArgumentException("Нельзя перреводить на этот же счет");
        }

        // перевод
        this.withdraw(money);

        double resultAmount = money.getAmount() * this.money.getPrice() / other.money.getPrice();

        other.deposit(new Money(other.money.getCurrency(), resultAmount));
    }

    @Override
    public Money balance() {
        return money;
    }
}
