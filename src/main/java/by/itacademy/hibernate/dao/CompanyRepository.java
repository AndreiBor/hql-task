package by.itacademy.hibernate.dao;

import by.itacademy.hibernate.entity.Company;

import javax.persistence.EntityManager;

public class CompanyRepository extends RepositoryBase<Integer, Company>{

    public CompanyRepository(EntityManager entityManager) {
        super(entityManager, Company.class);
    }
}
