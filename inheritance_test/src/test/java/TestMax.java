import com.google.inject.Guice;
import com.google.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import javax.persistence.criteria.CriteriaDelete;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMax {

    @Inject
    private static CustomerGroup group;


    @Before
    public void initGroup() {

        group = Guice.createInjector(new TestModule())
                .getInstance(CustomerGroup.class);


    }

    @Test
    public void testOneCustomer() {

        group.registerCustomer(new Customer("1", 1, 100));

        Assert.assertEquals(100, App4.getMaxPayment(group));
    }

    @Test
    public void testSomeCustomers() {

        group.registerCustomer(new Customer("1", 1, 350));
        group.registerCustomer(new Customer("2", 2, 80));
        group.registerCustomer(new Customer("3", 3, 200));

        Assert.assertEquals(350, App4.getMaxPayment(group));
    }

    @Test
    public void testMaxWithUnregister() {

        Customer c = new Customer("1", 1, 350);

        group.registerCustomer(c);
        group.registerCustomer(new Customer("2", 2, 80));
        group.registerCustomer(new Customer("3", 3, 200));

        group.unregisterCustomer(c);

        Assert.assertEquals(200, App4.getMaxPayment(group));
    }

}
