package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByTitle(String value);
    List<Book> findByAuthor(String id);
    List<Book> findByAuthorOrderByTitleAsc(String id);
    List<Book> findAllByOrderByTitleAsc();
}
