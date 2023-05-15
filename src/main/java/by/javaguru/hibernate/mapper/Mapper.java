package by.javaguru.hibernate.mapper;

public interface Mapper <F, T> {
    T mapFrom(F object);
}
