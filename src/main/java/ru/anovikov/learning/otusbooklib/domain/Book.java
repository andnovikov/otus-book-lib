package ru.anovikov.learning.otusbooklib.domain;

import javax.persistence.*;

@Entity
@NamedQuery(name="Book.findAll", query="select b from Book b join fetch b.author join fetch b.genre")
@NamedQuery(name="Book.findById", query="select b from Book b join fetch b.author join fetch b.genre where b.id = :bookId")
@NamedQuery(name="Book.findByTitle", query="select b from Book b join fetch b.author join fetch b.genre where b.title = :title")
@NamedQuery(name="Book.findByParam", query="select b from Book b join fetch b.author join fetch b.genre where b.author.id = :authorId and b.genre.id = :genreId and b.title = :title")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorId")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genreId")
    private Genre genre;

    @Column(name = "title", nullable = false)
    private String title;

    public Book() {};

    public Book(Author author, Genre genre, String title){
        this.genre = genre;
        this.title = title;
        this.author = author;
    }

    public Book(long id, Author author, Genre genre, String title){
        this.id = id;
        this.genre = genre;
        this.title = title;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return id + " " + author.getFirstName() + " " + author.getLastName() + " " + genre.getGenreName() + " " + title;
    }
}