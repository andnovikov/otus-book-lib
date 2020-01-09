package ru.anovikov.learning.otusbooklib.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {

    @Id
    @JsonProperty("id")
    @ApiModelProperty(notes = "Book id, generated value", position = 0)
    private String id;
    @DBRef
    @JsonProperty("author")
    @ApiModelProperty(notes = "Book author", position = 1)
    private Author author;
    @DBRef
    @JsonProperty("genre")
    @ApiModelProperty(notes = "Book genre", position  = 2)
    private Genre genre;
    @JsonProperty("title")
    @ApiModelProperty(notes = "Book title", position = 3)
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
}