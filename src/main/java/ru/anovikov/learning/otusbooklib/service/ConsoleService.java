package ru.anovikov.learning.otusbooklib.service;

public interface ConsoleService {

    long readLong(String message);

    String readString(String message);

    void writeString(String message, String value);

}