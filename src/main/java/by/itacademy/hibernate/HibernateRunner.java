package by.itacademy.hibernate;

import by.itacademy.hibernate.entity.User;
import by.itacademy.hibernate.util.HibernateUtil;
import by.itacademy.hibernate.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateRunner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();

            var user = session.get(User.class, 1L);

            session.getTransaction().commit();
        }
    }
}
