package ru.anovikov.learning.otusbooklib.domain;

import javax.persistence.*;

@Entity
@NamedQuery(name="Genre.findAll", query="select g from Genre g")
@NamedQuery(name="Genre.getByName", query="select g from Genre g where g.genreName = :genreName")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "genreName", nullable = false, unique = true)
    private String genreName;

    public Genre() {};

    public Genre(String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

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

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return id + " " + genreName;
    }
}