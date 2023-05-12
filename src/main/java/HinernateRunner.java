import by.itacademy.hibernate.dao.PaymentRepository;
import by.itacademy.hibernate.entity.Payment;
import by.itacademy.hibernate.entity.User;
import by.itacademy.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.transaction.Transactional;

@Transactional
public class HinernateRunner {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            var paymentRepository = new PaymentRepository(sessionFactory);
            var payment = paymentRepository.findById(1L).get();
            System.out.println(payment);

            session.getTransaction().commit();
        }
    }
}
