package by.itacademy.hibernate.mapper;

public interface Mapper<K, F> {
    F mapFrom(K object);
}
