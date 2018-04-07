import com.google.inject.AbstractModule;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProductionModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomerGroup.class).to(CustomerDB.class);
        bind(SessionFactory.class).toInstance(initHibernate());
    }

    private static SessionFactory initHibernate() {

        //Класс настроек библиотеки Hibernate
        Configuration configuration = new Configuration();
        //Загрузка настроек из xml-файла
        configuration.configure("hibernate.cfg.xml");

        //Добавить класс, у которого есть специальные JPA-аннотации
        configuration.addAnnotatedClass(Customer.class);

        return configuration.buildSessionFactory();
    }
}
