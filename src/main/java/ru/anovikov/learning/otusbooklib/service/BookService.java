package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.domain.Book;
import ru.anovikov.learning.otusbooklib.domain.Genre;

public interface BookService {
    Book insert(Author author, Genre genre, String title);

    Book update(String id, Author author, Genre genre, String title);

    void delete(String id);

    Book findById(String id);

    Book findByTitle(String title);

}
