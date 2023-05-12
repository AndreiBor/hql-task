package by.itacademy.hibernate.mapper;

import by.itacademy.hibernate.dto.CompanyReadDto;
import by.itacademy.hibernate.dto.UserReadDto;
import by.itacademy.hibernate.entity.PersonalInfo;
import by.itacademy.hibernate.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto mapFrom(User object) {
        return new UserReadDto(object.getId(),
                object.getPersonalInfo(),
                object.getUsername(),
                object.getRole(),
                companyReadMapper.mapFrom(object.getCompany())
        );
    }
}
