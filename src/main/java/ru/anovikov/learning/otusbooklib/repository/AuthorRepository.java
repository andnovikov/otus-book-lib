package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    void delete(long id);

    Optional<Author> findById(long id);

    Author getByName(String firstName, String lastName);

    List<Author> getAll();

}
