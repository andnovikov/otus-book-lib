package ru.anovikov.learning.otusbooklib.domain;

public class Book {

    private final long id;
    private final Author author;
    private final Genre genre;
    private final String title;

    public Book(long id, Author author, Genre genre, String title){
        this.id = id;
        this.genre = genre;
        this.title = title;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }
}