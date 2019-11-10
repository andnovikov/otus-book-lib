package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Genre;

public interface GenreService {

    Genre createGenre(String genreName);

    Genre updateGenre(long id, String genreName);

    void deleteGenre(long id);

}
