package ru.anovikov.learning.otusbooklib.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Book save(Book book);

    void delete(Book book);

    Optional<Book> findById(String id);

    Optional<Book> findByTitle(String title);

    Optional<Book> findByAuthorAndGenreAndTitle(Author author, Genre genre, String title);

    List<Book> findAll();

    Book findFirstByGenreId(String genreId);

    Book findFirstByAuthorId(String authorId);

    boolean existsById(String id);
}