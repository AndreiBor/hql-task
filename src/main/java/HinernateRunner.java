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
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            user = session.find(User.class, 1L);
            var user1 = session.find(User.class, 1L);
            System.out.println(user.getCompany());
            System.out.println(user.getUserChats());
            session.getTransaction().commit();
        }
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user2 = session.find(User.class, 1L);
            System.out.println(user2.getCompany());
           // System.out.println(user.getUserChats().size());
            System.out.println(user.getUserChats());
            session.getTransaction().commit();
        }
    }
}
