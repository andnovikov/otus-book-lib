package ru.anovikov.learning.otusbooklib.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
public class Genre {

    @Id
    @JsonProperty("id")
    @ApiModelProperty(notes = "Genre id, generated value", position = 0)
    private String id;
    @JsonProperty("genre_name")
    @ApiModelProperty(notes = "Genre name", position = 1)
    private String genreName;

    public Genre() {};

    public Genre(String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public Genre(String id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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