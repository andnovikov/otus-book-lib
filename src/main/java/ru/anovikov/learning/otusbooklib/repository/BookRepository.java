package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Book;

import java.util.List;

public interface BookRepository {

    Book save(Book book);

    void delete(long id);

    Book findById(long id);

    Book findByTitle(String title);

    Book findByParam(long authorId, long genreId, String title);

    List<Book> getAll();

}