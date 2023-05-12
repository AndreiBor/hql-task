package by.itacademy.hibernate.dto;

import by.itacademy.hibernate.entity.Company;
import by.itacademy.hibernate.entity.PersonalInfo;
import by.itacademy.hibernate.entity.Role;

public record UserReadDto (Long id,
                           PersonalInfo personalInfo,
                           String username,
                           Role role,
                           CompanyReadDto company) {
}
