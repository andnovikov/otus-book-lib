package ru.anovikov.learning.otusbooklib.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String welcome(){
        return "index";
    }

    @GetMapping(value = "/authors")
    public String getAuthors() {
        return "authors";
    }

    @GetMapping(value = "/books")
    public String getBooks() {
        return "books";
    }

    @GetMapping(value = "/genres")
    public String getGenres() {
        return "genres";
    }

}
