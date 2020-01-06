package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Author;

import java.util.List;

public interface AuthorService {

    Author insert(String firstName, String lastName);

    Author update(String id, String firstName, String lastName);

    Author update(Author author);

    void delete(String id);

    Author findByName(String firstName, String lastName);

    Author findById(String id);

    List<Author> getAll();

}
