import com.google.inject.Guice;

import javax.inject.Inject;
import java.util.Set;

public class App4 {

    @Inject
    private static CustomerGroup group;

    public static void main(String[] args) {

        group = Guice.createInjector(new ProductionModule()).getInstance(CustomerGroup.class);

        group.registerCustomer(new Customer("Client 1", 1, 100));
        group.registerCustomer(new Customer("Client 2", 2, 200));

        group.notifyAllCustomers();

        System.out.println(getMaxPayment(group));
    }



    public static int getMaxPayment(CustomerGroup customerGroup) {
        Set<Customer> allCustomers = customerGroup.getAllCustomers();
        return allCustomers.stream()
                    .map(Customer::getPayment)
                    .reduce(Math::max).get();
    }
}
