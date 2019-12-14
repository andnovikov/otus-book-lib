package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.repository.GenreRepository;
import ru.anovikov.learning.otusbooklib.domain.Genre;
import ru.anovikov.learning.otusbooklib.repository.NoDataFoundException;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;
    private ConsoleService consoleService;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, ConsoleService consoleService) {
        this.genreRepository = genreRepository;
        this.consoleService = consoleService;
    }

    @Override
    public Genre insert(String genreName) {
        // check for duplicate values
        try {
            Genre foundGenre = genreRepository.findByName(genreName);
            if (foundGenre != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // do nothing
        }
        Genre genre = new Genre(genreName);
        genre = genreRepository.save(genre);
        return genre;
    }

    @Override
    public Genre update(long id, String genreName) {
        //chek if exists
        genreRepository.findById(id);
        // check for duplicate values
        try {
            Genre foundGenre = genreRepository.findByName(genreName);
            if ((foundGenre != null) && (foundGenre.getId() != id)) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // do nothing
        }

        Genre genre = new Genre(id, genreName);
        genreRepository.save(genre);
        genre = genreRepository.findById(id);
        return genre;
    }

    @Override
    public void delete(long id) {
        genreRepository.delete(id);
    }

    @Override
    public Genre findById(long id){
        Genre genre = genreRepository.findById(id);
        return genre;
    }

    @Override
    public Genre findByName(String genreName){
        Genre genre = genreRepository.findByName(genreName);
        return genre;
    }

}
