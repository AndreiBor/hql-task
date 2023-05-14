package by.javaguru.hibernate.util;

import by.javaguru.hibernate.convertor.BirthdayConvertor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConvertor());
//        configuration.registerTypeOverride(new JsonBinaryType());
//        configuration.addAnnotatedClass(User.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        return configuration.buildSessionFactory();
    }
}
