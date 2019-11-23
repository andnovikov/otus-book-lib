package ru.anovikov.learning.otusbooklib.dao;

import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre insert(Genre genre);

    void update(Genre genre, long id);

    void deleteById(long id);

    Genre getById(long id);

    List<Genre> getAll();

}
