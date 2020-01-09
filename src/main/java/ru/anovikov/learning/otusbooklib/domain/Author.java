package ru.anovikov.learning.otusbooklib.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class Author {

    @Id
    @JsonProperty(value = "id")
    @ApiModelProperty(notes = "Author id, generated value", position = 0)
    private String id;
    @JsonProperty(value = "first_name")
    @ApiModelProperty(notes = "Author name", position = 1)
    private String firstName;
    @JsonProperty(value = "last_name")
    @ApiModelProperty(notes = "Author surname", position = 2)
    private String lastName;

    public Author() {};

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
