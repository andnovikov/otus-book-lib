package ru.anovikov.learning.otusbooklib.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

    @Id
    private String id;
    @DBRef
    private Author author;
    @DBRef
    private Genre genre;
    private String title;

    public Book() {};

    public Book(Author author, Genre genre, String title){
        this.genre = genre;
        this.title = title;
        this.author = author;
    }

    public Book(String id, Author author, Genre genre, String title){
        this.id = id;
        this.genre = genre;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return id + " " + author.getFirstName() + " " + author.getLastName() + " " + genre.getGenreName() + " " + title;
    }
}