package ru.anovikov.learning.otusbooklib.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Author.findAll", query="select a from Author a")
@NamedQuery(name="Author.getByName", query="select a from Author a where a.firstName = :firstName and a.lastName = : lastName")
public class Author {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    public Author() {};

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.id + " " + this.lastName + " " + this.firstName;
    }
}
