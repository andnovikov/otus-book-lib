package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Author;

public interface AuthorService {

    Author insert();

    Author update();

    void delete();

    Author findByName();

    Author findById();

    Author getById(long id);

    void print(Author author);

}
