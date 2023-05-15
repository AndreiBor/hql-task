package by.javaguru.hibernate.mapper;

import by.javaguru.hibernate.dao.CompanyRepository;
import by.javaguru.hibernate.dto.UserCreateDto;
import by.javaguru.hibernate.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCreateMapper implements Mapper <UserCreateDto, User>{

    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto object) {
        return User.builder()
                .personalInfo(object.personalInfo())
                .username(object.username())
                .role(object.role())
                .company(companyRepository.findById(object.companyId())
                        .orElseThrow(IllegalArgumentException::new))
                .build();
    }
}
