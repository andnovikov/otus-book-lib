package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.dao.DuplicateValueException;
import ru.anovikov.learning.otusbooklib.dao.GenreDao;
import ru.anovikov.learning.otusbooklib.dao.NoDataFoundException;
import ru.anovikov.learning.otusbooklib.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreDao genreDao;
    private ConsoleService consoleService;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao, ConsoleService consoleService) {
        this.genreDao = genreDao;
        this.consoleService = consoleService;
    }

    @Override
    public Genre insert(String genreName) {
        Genre genre = new Genre(0, genreName);
        genre = genreDao.insert(genre);
        return genre;
    }

    @Override
    public Genre update(long id, String genreName) {
        Genre genre = new Genre(id, genreName);
        genreDao.update(genre);
        genre = genreDao.getById(id);
        return genre;
    }

    @Override
    public void delete(long id) {
        genreDao.delete(id);
    }

    @Override
    public Genre findById(long id){
        Genre genre = genreDao.getById(id);
        return genre;
    }

    @Override
    public Genre findByName(String genreName){
        Genre genre = genreDao.getByName(genreName);
        return genre;
    }

}
