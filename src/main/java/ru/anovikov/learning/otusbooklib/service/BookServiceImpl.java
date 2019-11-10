package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.dao.BookDao;
import ru.anovikov.learning.otusbooklib.domain.Book;

@Service
public class BookServiceImpl implements BookService{

    BookDao bookDao;

    @Autowired
    public BookServiceImpl (BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book createBook() {
        return null;
    }

    @Override
    public Book updateBook() {
        return null;
    }

    @Override
    public void deleteBook() {

    }

}
