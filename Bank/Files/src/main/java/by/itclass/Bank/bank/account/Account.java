package by.itclass.Bank.bank.account;

public interface Account<V, A> {

    V balance();

    void deposit(V val);

    void withdraw(V val);

    void transfer(V val, A account);

}
