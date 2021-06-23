package ru.otus.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.books.Pages;

@Controller
public class BookPageController {
    @GetMapping("/books")
    public String getAllBooks() {
        return Pages.BOOKS_PAGE.getTemplateName();
    }

}
