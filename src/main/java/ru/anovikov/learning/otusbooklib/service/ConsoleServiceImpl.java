package ru.anovikov.learning.otusbooklib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusbooklib.shell.DataInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final BufferedReader reader;
    private final MessageService messageService;


    @Autowired
    public ConsoleServiceImpl(BufferedReader reader, MessageService messageService){
        this.reader = reader;
        this.messageService = messageService;
    }

    @Override
    public long readLong(String message) {
        long result = Long.valueOf(0);
        try {
            System.out.print(messageService.getMessage(message) + " ");
            result = Long.parseLong(reader.readLine());
        }
        catch (IOException e) {
            System.out.print(messageService.getMessage("error.read.value") + " " + e.getMessage());
        }
        return result;
    };

    @Override
    public String readString(String message) {
        String result = "";
        try {
            System.out.print(messageService.getMessage(message) + " ");
            result = reader.readLine();

            if (result.equalsIgnoreCase("")) {
                throw new DataInputException();
            }
        }
        catch (IOException e) {
            System.out.print(messageService.getMessage("error.read.value") + " " + e.getMessage());
        }
        return result;
    };

    @Override
    public void writeString(String message, String value) {
        String str = "";
        if (message.length() > 0) {
            System.out.print(messageService.getMessage(message) + " ");
        }
        System.out.println(value);
    };

}