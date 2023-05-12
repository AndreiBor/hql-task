package by.itacademy.hibernate.mapper;

public interface Mapper<F, T> {
    T mapFrom(F object);
}
