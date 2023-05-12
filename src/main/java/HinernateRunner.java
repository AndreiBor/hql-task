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
            // DI BEANS
            var companyRepository = new CompanyRepository(session);

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);
            var userCreateMapper = new UserCreateMapper(companyRepository);

            var userRepository = new UserRepository(session);
            var userService = new UserService(userRepository, userReadMapper, userCreateMapper);

            userService.findById(1L).ifPresent(System.out::println);
            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstname("Anna2")
                            .lastname("Petrova2")
//                            .birthDate(new Birthday(LocalDate.now()))
                            .build(),
                    "anna4@gmail.com",
                    Role.USER,
                    1
            );
            System.out.println(userService.create(userCreateDto));
            session.getTransaction().commit();
        }
    }
}
