package ru.anovikov.learning.otusbooklib.domain;

public class Genre {

    private long id;
    private final String genreName;

    public Genre(long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    @Override
    public String toString() {
        return id + " " + genreName;
    }
}