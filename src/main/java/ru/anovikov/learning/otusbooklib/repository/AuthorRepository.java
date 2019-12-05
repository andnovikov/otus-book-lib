package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;

public interface AuthorRepository {

    Author save(Author author);

    void delete(long id);

    Author findById(long id);

    Author findByName(String firstName, String lastName);

    List<Author> getAll();

}
