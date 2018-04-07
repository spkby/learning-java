package by.itclass.Bank.bank.comission;

import by.itclass.Bank.bank.account.CreditMoneyAccount;
import by.itclass.Bank.bank.account.StockAccount;
import by.itclass.Bank.bank.value.Currency;
import by.itclass.Bank.bank.value.Money;
import by.itclass.Bank.bank.value.Stock;

public class StockCommission extends Commission<StockAccount> {

    private StockAccount stockAccount;
    private CreditMoneyAccount moneyAccount;

    public StockCommission(StockAccount stockAccount, CreditMoneyAccount moneyAccount) {
        if (stockAccount == null || !(stockAccount.balance() instanceof Stock)) {
            throw new IllegalArgumentException("Хранилище не должно отсутствовать или" +
                    "хранить что-то кроме акций");
        }

        if (moneyAccount == null) {
            throw new IllegalArgumentException("Обслуживающий счет не должен остсутствовать");
        }
        this.stockAccount = stockAccount;
        this.moneyAccount = moneyAccount;
    }

    @Override
    public void checkCommissionMonthly() {
        Money money = new Money(Currency.BYN, Math.ceil(stockAccount.balance().getAmount() / 100) * 0.1);
        moneyAccount.withdraw(money);
    }
}
