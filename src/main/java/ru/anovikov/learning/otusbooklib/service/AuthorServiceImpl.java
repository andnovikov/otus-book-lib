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
    private ConsoleService consoleService;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, ConsoleService consoleService){
        this.authorDao = authorDao;
        this.consoleService = consoleService;
    }

    @Override
    public Author insert() {
        try {
            String firstName = consoleService.readString("read.author.firstname");
            String lastName = consoleService.readString("read.author.lastname");
            Author author = new Author(0, firstName, lastName);
            author = authorDao.insert(author);
            return author;
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.author.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    @Override
    public Author update() {
        try {
            long id = consoleService.readLong("read.author.id");
            String firstName = consoleService.readString("read.author.firstname");
            String lastName = consoleService.readString("read.author.lastname");
            Author author = new Author(id, firstName, lastName);
            authorDao.update(author);
            author = authorDao.getById(id);
            return author;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DuplicateValueException e) {
            consoleService.writeString("error.author.exists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    @Override
    public void delete() {
        try {
            long id = consoleService.readLong("read.author.id");
            authorDao.delete(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
    }

    @Override
    public Author findByName() {
        try {
            String firstName = consoleService.readString("read.author.firstname");
            String lastName = consoleService.readString("read.author.lastname");
            Author author = authorDao.getByName(firstName, lastName);
            return author;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    public Author findById() {
        try {
            long id = consoleService.readLong("read.author.id");
            Author author = authorDao.getById(id);
            return author;
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    };

    @Override
    public Author getById(long id) {
        try {
            return authorDao.getById(id);
        }
        catch (NoDataFoundException e) {
            consoleService.writeString("error.author.notexists", "");
        }
        catch (DataInputException e) {
            consoleService.writeString("error.read.value", "");
        }
        return null;
    }

    @Override
    public void print(Author author) {
        if (author != null) {
            consoleService.writeString("table.author", author.toString());
        }
    }

}