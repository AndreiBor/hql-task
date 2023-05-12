package by.itacademy.hibernate.service;

import by.itacademy.hibernate.dao.UserRepository;
import by.itacademy.hibernate.dto.UserCreateDto;
import by.itacademy.hibernate.dto.UserReadDto;
import by.itacademy.hibernate.mapper.UserCreateMapper;
import by.itacademy.hibernate.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;

    public Long create(UserCreateDto userDto) {
        //validation
        var validationFactory = Validation.buildDefaultValidatorFactory();
        var validator = validationFactory.getValidator();
        var validationResult = validator.validate(userDto);
        if(!validationResult.isEmpty())
            throw new ConstraintViolationException(validationResult);

        var userEntity = userCreateMapper.mapFrom(userDto);
        return userRepository.save(userEntity).getId();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }

    public boolean delete(Long id) {
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

}
