package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Author;

public interface AuthorService {

    void insert();

    void update();

    void delete();

    void findByName();

    Author getById(long id);

    Author getByName();

}
