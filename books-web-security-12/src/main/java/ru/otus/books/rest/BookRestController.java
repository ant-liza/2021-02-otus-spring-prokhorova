package ru.otus.books.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.books.model.Author;
import ru.otus.books.model.Book;
import ru.otus.books.pojo.BookPOJO;
import ru.otus.books.repository.AuthorRepository;
import ru.otus.books.repository.BookRepository;

import java.util.List;

@RestController
public class BookRestController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookRestController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    @DeleteMapping("/api/book/{id}")
    public String deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id);
        return "book have been deleted!";
    }


    @GetMapping("/api/book/{id}")
    public Book currentBook(@PathVariable String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/book/{id}")
    public String saveBook(BookPOJO pogo) {
        Book book = bookRepository.findById(pogo.getBookId()).orElse(new Book());
        Author author = authorRepository.findById(pogo.getAuthorId()).orElse(null);
        book.setTitle(pogo.getTitle());
        book.setAuthor(author);
        bookRepository.save(book);
        return "Книга обновлена/добавлена";
    }

}
