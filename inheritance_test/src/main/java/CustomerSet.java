import java.util.HashSet;
import java.util.Set;

public class CustomerSet implements CustomerGroup {

    private Set<Customer> customers;

    public CustomerSet() {
        customers = new HashSet<>();
    }


    @Override
    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void unregisterCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public void notifyAllCustomers() {
        for (Customer c : customers) {
            System.out.println(c.getName() + " оповещен");
        }
    }

    @Override
    public Set<Customer> getAllCustomers() {
        return new HashSet<>(customers);
    }


}
