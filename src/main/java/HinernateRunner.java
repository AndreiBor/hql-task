import by.itacademy.hibernate.dao.CompanyRepository;
import by.itacademy.hibernate.dao.PaymentRepository;
import by.itacademy.hibernate.dao.UserRepository;
import by.itacademy.hibernate.dto.UserCreateDto;
import by.itacademy.hibernate.entity.*;
import by.itacademy.hibernate.mapper.CompanyReadMapper;
import by.itacademy.hibernate.mapper.UserCreateMapper;
import by.itacademy.hibernate.mapper.UserReadMapper;
import by.itacademy.hibernate.service.UserService;
import by.itacademy.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.lang.reflect.Proxy;
import java.time.LocalDate;

@Transactional
public class HinernateRunner {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));

            session.beginTransaction();

            var companyRepository = new CompanyRepository(session);
            var userCreateMapper = new UserCreateMapper(companyRepository);
            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);
            var userRepository = new UserRepository(session);
            var userService = new UserService(userRepository, userCreateMapper, userReadMapper);

            UserCreateDto userCreateDto = new UserCreateDto(
              PersonalInfo.builder()
                      .firstname("Anna")
                      .lastname("Ivanovskaya")
//                      .birthDate(new Birthday(LocalDate.now()))
                      .build(),
                    "anna13@gmail.com",
                    Role.USER,
                    1
            );

            System.out.println(userService.create(userCreateDto));
            System.out.println(userService.findUserById(15L));
            session.getTransaction().commit();
        }
    }
}
