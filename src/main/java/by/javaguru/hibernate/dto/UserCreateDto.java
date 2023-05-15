package by.javaguru.hibernate.dto;

import by.javaguru.hibernate.entity.PersonalInfo;
import by.javaguru.hibernate.entity.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UserCreateDto(
        @Valid
        PersonalInfo personalInfo,
        @NotNull
        String username,
        Role role,
        Integer companyId) {
}
