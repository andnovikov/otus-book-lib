package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    void delete(long id);

    Optional<Book> findById(long id);

    Optional<Book> findByTitle(String title);

    Optional<Book> findByParam(long authorId, long genreId, String title);

    List<Book> getAll();

}