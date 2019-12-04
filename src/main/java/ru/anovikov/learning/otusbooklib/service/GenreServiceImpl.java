package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.repository.GenreRepository;
import ru.anovikov.learning.otusbooklib.domain.Genre;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreDao;
    private ConsoleService consoleService;

    @Autowired
    public GenreServiceImpl(GenreRepository genreDao, ConsoleService consoleService) {
        this.genreDao = genreDao;
        this.consoleService = consoleService;
    }

    @Override
    public Genre insert(String genreName) {
        Genre genre = new Genre(genreName);
        genre = genreDao.save(genre);
        return genre;
    }

    @Override
    public Genre update(long id, String genreName) {
        Genre genre = new Genre(id, genreName);
        genreDao.save(genre);
        genre = genreDao.findById(id);
        return genre;
    }

    @Override
    public void delete(long id) {
        genreDao.delete(id);
    }

    @Override
    public Genre findById(long id){
        Genre genre = genreDao.findById(id);
        return genre;
    }

    @Override
    public Genre findByName(String genreName){
        Genre genre = genreDao.findByName(genreName);
        return genre;
    }

}
