package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;

public interface AuthorRepository {

    Author insert(Author author);

    void update(Author author);

    void delete(long id);

    Author getById(long id);

    Author getByName(String firstName, String lastName);

    List<Author> getAll();

}
