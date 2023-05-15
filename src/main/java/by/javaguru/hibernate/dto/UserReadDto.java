package by.javaguru.hibernate.dto;

import by.javaguru.hibernate.entity.PersonalInfo;
import by.javaguru.hibernate.entity.Role;

public record UserReadDto(Long id,
                          PersonalInfo personalInfo,
                          String username,
                          Role role,
                          CompanyReadDto company) {
}
