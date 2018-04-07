package by.itclass.Bank.bank.comission;

import by.itclass.Bank.bank.account.MoneyAccount;
import by.itclass.Bank.bank.value.Currency;
import by.itclass.Bank.bank.value.Money;

public class AccountCommission extends Commission<MoneyAccount> {

    private final MoneyAccount account;

    public AccountCommission(MoneyAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Нельзя создать комиссию с пустым счетом");
        }
        this.account = account;
    }

    @Override
    public void checkCommissionMonthly() {

        Currency currency = account.balance().getCurrency();
        Money money = null;
        switch (currency) {
            case BYN:
                money = new Money(Currency.BYN, 0.01);
                break;
            case USD:
                money = new Money(Currency.USD, 0.02);
                break;
            case EUR:
                money = new Money(Currency.EUR, 0.02);
                break;
            case RUR:
                money = new Money(Currency.RUR, 0.5);
                break;
        }
        account.withdraw(money);
    }
}
