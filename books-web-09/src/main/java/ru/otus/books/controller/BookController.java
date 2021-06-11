package ru.otus.books.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.books.Pages;
import ru.otus.books.model.Book;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAllByOrderByTitleAsc());
        return Pages.BOOKS_PAGE.getTemplateName();
    }


    @GetMapping("/delete_book")
    public String deleteBook(@RequestParam("id") String id) {
        bookRepository.deleteById(id);
        return "redirect:/" + Pages.BOOKS_PAGE.getNameWithoutExtension();
    }

    @GetMapping("/edit_book")
    public String editBook(@RequestParam("id") String id, Model model) {
        model.addAttribute("book", bookRepository.findById(id).orElseThrow(NullPointerException::new));
        model.addAttribute("authors", authorRepository.findAll());
        return Pages.EDIT_BOOK_PAGE.getTemplateName();
    }


    @PostMapping("/edit_book")
    public ModelAndView saveBook(@NotNull Book book, ModelAndView model) {
        System.out.println("book = " + book.getBookId());
        Book saved = bookRepository.save(book);
        model.addObject(saved);
        model.addObject("authors", authorRepository.findAll());
        return model;
    }

    @GetMapping("/add_book")
    public String addBook(Model model) {
        Book newBook = new Book();
        model.addAttribute("newBook", newBook);
        model.addAttribute("authors", authorRepository.findAll());
        return Pages.ADD_BOOK_PAGE.getTemplateName();
    }
    @PostMapping("/add_book")
    public String saveNewBook(@NotNull Book book, ModelAndView model) {
        Book saved = bookRepository.save(book);
        model.addObject(saved);
        model.addObject("authors", authorRepository.findAll());
        return "redirect:/"+ Pages.BOOKS_PAGE.getNameWithoutExtension();
    }
}
