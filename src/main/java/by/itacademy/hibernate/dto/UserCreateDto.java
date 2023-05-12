package by.itacademy.hibernate.dto;

import by.itacademy.hibernate.entity.PersonalInfo;
import by.itacademy.hibernate.entity.Role;

public record UserCreateDto(PersonalInfo personalInfo,
                            String username,
                            Role role,
                            Integer companyId) {
}
