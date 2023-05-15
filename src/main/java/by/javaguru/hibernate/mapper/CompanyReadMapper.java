package by.javaguru.hibernate.mapper;

import by.javaguru.hibernate.dto.CompanyReadDto;
import by.javaguru.hibernate.entity.Company;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto>{
    @Override
    public CompanyReadDto mapFrom(Company object) {
        return new CompanyReadDto(object.getId(),
                object.getName());
    }
}
