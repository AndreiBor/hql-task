package by.itacademy.hibernate.service;

import by.itacademy.hibernate.dao.UserRepository;
import by.itacademy.hibernate.dto.UserCreateDto;
import by.itacademy.hibernate.entity.User;
import by.itacademy.hibernate.mapper.UserCreateMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;


    public Long create(UserCreateDto userDto) {
        //validation
        var userEntity = userCreateMapper.mapFrom(userDto);
        return userRepository.save(userEntity).getId();
    }
}
