package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final BufferedReader reader;
    private final Locale locale;
    private final MessageSource messageSource;

    @Autowired
    public ConsoleServiceImpl(BufferedReader reader, MessageSource messageSource){
        this.reader = reader;
        this.messageSource = messageSource;
        this.locale = Locale.getDefault();
    }

    @Override
    public long readLong(String message) {
        long result = Long.valueOf(0);
        try {
            System.out.print(messageSource.getMessage(message, new String[] {""}, locale) + ": ");
            result = Long.parseLong(reader.readLine());
        }
        catch (IOException e) {
            System.out.print(messageSource.getMessage("error.read.value", new String[] {""}, locale) + ": " + e.getMessage());
        }
        return result;
    };

    @Override
    public String readString(String message) {
        String result = "";
        try {
            System.out.print(messageSource.getMessage(message, new String[] {""}, locale) + ": ");
            result = reader.readLine();
        }
        catch (IOException e) {
            System.out.print(messageSource.getMessage("error.read.value", new String[] {""}, locale) + ": " + e.getMessage());
        }
        return result;
    };

    @Override
    public void writeString(String message, String value) {
        String str = "";
        if (message.length() > 0) {
            System.out.print(messageSource.getMessage(message, new String[] {""}, locale) + ": ");
        }
        System.out.println(value);
    };

}
