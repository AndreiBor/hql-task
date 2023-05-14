package by.javaguru.hibernate;

import by.javaguru.hibernate.entity.User;
import by.javaguru.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateRunner {
    public static void main(String[] args) {
        User user = null;
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            user = session.find(User.class, 1L);
            var user1 = session.find(User.class, 1L);
            System.out.println(user.getCompany());
            System.out.println(user.getUserChats().size());
            session.getTransaction().commit();
        }
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user2 = session.find(User.class, 1L);
            System.out.println(user2.getCompany());
            session.getTransaction().commit();
        }
    }
}
