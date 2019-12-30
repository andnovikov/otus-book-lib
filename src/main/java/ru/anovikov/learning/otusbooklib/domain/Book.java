package ru.anovikov.learning.otusbooklib.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private Author author;

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

    @Override
    public String toString() {
        return id + " " + author.getFirstName() + " " + author.getLastName() + " " + genre.getGenreName() + " " + title;
    }
}