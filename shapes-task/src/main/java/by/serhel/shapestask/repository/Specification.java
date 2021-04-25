package by.serhel.shapestask.repository;

@FunctionalInterface
public interface Specification<T> {
    boolean specify(T object);
}
