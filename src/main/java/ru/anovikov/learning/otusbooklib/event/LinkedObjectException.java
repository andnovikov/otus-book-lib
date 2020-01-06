package ru.anovikov.learning.otusbooklib.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.anovikov.learning.otusbooklib.config.OtusBookLibException;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="There is linked object")
public class LinkedObjectException extends OtusBookLibException {

    LinkedObjectException() {}

}
