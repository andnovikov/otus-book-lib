package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.dao.AuthorDao;
import ru.anovikov.learning.otusbooklib.dao.DuplicateValueException;
import ru.anovikov.learning.otusbooklib.dao.NoDataFoundException;
import ru.anovikov.learning.otusbooklib.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao){
        this.authorDao = authorDao;
    }

    @Override
    public Author insert(String firstName, String lastName) {
        Author author = new Author(0, firstName, lastName);
        author = authorDao.insert(author);
        return author;
    }

    @Override
    public Author update(long id, String firstName, String lastName) {
        Author author = new Author(id, firstName, lastName);
        authorDao.update(author);
        author = authorDao.getById(id);
        return author;
    }

    @Override
    public void delete(long id) {
        authorDao.delete(id);
    }

    @Override
    public Author findByName(String firstName, String lastName) {
        Author author = authorDao.getByName(firstName, lastName);
        return author;
    }

    public Author findById(long id) {
        Author author = authorDao.getById(id);
        return author;
    };

}