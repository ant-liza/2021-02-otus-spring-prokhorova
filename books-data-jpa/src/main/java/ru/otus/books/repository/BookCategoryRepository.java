package ru.otus.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.books.domain.BookCategory;

import java.util.List;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    List<BookCategory> findByBookCategoryName(String name);
}
