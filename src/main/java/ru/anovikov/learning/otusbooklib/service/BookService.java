package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Book;

public interface BookService {
    Book createBook();

    Book updateBook();

    void deleteBook();
}
