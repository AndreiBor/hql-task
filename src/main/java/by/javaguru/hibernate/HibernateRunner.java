package by.javaguru.hibernate;

import by.javaguru.hibernate.dao.CompanyRepository;
import by.javaguru.hibernate.dao.PaymentRepository;
import by.javaguru.hibernate.dao.UserRepository;
import by.javaguru.hibernate.dto.UserCreateDto;
import by.javaguru.hibernate.entity.Birthday;
import by.javaguru.hibernate.entity.PersonalInfo;
import by.javaguru.hibernate.entity.Role;
import by.javaguru.hibernate.entity.User;
import by.javaguru.hibernate.mapper.CompanyReadMapper;
import by.javaguru.hibernate.mapper.UserCreateMapper;
import by.javaguru.hibernate.mapper.UserReadMapper;
import by.javaguru.hibernate.service.UserService;
import by.javaguru.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            session.beginTransaction();

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);

            var userRepository = new UserRepository(session);
            var companyRepository = new CompanyRepository(session);
            var userCreateMapper = new UserCreateMapper(companyRepository);
            var userService = new UserService(userRepository, userReadMapper, userCreateMapper);

            userService.findById(1L).ifPresent(System.out::println);


            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstname("Anna")
                            .lastname("Petrova")
//                            .birthDate(new Birthday(LocalDate.now()))
                            .build(),
                    "anna109@gmail.com",
                    Role.USER,
                    1
            );
            System.out.println(userService.create(userCreateDto));

            session.getTransaction().commit();

        }
    }
}
