package by.javaguru.hibernate.dao;

import by.javaguru.hibernate.entity.Company;

import javax.persistence.EntityManager;

public class CompanyRepository extends BaseRepository<Integer, Company> {
    public CompanyRepository(EntityManager entityManager) {
        super(Company.class, entityManager);
    }
}
