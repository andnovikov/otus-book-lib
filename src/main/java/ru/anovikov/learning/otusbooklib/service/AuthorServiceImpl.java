package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.domain.Author;
import ru.anovikov.learning.otusbooklib.repository.AuthorRepository;

import java.util.List;
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
        // check for duplicate values
        Optional<Author> foundAuthor = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        if (foundAuthor.isPresent()) {
            throw new DuplicateValueException();
        }
        Author author = new Author(firstName, lastName);
        author = authorRepository.save(author);
        return author;
    }

    @Override
    public Author update(String id, String firstName, String lastName) {
        //chek if exists
        if (!authorRepository.existsById(id)) {
            throw new NoDataFoundException();
        }
        authorRepository.findById(id);
        // check for duplicate values
        Optional<Author> foundAuthor = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        if (foundAuthor.isPresent() && (!foundAuthor.get().getId().equalsIgnoreCase(id))) {
            throw new DuplicateValueException();
        }
        Author author = new Author(id, firstName, lastName);
        author = authorRepository.save(author);
        return author;
    }

    @Override
    public Author update(Author author) {
        //chek if exists
        if (!authorRepository.existsById(author.getId())) {
            throw new NoDataFoundException();
        }
        authorRepository.findById(author.getId());
        // check for duplicate values
        Optional<Author> foundAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        if (foundAuthor.isPresent() && (!foundAuthor.get().getId().equalsIgnoreCase(author.getId()))) {
            throw new DuplicateValueException();
        }
        author = authorRepository.save(author);
        return author;
    }

    @Override
    public void delete(String id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if (!foundAuthor.isPresent()) {
            throw new NoDataFoundException();
        }
        authorRepository.delete(foundAuthor.get());
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        Optional<Author> foundAuthor = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!foundAuthor.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundAuthor.get();
    }

    public Author findById(String id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if (!foundAuthor.isPresent()) {
            throw new NoDataFoundException();
        }
        return foundAuthor.get();
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

}