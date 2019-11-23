package ru.anovikov.learning.otusbooklib.domain;

public class Author {

    private long id;
    private final String firstName;
    private final String lastName;

    public Author(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return this.id + " " + this.lastName + " " + this.firstName;
    }
}
