package by.javaguru.hibernate.dao;


import by.javaguru.hibernate.dto.PaymentFilter;
import by.javaguru.hibernate.entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    /**
     * Возвращает всех сотрудников
     */
    public List<User> findAll(Session session) {
        return new JPAQuery<User>(session).select(QUser.user).from(QUser.user).fetch();
    }

    /**
     * Возвращает всех сотрудников с указанным именем
     */
    public List<User> findAllByFirstName(Session session, String firstName) {
        return session.createQuery(
                        "select u from User u where u.personalInfo.firstname=?",
                        User.class)
                .setParameter(1, "Pavel")
                .list();
    }

    /**
     * Возвращает первые {limit} сотрудников, упорядоченных по дате рождения (в порядке возрастания)
     */
    public List<User> findLimitedUsersOrderedByBirthday(Session session, int limit) {
        return new JPAQuery<User>(session).select(QUser.user).from(QUser.user)
                .orderBy(new OrderSpecifier(Order.ASC, QUser.user.personalInfo().birthDate))
                .limit(limit).fetch();
    }

    /**
     * Возвращает всех сотрудников компании с указанным названием
     */
    public List<User> findAllByCompanyName(Session session, String companyName) {
        return new JPAQuery<User>(session).select(QUser.user).from(QCompany.company)
                .join(QCompany.company.users, QUser.user)
                .where(QCompany.company.name.eq(companyName))
                .fetch();
    }

    /**
     * Возвращает все выплаты, полученные сотрудниками компании с указанными именем,
     * упорядоченные по имени сотрудника, а затем по размеру выплаты
     */
    public List<Payment> findAllPaymentsByCompanyName(Session session, String companyName) {
        return new JPAQuery<Payment>(session)
                .select(QPayment.payment)
                .from(QCompany.company)
                .join(QCompany.company.users, QUser.user)
                .join(QUser.user.payments, QPayment.payment)
                .where(QCompany.company.name.eq(companyName))
                .orderBy(QUser.user.personalInfo().firstname.asc(), QPayment.payment.amount.asc())
                .fetch();
    }

    /**
     * Возвращает среднюю зарплату сотрудника с указанными именем и фамилией
     */
    public Double findAveragePaymentAmountByFirstAndLastNames(Session session, PaymentFilter filter) {
        /*List<Predicate> predicates = new ArrayList<>();
        if(filter.getFirstname() != null)
            predicates.add(user.personalInfo().firstname.eq(filter.getFirstname()));
        if(filter.getLastname() != null)
            predicates.add(user.personalInfo().lastname.eq(filter.getLastname()));*/

        var predicate = QPredicate.builder()
                .add(filter.getFirstname(), QUser.user.personalInfo().firstname::eq)
                .add(filter.getLastname(), QUser.user.personalInfo().lastname::eq)
                .buildOr();

        return new JPAQuery<Double>(session)
                .select(QPayment.payment.amount.avg())
                .from(QPayment.payment)
                .join(QPayment.payment.receiver(), QUser.user)
                .where(predicate)
                .fetchOne();
    }

    /**
     * Возвращает для каждой компании: название, среднюю зарплату всех её сотрудников. Компании упорядочены по названию.
     */
    public List<Tuple> findCompanyNamesWithAvgUserPaymentsOrderedByCompanyName(Session session) {
        return new JPAQuery<Tuple>(session)
                .select(QCompany.company.name, QPayment.payment.amount.avg())
                .from(QCompany.company)
                .join(QCompany.company.users, QUser.user)
                .join(QUser.user.payments, QPayment.payment)
                .groupBy(QCompany.company.name)
                .orderBy(QCompany.company.name.asc())
                .fetch();

    }

    /**
     * Возвращает список: сотрудник (объект User), средний размер выплат, но только для тех сотрудников, чей средний размер выплат
     * больше среднего размера выплат всех сотрудников
     * Упорядочить по имени сотрудника
     */
    public List<Tuple> isItPossible(Session session) {
        return new JPAQuery<Tuple>(session)
                .select(QUser.user, QPayment.payment.amount.avg())
                .from(QUser.user)
                .join(QUser.user.payments, QPayment.payment)
                .groupBy(QUser.user.id)
                .having(QPayment.payment.amount.avg().gt(
                        new JPAQuery<Double>(session)
                                .select(QPayment.payment.amount.avg())
                                .from(QPayment.payment)
                ))
                .orderBy(QUser.user.personalInfo().firstname.asc())
                .fetch();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}