package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre insert(String genreName);

    Genre update(String id, String genreName);

    Genre update(Genre genre);

    void delete(String id);

    Genre findById(String id);

    Genre findByName(String genreName);

    List<Genre> getAll();

}
