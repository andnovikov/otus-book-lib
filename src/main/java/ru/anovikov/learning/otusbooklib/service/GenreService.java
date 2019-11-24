package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Genre;

public interface GenreService {

    Genre insert();

    Genre update();

    void delete();

    Genre findByName();

    Genre findById();

    Genre getById(long id);

    void print(Genre genre);
}
