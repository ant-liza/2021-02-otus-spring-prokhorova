package ru.otus.books.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.books.model.Author;
import ru.otus.books.model.Book;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;

import java.util.List;

@RestController
public class AuthorRestController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorRestController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    @GetMapping("/api/authors")
    List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping("/api/author/{id}/books")
    public List<Book> getAuthorBooks(@PathVariable String id) {
        return bookRepository.findByAuthor(id);
    }
}
