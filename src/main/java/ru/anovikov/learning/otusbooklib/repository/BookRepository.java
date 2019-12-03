package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Book;

import java.util.List;

public interface BookRepository {

    Book insert(Book book);

    Book update(Book book);

    void delete(long id);

    Book getById(long id);

    Book getByTitle(String title);

    Book getByParam(long authorId, long genreId, String title);

    List<Book> getAll();

}