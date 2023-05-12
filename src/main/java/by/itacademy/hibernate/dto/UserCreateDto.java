package by.itacademy.hibernate.dto;

import by.itacademy.hibernate.entity.PersonalInfo;
import by.itacademy.hibernate.entity.Role;

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
