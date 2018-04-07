package by.itclass.Bank.bank.comission;

import by.itclass.Bank.bank.account.CreditMoneyAccount;
import by.itclass.Bank.bank.account.MetalAccount;
import by.itclass.Bank.bank.value.Currency;
import by.itclass.Bank.bank.value.Metal;
import by.itclass.Bank.bank.value.Money;

public class MetalCommission extends Commission<MetalAccount> {

    private CreditMoneyAccount moneyAccount;
    private MetalAccount metalAccount;

    public MetalCommission(MetalAccount metalAccount, CreditMoneyAccount moneyAccount) {
        if (metalAccount == null || !(metalAccount.balance() instanceof Metal)) {
            throw new IllegalArgumentException("Хранилище не должно отсутствовать или" +
                    "хранить что-то кроме металла");
        }
        if (moneyAccount == null) {
            throw new IllegalArgumentException("Обслуживающий счет не должен остсутствовать");
        }
        this.metalAccount = metalAccount;
        this.moneyAccount = moneyAccount;
    }

    @Override
    public void checkCommissionMonthly() {
        Money money = new Money(Currency.BYN, Math.ceil(metalAccount.balance().getAmount() / 50) * 0.2);
        moneyAccount.withdraw(money);
    }
}
