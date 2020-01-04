package ru.anovikov.learning.otusbooklib.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Element already exists")
public class DuplicateValueException extends RuntimeException {

    DuplicateValueException () {}

}
