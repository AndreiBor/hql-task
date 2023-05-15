package by.javaguru.hibernate.mapper;

import by.javaguru.hibernate.dto.UserReadDto;
import by.javaguru.hibernate.entity.User;
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
                companyReadMapper.mapFrom(object.getCompany()));
    }
}
