package com.company.io;

public interface Source<T> {
    void load(T t);
    void store(T t);
    boolean delete(T t);
    boolean create(T t);
}
