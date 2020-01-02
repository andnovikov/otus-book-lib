package ru.anovikov.learning.otusbooklib.service;

import ru.anovikov.learning.otusbooklib.domain.Author;

public interface AuthorService {

    Author insert(String firstName, String lastName);

    Author update(String id, String firstName, String lastName);

    void delete(String id);

    Author findByName(String firstName, String lastName);

    Author findById(String id);

}
