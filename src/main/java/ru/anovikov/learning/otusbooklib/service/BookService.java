package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Book;

public interface BookService {
    Book insert();

    Book update();

    void delete();

    Book findById();

    Book findByTitle();

    Book getById(long id);

    void print(Book book);
}
