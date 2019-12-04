package ru.anovikov.learning.otusbooklib.repository;

import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Genre save(Genre genre);

    void delete(long id);

    Genre getById(long id);

    Genre getByName(String genreName);

    List<Genre> getAll();

}
