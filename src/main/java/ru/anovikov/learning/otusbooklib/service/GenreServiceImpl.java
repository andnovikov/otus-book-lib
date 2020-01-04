package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.repository.GenreRepository;
import ru.anovikov.learning.otusbooklib.domain.Genre;

import java.util.List;
import java.util.Optional;

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
        Optional<Genre> foundGenre = genreRepository.findByGenreName(genreName);
        if (foundGenre.isPresent()) {
            throw new DuplicateValueException();
        }
        Genre genre = new Genre(genreName);
        genre = genreRepository.save(genre);
        return genre;
    }

    @Override
    public Genre update(String id, String genreName) {
        //chek if exists
        if (!genreRepository.existsById(id)) {
            throw new NoDataFoundException();
        }
        // check for duplicate values
        Optional<Genre> foundGenre = genreRepository.findByGenreName(genreName);
        if (foundGenre.isPresent() && (!foundGenre.get().getId().equalsIgnoreCase(id) )) {
            throw new DuplicateValueException();
        }
        Genre genre = new Genre(id, genreName);
        genre = genreRepository.save(genre);
        return genre;
    }

    @Override
    public Genre update(Genre genre) {
        //chek if exists
        if (!genreRepository.existsById(genre.getId())) {
            throw new NoDataFoundException();
        }
        // check for duplicate values
        Optional<Genre> foundGenre = genreRepository.findByGenreName(genre.getGenreName());
        if (foundGenre.isPresent() && (!foundGenre.get().getId().equalsIgnoreCase(genre.getId()))) {
            throw new DuplicateValueException();
        }
        genre = genreRepository.save(genre);
        return genre;
    }

    @Override
    public void delete(String id) {
        Optional<Genre> foundGenre = genreRepository.findById(id);
        if (!foundGenre.isPresent()) {
            throw new NoDataFoundException();
        }
        genreRepository.delete(foundGenre.get());
    }

    @Override
    public Genre findById(String id){
        Optional<Genre> foundGenre = genreRepository.findById(id);
        if (!foundGenre.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundGenre.get();
    }

    @Override
    public Genre findByName(String genreName){
        Optional<Genre> foundGenre = genreRepository.findByGenreName(genreName);
        if (!foundGenre.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundGenre.get();
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
