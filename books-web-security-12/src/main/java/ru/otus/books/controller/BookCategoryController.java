package ru.otus.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.books.Pages;
import ru.otus.books.repository.BookCategoryRepository;

@Controller
public class BookCategoryController {
    private final BookCategoryRepository bookCategoryRepository;

    public BookCategoryController(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    @GetMapping("/book_categories")
    public String getAllBookCategories(Model model) {
        model.addAttribute("bc", bookCategoryRepository.findAll());
        return Pages.BOOK_CATEGORIES_PAGE.getTemplateName();
    }
}
