package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.dao.GenreDao;
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
    public Genre insert() {
        String genreName = consoleService.readString("read.genre.genrename");
        Genre genre = new Genre(0, genreName);
        genre = genreDao.insert(genre);

        consoleService.writeString("table.genre", genre.toString());
        return genre;
    }

    @Override
    public Genre update() {
        long id = consoleService.readLong("read.genre.id");
        String genreName = consoleService.readString("read.genre.genrename");;
        Genre genre = new Genre(0, genreName);
        genreDao.update(genre, id);
        genre = genreDao.getById(id);

        consoleService.writeString("table.genre", genre.toString());
        return genre;
    }

    @Override
    public void delete() {
        long id = consoleService.readLong("read.genre.id");

        //TODO: Check if exists

        genreDao.deleteById(id);
    }

    @Override
    public Genre getGenre(long id){
        return genreDao.getById(id);
    }

}
