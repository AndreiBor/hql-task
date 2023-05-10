import by.itacademy.hibernate.entity.Payment;
import by.itacademy.hibernate.entity.User;
import by.itacademy.hibernate.util.HibernateUtil;
import by.itacademy.hibernate.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;

@Transactional
public class HinernateRunner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            //TestDataImporter.importData(sessionFactory);
            session.beginTransaction();

            var payment = session.find(Payment.class, 1L);
            payment.setAmount(payment.getAmount() + 10);

            session.getTransaction().commit();
        }
    }
}
