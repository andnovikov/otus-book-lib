package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;

public interface GenreRepository {

    Genre save(Genre genre);

    void delete(long id);

    Genre findById(long id);

    Genre findByName(String genreName);

    List<Genre> getAll();

}
