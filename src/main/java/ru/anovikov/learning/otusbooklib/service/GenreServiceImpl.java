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
    public Genre insert() {
        try {
            String genreName = consoleService.readString("read.genre.genrename");
            Genre genre = new Genre(0, genreName);
            genre = genreDao.insert(genre);
            return genre;
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.genre.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    @Override
    public Genre update() {
        try {
            long id = consoleService.readLong("read.genre.id");
            String genreName = consoleService.readString("read.genre.genrename");
            Genre genre = new Genre(0, genreName);
            genreDao.update(genre, id);
            genre = genreDao.getById(id);
            return genre;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.genre.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    @Override
    public void delete() {
        try {
            long id = consoleService.readLong("read.genre.id");
            genreDao.deleteById(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @Override
    public Genre getById(long id){
        try {
            return genreDao.getById(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    @Override
    public Genre findByName(){
        try {
            String genreName = consoleService.readString("read.genre.genrename");
            Genre genre = genreDao.getByName(genreName);
            return genre;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    @Override
    public Genre findById(){
        try {
            long id = consoleService.readLong("read.genre.id");
            Genre genre = genreDao.getById(id);
            return genre;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.genre.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    @Override
    public void print(Genre genre) {
        if (genre != null) {
            consoleService.writeString("table.genre", genre.toString());
        }
    };

}
