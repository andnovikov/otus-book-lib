package ru.anovikov.learning.otusbooklib.dao;

import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre insert(Genre genre);

    void update(Genre genre);

    void delete(long id);

    Genre getById(long id);

    Genre getByName(String genreName);

    List<Genre> getAll();

}
