package by.itacademy.hibernate.mapper;

import by.itacademy.hibernate.dto.CompanyReadDto;
import by.itacademy.hibernate.entity.Company;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {

    @Override
    public CompanyReadDto mapFrom(Company object) {
        return new CompanyReadDto(object.getId(),
                object.getName());
    }
}
