package by.itclass.Bank.bank.account;

import by.itclass.Bank.bank.value.Metal;
import by.itclass.Bank.bank.NotEnoughValuableException;

public class MetalAccount implements Account<Metal, MetalAccount> {

    private Metal metal;

    public MetalAccount(Metal metal) {
        this.metal = metal;
    }

    @Override
    public void deposit(Metal metal) {

        if (!this.metal.equals(metal)) {
            throw new IllegalArgumentException("Метал отличный от исходного");
        }

        int count = this.metal.getCount() + metal.getCount();
        this.metal = new Metal(this.metal.getType(), count);
    }

    @Override
    public void withdraw(Metal metal) {

        if (!this.metal.equals(metal)) {
            throw new IllegalArgumentException("Метал отличный от исходного");
        }

        if (this.metal.getCount() < metal.getCount()) {
            throw new NotEnoughValuableException();
        }

        int count = this.metal.getCount() - metal.getCount();
        this.metal = new Metal(this.metal.getType(), count);
    }

    @Override
    public void transfer(Metal metal, MetalAccount other) {

        if (other == null) {
            throw new IllegalArgumentException("Счет не может быть пустым");
        }

        if (other == this) {
            throw new IllegalArgumentException("Нельзя переводить на этот же счет");
        }

        this.withdraw(metal);
        other.deposit(metal);
    }

    @Override
    public Metal balance() {
        return metal;
    }

    @Override
    public String toString() {
        return "MetalAccount{" +
                "metal=" + metal +
                '}';
    }
}
