import by.itacademy.hibernate.entity.Payment;
import by.itacademy.hibernate.entity.User;
import by.itacademy.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;

@Transactional
public class HinernateRunner {
    public static void main(String[] args) {
        User user = null;

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            user = session.find(User.class, 1L);
            System.out.println(user);
            session.getTransaction().commit();
        }
    }
}
