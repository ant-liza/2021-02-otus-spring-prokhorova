package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.BookCategory;

import java.util.List;

public interface BookCategoryRepository extends MongoRepository<BookCategory, String> {
    List<BookCategory> findByBookCategoryName(String name);
}
