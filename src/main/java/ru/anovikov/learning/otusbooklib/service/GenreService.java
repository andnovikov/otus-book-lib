package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Genre;

public interface GenreService {

    Genre insert(String genreName);

    Genre update(long id, String genreName);

    void delete(long id);

    Genre findById(long id);

    Genre findByName(String genreName);

}
