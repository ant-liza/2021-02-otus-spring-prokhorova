package ru.otus.books.repository.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.books.model.Author;
import ru.otus.books.model.Book;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.repository.custom.AuthorRepositoryCustom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

    private final MongoTemplate mongoTemplate;
    private final BookRepository bookRepository;

    public AuthorRepositoryImpl(MongoTemplate mongoTemplate, BookRepository bookRepository) {
        this.mongoTemplate = mongoTemplate;
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBookToAuthor(String authorId, String bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Author authorOptional = mongoTemplate.findById(authorId, Author.class);
        if (bookOptional.isPresent() && authorOptional != null) {
            final Author author = authorOptional;
            List<Book> authorBooks = author.getBooks();
            if (authorBooks == null) {
                authorBooks = new ArrayList<>();
            }
            authorBooks.add(bookOptional.get());
            author.setBooks(authorBooks);
            mongoTemplate.save(author);
        }
    }
}
