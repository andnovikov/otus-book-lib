package ru.anovikov.learning.otusbooklib.dao;

import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);

}
