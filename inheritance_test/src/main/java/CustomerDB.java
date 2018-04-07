import com.google.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class CustomerDB implements CustomerGroup {

    private Session session;

    @Inject
    public CustomerDB(SessionFactory factory) {
        this.session = factory.openSession();
    }

    @Override
    public void registerCustomer(Customer customer) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void unregisterCustomer(Customer customer) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(customer);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void notifyAllCustomers() {

    }

    @Override
    public Set<Customer> getAllCustomers() {
        return new HashSet<>(session.createQuery("FROM Customer", Customer.class)
                .list());
    }
}
