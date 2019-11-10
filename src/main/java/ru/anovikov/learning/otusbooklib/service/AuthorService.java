package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Author;

public interface AuthorService {

    Author createAuthor(String firstName, String lastName);

    Author updateAuthor(long id, String firstName, String lastName);

    void deleteAuthor(long id);

}
