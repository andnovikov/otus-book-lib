package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.dao.AuthorDao;
import ru.anovikov.learning.otusbooklib.domain.Author;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao){
        this.authorDao = authorDao;
    }

    @Override
    public Author createAuthor(String firstName, String lastName) {
        return  null;
    }

    @Override
    public Author updateAuthor(long id, String firstName, String lastName) {
        return  null;
    }

    @Override
    public void deleteAuthor(long id) {
        authorDao.deleteById(id);
    }

}
