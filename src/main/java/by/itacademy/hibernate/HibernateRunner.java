package by.itacademy.hibernate;

import by.itacademy.hibernate.entity.Payment;
import by.itacademy.hibernate.entity.User;
import by.itacademy.hibernate.entity.UserChat;
import by.itacademy.hibernate.util.HibernateUtil;
import by.itacademy.hibernate.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;

import javax.persistence.LockModeType;
import java.util.Map;

public class HibernateRunner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            //TestDataImporter.importData(sessionFactory);
            session.beginTransaction();
            var payment = session.find(Payment.class, 1L);
            payment.setAmount(payment.getAmount() + 10);

            session.getTransaction().commit();
        }
    }
}
