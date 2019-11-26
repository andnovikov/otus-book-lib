package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageServiceImpl implements  MessageService {

    private final Locale locale;
    private final MessageSource messageSource;

    @Autowired
    public MessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = Locale.getDefault();
    }

    @Override
    public String getMessage(String message) {
        return messageSource.getMessage(message, new String[] {""}, locale);
    }
}
