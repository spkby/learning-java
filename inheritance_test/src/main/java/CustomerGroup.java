import javax.persistence.*;
import java.util.Set;

public interface CustomerGroup {

    void registerCustomer(Customer customer);
    void unregisterCustomer(Customer customer);
    void notifyAllCustomers();
    Set<Customer> getAllCustomers();

}

@Entity
@Table(name = "customers")
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;
    private int number;
    private int payment;

    public Customer() {
    }

    public Customer(String name, int number, int payment) {
        this.name = name;
        this.number = number;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (number != customer.number) return false;
        if (payment != customer.payment) return false;
        return name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + number;
        result = 31 * result + payment;
        return result;
    }
}
