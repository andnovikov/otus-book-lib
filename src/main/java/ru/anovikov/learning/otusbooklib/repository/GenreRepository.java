package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;

public interface GenreRepository {

    Genre insert(Genre genre);

    void update(Genre genre);

    void delete(long id);

    Genre getById(long id);

    Genre getByName(String genreName);

    List<Genre> getAll();

}
