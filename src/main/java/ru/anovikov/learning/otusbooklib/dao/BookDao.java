package ru.anovikov.learning.otusbooklib.dao;

import ru.anovikov.learning.otusbooklib.domain.Book;

import java.util.List;

public interface BookDao {
    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
