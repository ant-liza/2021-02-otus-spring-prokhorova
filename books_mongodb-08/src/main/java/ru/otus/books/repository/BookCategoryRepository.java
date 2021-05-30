package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.BookCategory;
import ru.otus.books.repository.custom.BookCategoryRepositoryCustom;

import java.util.List;

public interface BookCategoryRepository extends MongoRepository<BookCategory, Long>, BookCategoryRepositoryCustom {
    List<BookCategory> findByBookCategoryName(String name);
}
