package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Genre;

public interface GenreService {

    Genre insert(String genreName);

    Genre update(String id, String genreName);

    void delete(String id);

    Genre findById(String id);

    Genre findByName(String genreName);

}
