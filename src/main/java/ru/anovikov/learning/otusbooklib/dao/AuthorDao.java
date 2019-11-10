package ru.anovikov.learning.otusbooklib.dao;

import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    void update(Author author, long id);

    void deleteById(long id);

    Author getById(long id);

    List<Author> getAll();

}
