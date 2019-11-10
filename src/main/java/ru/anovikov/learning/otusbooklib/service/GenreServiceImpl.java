package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.dao.GenreDao;
import ru.anovikov.learning.otusbooklib.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre createGenre(String genreName) {
        return null;
    }

    @Override
    public Genre updateGenre(long id, String genreName) {
        return null;
    }

    @Override
    public void deleteGenre(long id) {

    }

}
