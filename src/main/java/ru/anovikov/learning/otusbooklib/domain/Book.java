package ru.anovikov.learning.otusbooklib.domain;

public class Book {

    private final long id;
    private final long authorId;
    private final long genreId;
    private final String title;

    public Book(long id, long authorId, long genreId, String title){
        this.id = id;
        this.genreId = genreId;
        this.title = title;
        this.authorId = authorId;
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getTitle() {
        return title;
    }

    public long getAuthorId() {
        return authorId;
    }
}
