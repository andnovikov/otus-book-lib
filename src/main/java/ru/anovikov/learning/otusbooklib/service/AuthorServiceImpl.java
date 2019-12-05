package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.repository.AuthorRepository;
import ru.anovikov.learning.otusbooklib.repository.NoDataFoundException;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public Author insert(String firstName, String lastName) {
        // check for duplicate values
        try {
            Author foundAuthor = authorRepository.findByName(firstName, lastName);
            if (foundAuthor != null) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // do nothing
        }

        Author author = new Author(firstName, lastName);
        author = authorRepository.save(author);
        return author;
    }

    @Override
    public Author update(long id, String firstName, String lastName) {
        //chek if exists
        authorRepository.findById(id);
        // check for duplicate values
        try {
            Author foundAuthor = authorRepository.findByName(firstName, lastName);
            if ((foundAuthor != null) && (foundAuthor.getId() != id)) {
                throw new DuplicateValueException();
            }
        }
        catch (NoDataFoundException e) {
            // do nothing
        }

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
        return authorRepository.findByName(firstName, lastName);
    }

    public Author findById(long id) {
        return authorRepository.findById(id);
    };

}