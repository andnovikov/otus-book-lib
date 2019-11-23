package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.dao.AuthorDao;
import ru.anovikov.learning.otusbooklib.domain.Author;
import sun.security.util.AuthResources;

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
    public void insert() {
        String firstName = consoleService.readString("read.author.firstname");
        String lastName = consoleService.readString("read.author.lastname");
        Author author = new Author(0, firstName, lastName);
        author = authorDao.insert(author);
        consoleService.writeString("table.author", author.toString());
    }

    @Override
    public void update() {
        long id = consoleService.readLong("read.author.id");

        //TODO: Check if exists

        String firstName = consoleService.readString("read.author.firstname");
        String lastName = consoleService.readString("read.author.lastname");
        Author author = new Author(0, firstName, lastName);
        authorDao.update(author, id);
        author = authorDao.getById(id);
        consoleService.writeString("table.author", author.toString());
    }

    @Override
    public void delete() {
        long id = consoleService.readLong("read.author.id");

        //TODO: Check if exists

        authorDao.deleteById(id);
    }

    @Override
    public void findByName() {
        String firstName = consoleService.readString("read.author.firstname");
        String lastName = consoleService.readString("read.author.lastname");
        Author author = authorDao.getByName(firstName, lastName);
        consoleService.writeString("table.author", author.toString());
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Author getByName() {
        return null;
        //TODO read author
    }

}
