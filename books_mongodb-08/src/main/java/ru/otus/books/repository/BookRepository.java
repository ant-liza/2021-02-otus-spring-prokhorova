package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.Book;
import ru.otus.books.repository.custom.BookRepositoryCustom;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long>, BookRepositoryCustom {
    Optional<Book> findByTitle(String value);
}
