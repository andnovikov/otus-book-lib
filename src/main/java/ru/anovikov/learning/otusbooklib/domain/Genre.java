package ru.anovikov.learning.otusbooklib.domain;

public class Genre {

    private final long id;
    private final String genreName;

    public Genre(long id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public long getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }
}
