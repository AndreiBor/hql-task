import by.itacademy.hibernate.dao.PaymentRepository;
import by.itacademy.hibernate.entity.Payment;
import by.itacademy.hibernate.entity.User;
import by.itacademy.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.lang.reflect.Proxy;

@Transactional
public class HinernateRunner {
    public static void main(String[] args) {


        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            session.beginTransaction();

            var paymentRepository = new PaymentRepository(session);
            var payment = paymentRepository.findById(1L).get();
            System.out.println(payment);

            session.getTransaction().commit();
        }
    }
}
