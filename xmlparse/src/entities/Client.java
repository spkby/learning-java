package entities;

import java.util.UUID;

public class Client extends Human {
    private String order;

    public Client(String firstName, String lastName, int age, Passport passport, String order) {
        super(firstName, lastName, age, passport);
        this.order = order;
    }

    public Client(UUID uid, String firstName, String lastName, int age, Passport passport, String order) {
        super(uid, firstName, lastName, age, passport);
        this.order = order;
    }

    public Client() {
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() +
                ", order='" + order + '\'' +
                '}';
    }
}
