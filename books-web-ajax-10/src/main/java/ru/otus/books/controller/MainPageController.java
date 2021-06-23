package ru.otus.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.books.Pages;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String getMainPage(Model model) {
        return Pages.MAIN_PAGE.getTemplateName();
    }
}
