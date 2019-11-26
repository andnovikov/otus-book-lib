package ru.anovikov.learning.otusbooklib.dao;

import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author insert(Author author);

    void update(Author authorid);

    void delete(long id);

    Author getById(long id);

    Author getByName(String firstName, String lastName);

    List<Author> getAll();

}
