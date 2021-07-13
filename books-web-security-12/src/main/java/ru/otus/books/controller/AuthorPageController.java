package ru.otus.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.books.Pages;
import ru.otus.books.model.Author;
import ru.otus.books.model.Book;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;

import java.util.List;

@Controller
public class AuthorPageController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    @Autowired
    public AuthorPageController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/authors")
    public String allAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAllByOrderByFirstNameAsc());
        return Pages.AUTHORS_PAGE.getTemplateName();
    }

    @GetMapping("/author")
    public String editAuthor(@RequestParam("id") String id, Model model) {
        Author author = authorRepository.findById(id).orElse(null);
        List<Book> books = bookRepository.findByAuthorOrderByTitleAsc(id);
        model.addAttribute("author", author);
        model.addAttribute("books", books);
        return Pages.EDIT_AUTHOR_PAGE.getTemplateName();
    }
}
