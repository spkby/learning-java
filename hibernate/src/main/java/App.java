import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;

import java.util.Date;
import java.util.List;

public class App {


    public static void main(String[] args) {


        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Salary.class);
        configuration.addAnnotatedClass(Workplace.class);


        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // HQL
            //Query<Integer> query =  session.createQuery("select 1 * 1;",Integer.class);

            // SQL
            Query query = session.createNativeQuery("select 1 + 1;");

            List result = query.getResultList();

            if (result.size() == 1) {
                System.out.print("Success - ");
                System.out.println(result.get(0));
                System.out.println(result.get(0).getClass());
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        try {
            transaction = session.beginTransaction();

            Worker worker = session.get(Worker.class, 1);

            System.out.println(worker);


            Worker worker1 = new Worker();
            worker1.setName("Oleg");
            worker1.setSurname("Olegovich");
            worker1.setBirthdate(new Date(1985 - 1900, 05, 16));

            Integer id = (Integer) session.save(worker1);
            System.out.println(id);


            worker.setName("Ivan Ivanovich");
            session.update(worker);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        try {
            transaction = session.beginTransaction();

            Department dep = session.get(Department.class, 1);

            System.out.println(dep);

            //Query<Department> query = session.createQuery("From Department",Department.class);
            //Query<Department> query = session.createQuery("From Department WHERE id > 3 ORDER BY name DESC",
            //       Department.class);
            Query<Department> query = session.createQuery("From Department", Department.class);
            query.setFirstResult(2);
            query.setMaxResults(2);

            List<Department> departments = query.getResultList();
            for (Department department : departments) {
                System.out.println(department);
            }

            Criteria criteria = session.createCriteria(Department.class);
            criteria.add(Restrictions.or(Restrictions.like("name", "I%"),
                    Restrictions.gt("id", 4)));
            List l = criteria.list();

            for (int i = 0; i < l.size(); i++) {
                System.out.println(l.get(i));
            }


            List<Salary> salaries = session.createQuery("from Salary",Salary.class).getResultList();

            for (Salary salary : salaries) {
                System.out.println(salary);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        factory.close();
    }

}
