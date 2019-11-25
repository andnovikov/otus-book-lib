package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

public interface BookService {
    Book insert(Author author, Genre genre, String title);

    Book update(long id, Author author, Genre genre, String title);

    void delete(long id);

    Book findById(long id);

    Book findByTitle(String title);

    Book getById(long id);

}
