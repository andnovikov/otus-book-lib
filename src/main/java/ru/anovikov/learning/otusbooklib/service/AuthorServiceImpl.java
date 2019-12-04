package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.repository.AuthorRepository;
import ru.anovikov.learning.otusbooklib.repository.NoDataFoundException;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public Author insert(String firstName, String lastName) {
        Author author = new Author(0, firstName, lastName);
        author = authorRepository.save(author);
        return author;
    }

    @Override
    public Author update(long id, String firstName, String lastName) {
        Author author = new Author(id, firstName, lastName);
        author = authorRepository.save(author);
        return author;
    }

    @Override
    public void delete(long id) {
        authorRepository.delete(id);
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        Author author = authorRepository.getByName(firstName, lastName);
        return author;
    }

    public Author findById(long id) {
        Optional<Author> foundEntity = authorRepository.findById(id);

        if (!foundEntity.isPresent()) {
            //TODO: throw new NoDataFoundException();
        }

        return foundEntity.get();
    };

}